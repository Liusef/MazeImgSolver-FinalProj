package Jfx.Pages.Process;

import Graphs.Vertex;
import Jfx.MazeGUI;

/**
 * The page where the user selects the ending node
 * ID: 32
 */
public class P3_2_SelEnd extends P3_0_Sel
{
    @Override
    protected void set( int x, int y )
    {
        MazeGUI.setEnd( Vertex.convertID( x, y,
                MazeGUI.getMaze().getSource().getX() ) );
    }

    @Override
    protected String pointType()
    {
        return "End";
    }

    @Override
    protected void moveToNext()
    {
        MazeGUI.getMaze().solve( MazeGUI.getStart(), MazeGUI.getEnd() );
        MazeGUI.changeScene( 40 );
    }
}
