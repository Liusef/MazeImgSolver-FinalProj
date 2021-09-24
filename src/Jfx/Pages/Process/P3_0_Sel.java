package Jfx.Pages.Process;

import Jfx.MazeGUI;
import Jfx.Page;
import MazeImg.Image;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Base page to select a coordinate position on the image
 * No ID, as abstract classes cannot be instantiated
 */
public abstract class P3_0_Sel extends Page
{

    @Override
    public Parent getPage()
    {

        Text title = new Text();
        title.setStyle( "-fx-font-weight: bold; -fx-font-size: 24" );
        title.setWrappingWidth( 250 );
        title.setText( "Please select the " + pointType() +
                " point on the maze" );

        VBox vb = new VBox( 2 );

        Text coords = new Text();
        coords.setText( "X: " + 0 + "\n" + "Y: " + 0 );

        Text err = new Text();
        err.setFill( Color.web( "#FF3333" ) );
        err.setWrappingWidth( 300 );

        vb.getChildren().addAll( coords, err );

        VBox vb2 = new VBox( 2 );
        vb2.getChildren().addAll( title, vb );

        ImageView iv = new ImageView();
        iv.setImage( new Image(
                Image.invert( MazeGUI.getMaze()
                        .getSource()
                        .getMat() ) )
                .getJfx() );
        iv.setOnMouseMoved( e ->
        {
            coords.setText( "X: " + e.getX() + "\n" + "Y: " + e.getY() );
        } );
        iv.setOnMouseClicked( e ->
        {
            if ( isValid( ( int ) e.getX(), ( int ) e.getY() ) )
            {
                set( ( int ) e.getX(), ( int ) e.getY() );
                moveToNext();
            } else
            {
                err.setText( "Invalid point selected. Please select an area " +
                        "of the maze that isn't a wall." );
            }
        } );

        ScrollPane sp = new ScrollPane();
        sp.setContent( iv );

        BorderPane bp = new BorderPane();
        bp.setPadding( new Insets( 30 ) );
        bp.setCenter( sp );
        bp.setRight( vb2 );

        return bp;
    }

    private boolean isValid( int x, int y )
    {
        return MazeGUI.getMaze().getArr()[y][x] != null;
    }

    /**
     * Sets the value with the Maze object
     *
     * @param x x
     * @param y y
     */
    protected abstract void set( int x, int y );

    /**
     * Whether the type the user is pointing at the start, end, something else
     * or whatever
     *
     * @return the string
     */
    protected abstract String pointType();

    /**
     * Continue to the next page
     */
    protected abstract void moveToNext();
}
