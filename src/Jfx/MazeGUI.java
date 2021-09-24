package Jfx;

import Jfx.Pages.HomePage;
import Jfx.Pages.HomeSubpages.H2_Guidelines;
import Jfx.Pages.HomeSubpages.H3_ProjectInfo;
import Jfx.Pages.Oops;
import Jfx.Pages.Process.*;
import MazeImg.Maze;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main MazeGUI class
 */
public class MazeGUI extends Application
{

    private static Stage stage;
    private static Scene scene;
    private static Maze maze;
    private static int start;
    private static int end;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main( String[] args )
    {
        launch( args );
    }


    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        maze = new Maze();
        scene = new Scene( choosePage( 0 ).getPage(), 800, 600 );
        stage = primaryStage;
        setTitle( "Pathfinder GUI" );
        stage.setScene( scene );
        stage.show();

    }

    /**
     * Changes the current scene based on ID
     *
     * @param id the id
     */
    public static void changeScene( int id )
    {
        scene.setRoot( choosePage( id ).getPage() );
    }

    private static Page choosePage( int id )
    {
        return switch ( id )
                {
                    case 0 -> new HomePage();
                    case 1 -> new H2_Guidelines();
                    case 2 -> new H3_ProjectInfo();
                    case 10 -> new P1_0_SelectImg();
                    case 11 -> new P1_1_ConfirmImg();
                    case 20 -> new P2_0_CropCheck();
                    case 21 -> new P2_1_BinCropConfirmation();
                    case 31 -> new P3_1_SelStart();
                    case 32 -> new P3_2_SelEnd();
                    case 40 -> new P4_0_Result();
                    default -> new Oops();
                };
    }

    /**
     * Quit.
     */
    public static void quit()
    {
        Platform.exit();
    }

    /**
     * returns start
     *
     * @return start start
     */
    public static int getStart()
    {
        return start;
    }

    /**
     * Sets start.
     *
     * @param s the start
     */
    public static void setStart( int s )
    {
        start = s;
    }

    /**
     * returns end
     *
     * @return end end
     */
    public static int getEnd()
    {
        return end;
    }

    /**
     * Sets end
     *
     * @param e the end
     */
    public static void setEnd( int e )
    {
        end = e;
    }

    /**
     * Gets stage.
     *
     * @return the stage
     */
    public static Stage getStage()
    {
        return stage;
    }

    /**
     * Gets maze.
     *
     * @return the maze
     */
    public static Maze getMaze()
    {
        return maze;
    }

    /**
     * Sets maze.
     *
     * @param m the m
     */
    public static void setMaze( Maze m )
    {
        maze = m;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public static void setTitle( String title )
    {
        stage.setTitle( title );
    }
}
