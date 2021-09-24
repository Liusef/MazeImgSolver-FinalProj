package PQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Implements a minimum heap data structure backed by an arraylist
 *
 * @param <E> the type parameter
 */
public class MinHeap<E extends Comparable<E>>
{


    private final ArrayList<E> contents;
    private int size;
    private final HashMap<E, Integer> itemToIndex;

    /**
     * Instantiates a new Min heap.
     */
    public MinHeap()
    {
        contents = new ArrayList<>();
        contents.add( null );
        itemToIndex = new HashMap<>();
    }

    private E getElement( int index )
    {
        if ( index >= contents.size() )
        {
            return null;
        } else
        {
            return contents.get( index );
        }
    }

    private void setElement( int index, E element )
    {
        while ( index >= contents.size() )
        {
            contents.add( null );
        }
        contents.set( index, element );
        itemToIndex.put( element, index );
    }

    private void swap( int index1, int index2 )
    {
        E element1 = getElement( index1 );
        E element2 = getElement( index2 );
        setElement( index2, element1 );
        setElement( index1, element2 );
    }

    @Override
    public String toString()
    {
        return toStringHelper( 1, "" );
    }

    private String toStringHelper( int index, String soFar )
    {
        if ( getElement( index ) == null )
        {
            return "";
        } else
        {
            String toReturn = "";
            int rightChild = getRightOf( index );
            toReturn += toStringHelper( rightChild, "        " + soFar );
            if ( getElement( rightChild ) != null )
            {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement( index ) + "\n";
            int leftChild = getLeftOf( index );
            if ( getElement( leftChild ) != null )
            {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper( leftChild, "        " + soFar );
            return toReturn;
        }
    }

    private int getLeftOf( int index )
    {
        return index * 2;
    }

    private int getRightOf( int index )
    {
        return ( index ) * 2 + 1;
    }

    private int getParentOf( int index )
    {
        return ( index / 2 );
    }

    private int min( int index1, int index2 )
    {
        if ( contents.get( index1 ) == null )
        {
            return index2;
        } else if ( contents.get( index2 ) == null )
        {
            return index1;
        } else if ( contents.get( index1 ).compareTo(
                contents.get( index2 ) ) < 0 )
        {
            return index1;
        }
        return index2;
    }

    /**
     * Finds smallest element in the heap
     *
     * @return the smallest element
     */
    public E findMin()
    {
        return contents.get( 1 );
    }

    private void bubbleUp( int index )
    {
        while ( index != 1 && getElement( getParentOf( index ) )
                .compareTo( getElement( index ) ) > 0 )
        {
            swap( getParentOf( index ), index );
            index = index / 2;
        }
    }

    private void bubbleDown( int index )
    {
        while ( true )
        {
            if ( getElement( getLeftOf( index ) ) != null &&
                    getElement( getRightOf( index ) ) != null &&
                    getElement( index )
                            .compareTo( getElement(
                                    getLeftOf( index ) ) ) > 0 &&
                    getElement( index ).compareTo(
                            getElement( getRightOf( index ) ) ) > 0 )
            {
                int next = min( getLeftOf( index ), getRightOf( index ) );
                swap( index, next );
                index = next;
            } else if ( getElement( getLeftOf( index ) ) != null &&
                    getElement( index )
                            .compareTo( getElement( getLeftOf( index ) ) ) > 0 )
            {
                int next = getLeftOf( index );
                swap( index, next );
                index = next;
            } else if ( ( getElement( getRightOf( index ) ) != null &&
                    getElement( index )
                            .compareTo( getElement(
                                    getRightOf( index ) ) ) > 0 ) )
            {
                int next = getRightOf( index );
                swap( index, next );
                index = next;
            } else
            {
                break;
            }
        }
    }

    /**
     * Size int.
     *
     * @return the size
     */
    public int size()
    {
        return contents.size() - 1;
    }

    /**
     * Insert.
     *
     * @param element the element
     */
    public void insert( E element )
    {
        if ( itemToIndex.containsKey( element ) )
        {
            throw new IllegalArgumentException();
        }
        contents.add( element );
        itemToIndex.put( element, contents.size() - 1 );
        bubbleUp( contents.size() - 1 );
    }

    /**
     * Remove min element
     *
     * @return the minimum
     */
    public E removeMin()
    {
        E min = getElement( 1 );
        int lastIndex = contents.size() - 1;
        swap( 1, lastIndex );
        contents.remove( lastIndex );
        itemToIndex.remove( min );
        bubbleDown( 1 );
        return min;
    }

    /**
     * Updates the position of an element
     *
     * @param element the element
     */
    public void update( E element )
    {
        if ( ! itemToIndex.containsKey( element ) )
        {
            throw new NoSuchElementException();
        }
        int index = itemToIndex.get( element );
        E elementBefore = contents.get( index );
        itemToIndex.remove( elementBefore );
        contents.set( index, element );
        itemToIndex.put( element, index );
        if ( element.compareTo( elementBefore ) > 0 )
        {
            bubbleDown( index );
        } else if ( element.compareTo( elementBefore ) < 0 )
        {
            bubbleUp( index );
        }
    }

    /**
     * Checks if an element is in the heap
     *
     * @param element the element
     * @return whether the heap contains the element
     */
    public boolean contains( E element )
    {
        return itemToIndex.containsKey( element );
    }

}
