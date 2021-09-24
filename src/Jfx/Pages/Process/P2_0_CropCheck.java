package Jfx.Pages.Process;

import Jfx.MazeGUI;
import Jfx.Page;
import MazeImg.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.opencv.core.Point;

/**
 * Asks the user if the image needs to be cropped or not
 * ID: 20
 */
public class P2_0_CropCheck extends Page
{

    @Override
    public Parent getPage()
    {
        // Image preview of the input image
        ImageView iv = new ImageView( MazeGUI.getMaze().getSource().getJfx() );
        iv.setFitHeight( 400 );
        iv.setFitHeight( 400 );
        iv.setPreserveRatio( true );

        Text ays = new Text();
        ays.setTextAlignment( TextAlignment.CENTER );
        ays.setText( "\nDoes your image have any extra space, padding, " +
                "or margins on the outside?" );
        ays.setWrappingWidth( 700 );
        ays.setStyle( "-fx-font-size: 16; -fx-font-weight: bold" );

        Button y = new Button();
        y.setPrefWidth( 100 );
        y.setText( "Yes" );
        y.setOnAction( e ->
        {
            prep();
            Image ia = MazeGUI.getMaze().getSource();
            Point[] corners = ia.findCorners();
            ia.crop( corners[0], corners[corners.length - 1] );
            MazeGUI.changeScene( 21 );
        } );

        Button n = new Button();
        n.setText( "No" );
        n.setPrefWidth( 100 );
        n.setOnAction( e ->
        {
            prep();
            MazeGUI.changeScene( 21 );
        } );

        HBox hb = new HBox( 2 );
        hb.setAlignment( Pos.CENTER );
        hb.getChildren().addAll( y, n );

        VBox vb = new VBox( 2 );
        vb.setAlignment( Pos.CENTER );
        vb.getChildren().addAll( ays, hb );

        BorderPane bp = new BorderPane();
        bp.setPadding( new Insets( 30 ) );
        bp.setCenter( iv );
        bp.setBottom( vb );

        return bp;
    }

    private void prep()
    {
        Image ia = MazeGUI.getMaze().getSource();
        ia.removeTransparency();
        ia.binarizeThresh( ( int ) ( 255 - Image.medianLuma( ia.getMat() ) ) );
    }
}
