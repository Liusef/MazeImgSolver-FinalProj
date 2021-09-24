package Jfx.Pages.Process;

import Jfx.MazeGUI;
import Jfx.Page;
import MazeImg.Image;
import MazeImg.Maze;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * The result page with the path drawn on and some statistics
 * ID: 40
 */
public class P4_0_Result extends Page
{
    @Override
    public Parent getPage()
    {
        MazeGUI.getMaze().setSource( new Image( Image.invert(
                MazeGUI.getMaze().getSource().getMat() ) ) );
        Image ia = MazeGUI.getMaze().writeSolution( 3 );

        ImageView iv = new ImageView( ia.getJfx() );
        iv.setFitHeight( 500 );
        iv.setFitHeight( 500 );
        iv.setPreserveRatio( true );

        Text done = new Text();
        done.setStyle( "-fx-font-size: 32; -fx-font-weight: bold" );
        done.setText( "Done!" );

        Text stats = new Text();
        stats.setText( "Statistics: " +
                "\nOutcome: " + MazeGUI.getMaze().getaStarSolver().outcome() +
                "\nTotal states explored: " + MazeGUI.getMaze()
                .getaStarSolver().totalExplored() +
                "\nPath distance (px): " + MazeGUI.getMaze()
                .getaStarSolver().totalDist() );

        Button b = new Button();
        b.setText( "Save Image to system" );
        b.setOnAction( e ->
        {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter( "PNG", "*.png" )
            );

            File save = fc.showSaveDialog( MazeGUI.getStage() );

            if ( save == null ) return;

            ia.writeImg( save.getPath() );
        } );

        Button home = new Button();
        home.setText( "Start over" );
        home.setOnAction( e ->
        {
            MazeGUI.setMaze( new Maze() );
            MazeGUI.changeScene( 0 );
        } );

        HBox hb = new HBox();
        hb.getChildren().addAll( b, home );

        VBox vb = new VBox( 3 );
        vb.getChildren().addAll( done, stats, hb );

        BorderPane bp = new BorderPane();
        bp.setPadding( new Insets( 30 ) );
        bp.setCenter( iv );
        bp.setRight( vb );

        return bp;
    }
}
