package Graphs;

import java.util.Objects;

/**
 * The type Vertex.
 */
public class Vertex
{

    private Graph graph;
    private int id;

    /**
     * Instantiates a new Vertex.
     *
     * @param g the graph
     * @param i the ID
     */
    public Vertex( Graph g, int i )
    {
        graph = g;
        id = i;
    }

    /**
     * Instantiates a new Vertex.
     *
     * @param g the graph
     * @param x the x
     * @param y the y
     */
    public Vertex( Graph g, int x, int y )
    {
        graph = g;
        id = ( y * g.getX() ) + x;
    }

    /**
     * Converts coordinates to a vertex ID
     *
     * @param x     the x
     * @param y     the y
     * @param width the width
     * @return the ID
     */
    public static int convertID( int x, int y, int width )
    {
        return ( y * width ) + x;
    }

    /**
     * Sets graph.
     *
     * @param g the graph
     * @param x the x
     * @param y the y
     */
    public void setGraph( Graph g, int x, int y )
    {
        graph = g;
        id = ( y * g.getX() ) + x;
    }

    /**
     * returns id
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * returns graph associated with this vertex
     *
     * @return graph graph
     */
    public Graph getGraph()
    {
        return graph;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX()
    {
        return id % graph.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY()
    {
        return id / graph.getX();
    }

    /**
     * Distance between nodes
     *
     * @param to Node to
     * @return the distance
     */
    public double distTo( Vertex to )
    {
        int dx = to.getX() - getX();
        int dy = to.getY() - getY();
        return Math.sqrt( ( dx * dx ) + ( dy * dy ) );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Vertex vertex = ( Vertex ) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
