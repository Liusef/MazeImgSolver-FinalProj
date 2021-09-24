package Jfx.Pages.Process;

import Jfx.MazeGUI;
import Jfx.Page;
import MazeImg.Image;
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
 * Asks the user if the image crop is correct, puts them back to the start
 * screen if not
 * ID: 21
 */
public class P2_1_BinCropConfirmation extends Page
{
    @Override
    public Parent getPage()
    {
        // Clones the image mat that's binarized and cropped
        ImageView iv = new ImageView( new Image(
                Image.invert( MazeGUI.getMaze().getSource().getMat().clone() ) )
                .getJfx() );
        iv.setFitHeight( 400 );
        iv.setFitHeight( 400 );
        iv.setPreserveRatio( true );

        Text ays = new Text();
        ays.setTextAlignment( TextAlignment.CENTER );
        ays.setText( "\nDoes this binarized and cropped image look correct? " +
                "If it doesn't, please select another image" );
        ays.setWrappingWidth( 750 );
        ays.setStyle( "-fx-font-size: 16; -fx-font-weight: bold" );

        Button y = new Button();
        y.setPrefWidth( 100 );
        y.setText( "Yes" );
        y.setOnAction( e ->
        {
            MazeGUI.setMaze( Maze.generate( MazeGUI.getMaze().getSource(),
                    MazeGUI.getMaze().getSource().getX(),
                    MazeGUI.getMaze().getSource().getY() ) );
            MazeGUI.changeScene( 31 );
        } );

        // New Code (9/21)
        Button fuck = new Button();
        fuck.setPrefWidth( 100 );
        fuck.setText( "Fuck" );
        fuck.setOnAction( e ->{
            MazeGUI.setMaze( Maze.generate(
                    new Image(Image.invert(
                            MazeGUI.getMaze().getSource().getMat())),
                    MazeGUI.getMaze().getSource().getX(),
                    MazeGUI.getMaze().getSource().getY() ) );
            MazeGUI.changeScene( 31 );
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
        hb.getChildren().addAll( y, n, fuck );

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
