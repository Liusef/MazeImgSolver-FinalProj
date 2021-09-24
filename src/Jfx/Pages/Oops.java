package Jfx.Pages;

import Jfx.MazeGUI;
import Jfx.Page;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * The page for if you go to a page that doesn't exist
 */
public class Oops extends Page
{
    @Override
    public Parent getPage()
    {
        Text t = new Text();
        t.setText( "ya goofed bud" );
        Button b = new Button();
        b.setText( "exit" );
        b.setOnAction( e -> MazeGUI.quit() );
        GridPane p = new GridPane();
        p.setPadding( new Insets( 30 ) );
        p.add( t, 0, 0 );
        p.add( b, 0, 1 );
        return p;
    }
}
