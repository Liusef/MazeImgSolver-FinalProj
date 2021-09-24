package MazeImg;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * A wrapper class for the OpenCV Mat class with some extra utility methods
 */
public class Image
{

    private Mat mat;
    private Mat ogMat;

    /**
     * Instantiates a new Image.
     */
    public Image()
    {
        loadNative();
    }

    /**
     * Instantiates a new Image.
     *
     * @param nMat the n mat
     */
    public Image( Mat nMat )
    {
        mat = nMat;
        ogMat = mat;
    }

    /**
     * Instantiates a new Image.
     *
     * @param path the path
     */
    public Image( String path )
    {
        loadNative();
        readNew( path );
    }

    /**
     * Loads native libraries (Required by OpenCV)
     */
    public static void loadNative()
    {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    }

    /**
     * Reads new image to replace existing image of the mat
     *
     * @param path the path the image
     */
    public void readNew( String path )
    {
        mat = Imgcodecs.imread( path, Imgcodecs.IMREAD_UNCHANGED );
        ogMat = mat;
    }

    /**
     * Revert to the original
     */
    public void revert()
    {
        mat = ogMat;
    }

    /**
     * Writes image
     *
     * @param path the path to write to
     */
    public void writeImg( String path )
    {
        Imgcodecs.imwrite( path, mat );
    }

    /**
     * Binarizes the image
     *
     * @param threshold the threshold for the binarization
     */
    public void binarizeThresh( int threshold )
    {
        binarizeThresh( threshold, isBright( mat ) );
    }

    /**
     * Binarize thresh.
     *
     * @param threshold the threshold for the binarization
     * @param invert    whether or not to invert the image afterwards
     */
    public void binarizeThresh( int threshold, boolean invert )
    {
        mat = invert ? invert( mat ) : mat;
        Imgproc.threshold( mat, mat, threshold, 255,
                Imgproc.THRESH_BINARY );
        mat = mat;
    }

    /**
     * Removes transparency
     */
    public void removeTransparency()
    {
        if ( mat.channels() != 4 ) return;
        if ( isTransparent( mat ) )
        {
            mat = isBright( mat ) ? invert( mat ) : mat;
            for ( int y = 0; y < mat.rows(); y++ )
            {
                for ( int x = 0; x < mat.cols(); x++ )
                {
                    if ( mat.get( y, x )[3] < 255 ) mat.put( y, x,
                            new byte[]{ ( byte ) 0xFF, ( byte ) 0xFF,
                                    ( byte ) 0xFF, ( byte ) 0xFF } );
                    // If transparency is detected, the pixel is set to white
                }
            }
        }
        Imgproc.cvtColor( mat, mat, Imgproc.COLOR_BGRA2BGR );
    }

    /**
     * Calculates the median luma
     *
     * @param img the img
     * @return the median luma (0-2^(bit depth))
     */
    public static double medianLuma( Mat img )
    {
        Mat r = new Mat();
        Imgproc.cvtColor( img, r, Imgproc.COLOR_BGR2HLS );
        Scalar s = Core.mean( r );
        return ( int ) s.val[1]; // Grabs luma from Hue, **LUMA**, Saturation
    }

    /**
     * Calculates the median alpha
     *
     * @param img the img
     * @return the median alpha (0-2^(bit depth))
     */
    public static double medianAlpha( Mat img )
    {
        Scalar s = Core.mean( img );
        if ( s.val.length < 4 ) return 0;
        return ( int ) s.val[3];
    }

    /**
     * Inverts the image
     *
     * @param img the img
     * @return an inverted mat
     */
    public static Mat invert( Mat img )
    {
        Mat r = new Mat();
        Core.bitwise_not( img, r );
        return r;
    }

    /**
     * Is the image bright ( more than half of max)
     *
     * @param img the img
     * @return whether its bright
     */
    public static boolean isBright( Mat img )
    {
        return medianLuma( img ) >= 127.5;
    }

    /**
     * Is the image transparent (more than half of max)
     *
     * @param img the img
     * @return whether its transparent
     */
    public static boolean isTransparent( Mat img )
    {
        return medianAlpha( img ) <= 127.5;
    }


    /**
     * Get pixel values
     *
     * @param x the x
     * @param y the y
     * @return the values
     */
    public double[] getPixel( int x, int y )
    {
        return mat.get( y, x );
    }


    /**
     * Crops the image between 2 points
     *
     * @param p1 point 1
     * @param p2 point 2
     */
    public void crop( Point p1, Point p2 )
    {
        mat = new Mat( mat, new Range( ( int ) p1.y, ( int ) p2.y ),
                new Range( ( int ) p1.x, ( int ) p2.x ) );
    }

    /**
     * Crops the image and returns a new Image object
     *
     * @param p1 point 1
     * @param p2 point 2
     * @return new Image object
     */
    public Image cropCopy( Point p1, Point p2 )
    {
        return new Image( new Mat( mat, new Range( ( int ) p1.y, ( int ) p2.y ),
                new Range( ( int ) p1.x, ( int ) p2.x ) ) );
    }

    /**
     * Find corners of an image.
     *
     * @return All corners (areas of interest)
     */
    public Point[] findCorners()
    {
        MatOfPoint r = new MatOfPoint();
        Mat mat2 = mat.clone();
        Imgproc.cvtColor( mat2, mat2, Imgproc.COLOR_BGR2GRAY );
        //mat2.convertTo(mat2, CvType.CV_8UC1);
        Imgproc.goodFeaturesToTrack( mat2, r, 0, 0.01,
                10, new Mat(), 4,
                false, 0.01 );
        //Shi-Tomasi corner detection
        Point[] corners = r.toArray();
        Arrays.sort( corners, ( x, y ) ->
                ( int ) ( ( x.x + x.y ) - ( y.x + y.y ) ) );
        return corners;
    }

    /**
     * Draws a line on the image
     *
     * @param p1        point 1
     * @param p2        point 2
     * @param thickness the thickness
     */
    public void line( Point p1, Point p2, int thickness )
    {
        Imgproc.line( mat, p1, p2, new Scalar( 30, 60, 240 ), thickness );
    }

    /**
     * Converts the color space to HLS (Hue, Luma, Saturation)
     *
     * @return the converted Mat
     */
    public Mat hls()
    {
        Mat r = new Mat();
        Imgproc.cvtColor( mat, r, Imgproc.COLOR_BGR2HLS );
        return r;
    }

    /**
     * Converts the Image to a WritableImage that's readable by JavaFX
     *
     * @return a WriteableImage object
     */
    public WritableImage getJfx()
    {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode( ".png", mat, mob );
        byte[] barr = mob.toArray();
        InputStream in = new ByteArrayInputStream( barr );
        BufferedImage bi = null;
        try
        {
            bi = ImageIO.read( in );
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        return SwingFXUtils.toFXImage( bi, null );
    }

    /**
     * returns the mat
     *
     * @return mat mat
     */
    public Mat getMat()
    {
        return mat;
    }

    /**
     * returns the original x
     *
     * @return ogMat og mat
     */
    public Mat getOgMat()
    {
        return ogMat;
    }

    /**
     * Gets x range
     *
     * @return the x
     */
    public int getX()
    {
        return mat.cols();
    }

    /**
     * Gets y range
     *
     * @return the y
     */
    public int getY()
    {
        return mat.rows();
    }
}
