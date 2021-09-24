package Jfx.Pages.Process;

import Jfx.MazeGUI;
import Jfx.Page;
import MazeImg.Maze;
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

/**
 * Page 1 of the GUI (confirmation of the image you uploaded)
 * ID: 11
 */
public class P1_1_ConfirmImg extends Page
{
    @Override
    public Parent getPage()
    {

        ImageView iv = new ImageView( MazeGUI.getMaze().getSource().getJfx() );
        iv.setFitHeight( 400 );
        iv.setFitHeight( 400 );
        iv.setPreserveRatio( true );

        Text ays = new Text();
        ays.setTextAlignment( TextAlignment.CENTER );
        ays.setText( "\nIs this correct?" );
        ays.setStyle( "-fx-font-size: 16; -fx-font-weight: bold" );

        Button y = new Button();
        y.setPrefWidth( 100 );
        y.setText( "Yes" );
        y.setOnAction( e ->
        {
            MazeGUI.changeScene( 20 );
        } );

        Button n = new Button();
        n.setText( "No" );
        n.setPrefWidth( 100 );
        n.setOnAction( e ->
        {
            MazeGUI.changeScene( 10 );
            MazeGUI.setMaze( new Maze() );
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
}
