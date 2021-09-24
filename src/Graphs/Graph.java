package Graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Graph.
 */
public class Graph
{
    private final Map<Integer, Vertex> vertices;
    private final Map<Integer, Set<Edge>> neighbors;
    private final int x;
    private final int y;

    /**
     * Instantiates a new Graph.
     *
     * @param xx the x
     * @param yy the y
     */
    public Graph( int xx, int yy )
    {
        vertices = new HashMap<>();
        neighbors = new HashMap<>();
        x = xx;
        y = yy;
    }

    /**
     * Neighbors list.
     *
     * @param v the v
     * @return the list
     */
    public List<Edge> neighbors( int v )
    {
        return new ArrayList<>( neighbors.get( v ) );
    }

    private List<Integer> vertices()
    {
        return new ArrayList<>( vertices.keySet() );
    }

    /**
     * Add vertex.
     *
     * @param v the vertex
     */
    public void addVertex( Vertex v )
    {
        if ( vertices.containsKey( v.getId() ) ) return;
        vertices.put( v.getId(), v );
        neighbors.put( v.getId(), new HashSet<>() );
    }

    /**
     * Add vertex.
     *
     * @param x the x
     * @param y the y
     */
    public void addVertex( int x, int y )
    {
        Vertex v = new Vertex( this, x, y );
        addVertex( v );
    }

    /**
     * Add a directed edge
     *
     * @param from the from ID
     * @param to   the to ID
     */
    public void addEdge( int from, int to )
    {
        if ( ! vertices.containsKey( from ) || ! vertices.containsKey( to ) )
            return;

        Set<Edge> edges = neighbors.get( from );
        edges.add( new Edge( from, to ) );
    }

    /**
     * Add an undirected edge. This is achieved by creating a directed edge
     * in each direction
     *
     * @param v1 vertex 1
     * @param v2 vertex 2
     */
    public void addUdEdge( int v1, int v2 )
    {
        addEdge( v1, v2 );
        addEdge( v2, v1 );
    }

    /**
     * Gets distance b/w 2 nodes using the pythagorean theorem
     *
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return the distance
     */
    public double getDist( int v1, int v2 )
    {
        return vertices.get( v1 ).distTo( vertices.get( v2 ) );
    }

    /**
     * Checks if 2 nodes are connected
     *
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return whether nodes are connected
     */
    public boolean connected( int v1, int v2 )
    {
        Set<Edge> edges = neighbors.get( v1 );
        return edges.stream()
                .map( Edge::to )
                .collect( Collectors.toList() )
                .contains( v2 );
    }

    /**
     * returns y
     *
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * returns x
     *
     * @return x
     */
    public int getX()
    {
        return x;
    }
}
