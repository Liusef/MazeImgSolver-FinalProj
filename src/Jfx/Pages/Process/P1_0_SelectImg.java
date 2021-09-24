package Jfx.Pages.Process;

import Jfx.MazeGUI;
import Jfx.Page;
import MazeImg.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Page 1 of the GUI
 * ID: 10
 */
public class P1_0_SelectImg extends Page
{
    @Override
    public Parent getPage()
    {
        Text title = new Text();
        title.setTextAlignment( TextAlignment.CENTER );
        title.setWrappingWidth( 600 );
        title.setStyle( "-fx-font-size: 48; -fx-font-weight: bold;" );
        title.setText( "Choose a maze to solve" );

        Text desc = new Text();
        desc.setTextAlignment( TextAlignment.CENTER );
        desc.setWrappingWidth( 600 );
        desc.setStyle( "-fx-font-size: 16;" );
        desc.setText( "Please choose a maze image " +
                "that follows the guidelines!" );

        Button b1 = new Button();
        b1.setText( "Choose file" );
        b1.setOnAction( e ->
        {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter( "Images",
                            "*.jpg", "*.jpeg", "*.png" )
            );

            File file = fc.showOpenDialog( MazeGUI.getStage() );

            if ( file == null ) return;

            MazeGUI.getMaze().setSource( new Image( file.getPath() ) );
            MazeGUI.changeScene( 11 );
        } );

        VBox vb = new VBox(); // A VBox is used to center the element
        vb.setAlignment( Pos.CENTER );
        vb.getChildren().add( b1 );

        GridPane gp = new GridPane();
        gp.setAlignment( Pos.CENTER );
        gp.setHgap( 16 );
        gp.setVgap( 16 );
        gp.setPadding( new Insets( 30 ) );

        gp.add( title, 0, 0, 1, 1 );
        gp.add( desc, 0, 1, 1, 1 );
        gp.add( vb, 0, 3, 1, 1 );

        return gp;
    }
}
