package Testing;

import Graphs.Vertex;
import MazeImg.Image;
import MazeImg.Maze;
import org.opencv.core.Point;

import java.util.List;

/**
 * Test class essentially
 */
public class DMain
{

    public static void main( String[] args )
    {
//        gridTester("mazes/mazetcrop.png");
//        cropTester("mazes/amungus.jpg");
        theWholeShabang( "mazes/amungus.jpg", false );

    }

    /**
     * Runs the whole thing in 1 whole shabang
     *
     * @param path      Path of the source image
     * @param isCropped Is your source image cropped?
     */
    public static void theWholeShabang( String path, boolean isCropped )
    {
        Image ia = new Image( path );
        ia.removeTransparency();
        System.out.println( ia.getMat() );
        ia.binarizeThresh( ( int ) ( 255 - Image.medianLuma( ia.getMat() ) ) );

        if ( ! isCropped )
        {
            Point[] corners = ia.findCorners();
            ia.crop( corners[0], corners[corners.length - 1] );
        }
        ia.writeImg( "mazes/crop.png" );
//        double rowdist =   (MathUtils.avgDistBw(MathUtils.findLocalMax(
//                MathUtils.movingAvg(ia.lumaRow(), 12, true))));
//        double coldist =   (MathUtils.avgDistBw(MathUtils.findLocalMax(
//                MathUtils.movingAvg(ia.lumaCol(), 12, true))));
//        int rows = (int) (((ia.getY() + 0.0) / rowdist) + 0.5);
//        int cols = (int) (((ia.getX() + 0.0) / coldist) + 0.5);
        int rows = ia.getY();
        int cols = ia.getX();

        System.out.println( "#Rows: " + rows );
        System.out.println( "#Cols: " + cols );
        System.out.println();

        Maze m = Maze.generate( ia, cols, rows );
        m.solve( Vertex.convertID( 762, 1141, cols ), Vertex.convertID( 811, 1147, cols ) ); // amungus.jpg
        // m.solve(Vertex.convertID(10, 0, cols),Vertex.convertID(695, 704, cols) ); // mazet.png
        //m.solve(Vertex.convertID(0, 33, cols), Vertex.convertID(744, 679, cols)); //maze2.jpg
        // m.solve(Vertex.convertID(287, 587, cols), Vertex.convertID(300, 587, cols)); //maze3.jpg
        List<Integer> sol = m.getSolution();
        for ( int i : sol )
        {
            System.out.println( "[ " + i % cols + ", " + i / cols + "]" );
        }
        m.writeSolution( 3 );
        m.getSource().writeImg( "mazes/dididoit.png" );

        System.out.println( "\n\nOutcome: " + m.status() );
        System.out.println( "Total States Explored: " + m.statesExplored() );
    }


}


