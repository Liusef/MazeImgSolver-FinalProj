package Graphs;

import PQ.LivePriorityQueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The A* Solver
 */
public class AStarSolver
{

    private final Graph graph;
    private final List<Integer> solution;
    private final LivePriorityQueue<Integer> PQ;
    private final Map<Integer, Double> distTo;
    private final Map<Integer, Integer> predecessorMap;
    private int explored;
    private int start;
    private int end;
    private Status status;

    /**
     * Instantiates a new A star solver.
     *
     * @param g the graph
     */
    public AStarSolver( Graph g )
    {
        graph = g;
        solution = new LinkedList<>();
        PQ = new LivePriorityQueue<>();
        distTo = new HashMap<>();
        predecessorMap = new HashMap<>();
        int explored = 0;
        status = Status.NOT_STARTED;
    }

    /**
     * Solves
     *
     * @param startID the start id
     * @param endID   the end id
     */
    public void solve( int startID, int endID )
    {
        status = Status.INCOMPLETE;
        start = startID;
        end = endID;

        //Add the starting node to the search PQ
        PQ.insert( start, graph.getDist( start, end ) );
        distTo.put( start, 0.0 );

        while ( true )
        {
            if ( PQ.size() == 0 )
            {
                // If the PQ becomes empty, and it never finishes,
                // there's no solution
                status = Status.UNSOLVABLE;
                break;
            }
            if ( PQ.peek().equals( end ) )
            {
                // If the PQ's next item is the solution, A* has reached the end
                status = Status.SUCCESSFUL;
                break;
            }
            // PQ is polled to search the next most optimal node
            int p = PQ.poll();
            explored++; // Iterate number of nodes searched (statistic)
            for ( Edge e : graph.neighbors( p ) )
            {
                // Relaxing updates the values in the maps and the PQ so
                // A* knows where to search
                relax( e, end );
            }
        }

        if ( status == Status.SUCCESSFUL )
        {
            // Goes back from the solution to the start to get the most optimal
            // solution
            solution.add( end );
            int predecessor = predecessorMap.get( end );
            while ( predecessor != start )
            {
                solution.add( 0, predecessor );
                predecessor = predecessorMap.get( predecessor );
            }
            solution.add( 0, predecessor );
        }
    }

    private void relax( Edge e, int end )
    {
        int predecessorNode = e.from();
        int queuedNode = e.to();
        if ( ! distTo.containsKey( queuedNode ) || distTo.get( predecessorNode )
                + 1 < distTo.get( queuedNode ) )
        {
            // Updates values if a more optimal path is found or node hasn't
            // been previously discovered
            distTo.put( queuedNode, distTo.get( predecessorNode ) + 1 );
            predecessorMap.put( queuedNode, predecessorNode );
            if ( PQ.contains( queuedNode ) )
            { // Updates priority of the node in the PQ if a more optimal path
                // is found
                PQ.changePriority( queuedNode,
                        distTo.get( queuedNode ) +
                                graph.getDist( queuedNode, end ) );
            } else
            { //adds node to the PQ if it wasn't previously there
                PQ.insert( queuedNode, distTo.get( queuedNode ) +
                        graph.getDist( queuedNode, end ) );
            }
        }
    }

    /**
     * Outcome status.
     *
     * @return the status
     */
    public Status outcome()
    {
        return status;
    }

    /**
     * Gets solution.
     *
     * @return the solution
     */
    public List<Integer> getSolution()
    {
        return solution;
    }

    /**
     * Total distance
     *
     * @return the distance
     */
    public double totalDist()
    {
        if ( status != Status.SUCCESSFUL )
        {
            return - 1;
        }
        return distTo.get( this.end );
    }

    /**
     * Total vertices explored
     *
     * @return the number of vertices
     */
    public int totalExplored()
    {
        return explored;
    }

    /**
     * The enum Status.
     */
    public enum Status
    {
        /**
         * Not started status.
         */
        NOT_STARTED,
        /**
         * Incomplete status.
         */
        INCOMPLETE,
        /**
         * Successful status.
         */
        SUCCESSFUL,
        /**
         * Unsolvable status.
         */
        UNSOLVABLE,
        /**
         * Timeout status.
         */
        TIMEOUT,
        /**
         * Unsuccessful unknown status.
         */
        UNSUCCESSFUL_UNKNOWN
    }
}
