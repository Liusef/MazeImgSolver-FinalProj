package Jfx.Pages.Process;

import Graphs.Vertex;
import Jfx.MazeGUI;

/**
 * The page where the user selects the start node
 * ID: 31
 */
public class P3_1_SelStart extends P3_0_Sel
{
    @Override
    protected void set( int x, int y )
    {
        MazeGUI.setStart( Vertex.convertID( x, y,
                MazeGUI.getMaze().getSource().getX() ) );
    }

    @Override
    protected String pointType()
    {
        return "Start";
    }

    @Override
    protected void moveToNext()
    {
        MazeGUI.changeScene( 32 );
    }
}
