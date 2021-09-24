package MazeImg;

import Graphs.AStarSolver;
import Graphs.Graph;
import Graphs.Vertex;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import java.util.List;

/**
 * The type Maze.
 */
public class Maze
{


    private Graph graph;
    private Vertex[][] arr;
    private Image source;
    private AStarSolver aStarSolver;

    /**
     * Instantiates a new Maze.
     */
    public Maze()
    {
    }

    /**
     * Instantiates a new Maze.
     *
     * @param g graph
     * @param v a 2d array with all the relevant vertexes
     * @param i the image object
     */
    public Maze( Graph g, Vertex[][] v, Image i )
    {
        graph = g;
        arr = v;
        source = i;
        aStarSolver = new AStarSolver( g );
    }

    /**
     * Generates a Maze based on an image object
     *
     * @param img  the image
     * @param cols #columns
     * @param rows #rows
     * @return A new maze object
     */
    public static Maze generate( Image img, int cols, int rows )
    {
        Vertex[][] arr = new Vertex[rows][cols];
        Graph graph = new Graph( cols, rows );
        img.writeImg( "mazes/wtf.png" );
        double wall = Image.medianLuma( img.getMat() ) / 255.0;
        Mat m = img.hls();

        for ( int r = 0; r < rows; r++ )
        {
            for ( int c = 0; c < cols; c++ )
            {
                if ( m.get( r, c )[1] < 20 )
                {
                    Vertex v = new Vertex( graph, c, r );
                    arr[r][c] = v;
                    graph.addVertex( v );
                    //Adds a vertex in any position that doesn't have a wall
                }
            }
        }

        for ( int r = 0; r < rows; r++ )
        {
            for ( int c = 0; c < cols; c++ )
            {
                if ( arr[r][c] != null )
                    // Checks all directions for nodes
                    // If node is found, an undirected edge is connected
                {
                    if ( r > 0 && arr[r - 1][c] != null )
                    {
                        graph.addUdEdge( arr[r][c].getId(),
                                arr[r - 1][c].getId() );
                    }
                    if ( c > 0 && arr[r][c - 1] != null )
                    {
                        graph.addUdEdge( arr[r][c].getId(),
                                arr[r][c - 1].getId() );
                    }
                    if ( r < arr.length - 1 && arr[r + 1][c] != null )
                    {
                        graph.addUdEdge( arr[r][c].getId(),
                                arr[r + 1][c].getId() );
                    }
                    if ( c < arr[0].length - 1 && arr[r][c + 1] != null )
                    {
                        graph.addUdEdge( arr[r][c].getId(),
                                arr[r][c + 1].getId() );
                    }
                }
            }
        }

        return new Maze( graph, arr, img );
    }

    /**
     * Solves the maze
     *
     * @param start the start vertex
     * @param end   the end vertex
     */
    public void solve( int start, int end )
    {
        aStarSolver.solve( start, end );
    }

    /**
     * Gets an array that contains the path of the solution
     *
     * @return the solution
     */
    public List<Integer> getSolution()
    {
        return aStarSolver.getSolution();
    }

    /**
     * Writes solution to the image
     *
     * @param thickness the thickness
     * @return the Image object with the maze solution drawn on it
     */
    public Image writeSolution( int thickness )
    {
        List<Integer> sol = aStarSolver.getSolution();
        int w = arr[0].length;
        for ( int i = 1; i < sol.size(); i++ )
        {
            int id0 = sol.get( i - 1 );
            int id = sol.get( i );
            Point p1 = new Point( ( id0 % w ), ( id0 / w ) );
            Point p2 = new Point( ( id % w ), ( id / w ) );
            source.line( p1, p2, thickness );
        }
        return source;
    }

    /**
     * Gets the status of the solution
     *
     * @return the status of the solver
     */
    public AStarSolver.Status status()
    {
        return aStarSolver.outcome();
    }

    /**
     * How many different possible positions were explored?
     *
     * @return # of positions / states explored
     */
    public int statesExplored()
    {
        return aStarSolver.totalExplored();
    }

    //=======================================================================


    /**
     * Gets the graph
     *
     * @return the graph
     */
    public Graph getGraph()
    {
        return graph;
    }

    /**
     * Sets graph.
     *
     * @param graph the graph
     */
    public void setGraph( Graph graph )
    {
        this.graph = graph;
    }

    /**
     * Get vertex array.
     *
     * @return the vertex array
     */
    public Vertex[][] getArr()
    {
        return arr;
    }

    /**
     * Sets vertex arr.
     *
     * @param arr the arr
     */
    public void setArr( Vertex[][] arr )
    {
        this.arr = arr;
    }

    /**
     * Gets source image
     *
     * @return the image
     */
    public Image getSource()
    {
        return source;
    }

    /**
     * Sets source image
     *
     * @param source the source image
     */
    public void setSource( Image source )
    {
        this.source = source;
    }


    /**
     * Gets the solver
     *
     * @return the solver
     */
    public AStarSolver getaStarSolver()
    {
        return aStarSolver;
    }
}
