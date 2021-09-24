package Jfx.Pages;

import Jfx.MazeGUI;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * A basic page with a title, body, image, and back button
 */
public class TextInfoPage
{

    /**
     * Gets a parent object based on a generic page design with a title, text,
     * and an image.
     *
     * @param titleText the title text
     * @param bodyText  the body text
     * @param imgWI     the image (set to null if you don't want one)
     * @param returnID  the return id
     * @return Parent object to set as root
     */
    public static Parent get( String titleText, String bodyText,
                              WritableImage imgWI, int returnID )
    {

        Button b = new Button();
        b.setText( "Back" );
        b.setOnAction( e -> MazeGUI.changeScene( returnID ) );

        Text title = new Text();
        title.setTextAlignment( TextAlignment.LEFT );
        title.setStyle( "-fx-font-size: 48; -fx-font-weight: bold;" );
        title.setText( titleText );

        Text body = new Text();
        body.setTextAlignment( TextAlignment.LEFT );
        body.setWrappingWidth( 600 );
        body.setStyle( "-fx-font-size: 16" );
        body.setText( "\n" + bodyText );

        VBox vb = new VBox( 3 );
        vb.getChildren().addAll( b, title, body );

        if ( imgWI != null )
        {
            vb.getChildren().add( new ImageView( imgWI ) );
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent( vb );
        sp.setPadding( new Insets( 30 ) );

        return sp;
    }

}
