package Jfx.Pages;

import Jfx.MazeGUI;
import Jfx.Page;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The Homepage of the program
 * ID: 0
 */
public class HomePage extends Page
{

    @Override
    public Parent getPage()
    {

        Text title = new Text();
        title.setTextAlignment( TextAlignment.CENTER );
        title.setWrappingWidth( 600 );
        title.setStyle( "-fx-font-size: 48; -fx-font-weight: bold;" );
        title.setText( "Pathfinder GUI" );

        Text desc = new Text();
        desc.setTextAlignment( TextAlignment.CENTER );
        desc.setWrappingWidth( 600 );
        desc.setStyle( "-fx-font-size: 16;" );
        desc.setText( "Welcome to the maze solver! While somewhat limited, " +
                "this program can solve mazes that you put into it! " +
                "Please ensure that any maze images you input follow the " +
                "guidelines!" );

        Button b1 = new Button();
        b1.setText( "Begin Pathfinding!" );
        b1.setOnAction( e -> MazeGUI.changeScene( 10 ) );

        Button b2 = new Button();
        b2.setText( "Image Guidelines" );
        b2.setOnAction( e -> MazeGUI.changeScene( 1 ) );

        Button b3 = new Button();
        b3.setText( "Project Information" );
        b3.setOnAction( e -> MazeGUI.changeScene( 2 ) );

        Button b4 = new Button();
        b4.setText( "Quit Program" );
        b4.setOnAction( e -> MazeGUI.quit() );

        ButtonBar bb = new ButtonBar();
        ButtonBar.setButtonData( b1, ButtonBar.ButtonData.LEFT );
        ButtonBar.setButtonData( b2, ButtonBar.ButtonData.OTHER );
        ButtonBar.setButtonData( b3, ButtonBar.ButtonData.OTHER );
        ButtonBar.setButtonData( b4, ButtonBar.ButtonData.CANCEL_CLOSE );
        bb.getButtons().addAll( b1, b2, b3, b4 );

        GridPane gp = new GridPane();
        gp.setAlignment( Pos.CENTER );
        gp.setHgap( 16 );
        gp.setVgap( 16 );
        gp.setPadding( new Insets( 30 ) );

        gp.add( title, 0, 0, 1, 1 );
        gp.add( desc, 0, 1, 1, 1 );
        gp.add( bb, 0, 6, 1, 1 );

        return gp;
    }
}
