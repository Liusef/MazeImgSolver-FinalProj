package Jfx.Pages.HomeSubpages;

import Jfx.Page;
import Jfx.Pages.TextInfoPage;
import MazeImg.Image;
import javafx.scene.Parent;

/**
 * The page with some Project Info
 * ID: 2
 */
public class H3_ProjectInfo extends Page
{
    @Override
    public Parent getPage()
    {
        String title = "Project Info";
        String body = "Hello!\n\n" +
                "This program was created by Joseph Liu\n\n" +
                "Libraries used include OpenCV, JavaFX\n\n";
        return TextInfoPage.get( title, body,
                ( new Image( "resources/me.jpg" ) ).getJfx(), 0 );
    }
}
