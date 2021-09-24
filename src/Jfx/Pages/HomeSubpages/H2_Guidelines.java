package Jfx.Pages.HomeSubpages;

import Jfx.Page;
import Jfx.Pages.TextInfoPage;
import MazeImg.Image;
import javafx.scene.Parent;

/**
 * The page with the Image Guidelines
 * ID: 1
 */
public class H2_Guidelines extends Page
{
    @Override
    public Parent getPage()
    {
        String title = "Image Guidelines";
        String body = "The Maze input should be either rectangular or square." +
                "\n\nThe image itself should be monochrome or greyscale and " +
                "the maze walls should strongly contrast the background. The " +
                "walls in the maze image should also be somewhat thick, as " +
                "the program relies on thicker walls to determine what is a " +
                "wall. \n\nThe Image below is a good example of the image " +
                "should be like. ";
        return TextInfoPage.get( title, body,
                ( new Image( "resources/example.png" ) ).getJfx(), 0 );
    }
}
