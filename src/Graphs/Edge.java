package Graphs;

import java.util.Objects;

/**
 * The type Edge.
 */
public class Edge
{
    private final int v1;
    private final int v2;

    /**
     * Instantiates a new Edge.
     *
     * @param vertex1 vertex 1
     * @param vertex2 vertex 2
     */
    public Edge( int vertex1, int vertex2 )
    {
        v1 = vertex1;
        v2 = vertex2;
    }

    /**
     * From ID
     *
     * @return the ID
     */
    public int from()
    {
        return v1;
    }

    /**
     * To ID
     *
     * @return the ID
     */
    public int to()
    {
        return v2;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Edge edge = ( Edge ) o;
        return v1 == edge.v1 && v2 == edge.v2;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( v1, v2 );
    }
}
