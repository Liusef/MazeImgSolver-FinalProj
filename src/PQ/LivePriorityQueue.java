package PQ;

/**
 * Implements a priority queue using a minheap, priority values are updatable
 *
 * @param <T> the type parameter
 */
public class LivePriorityQueue<T>
{

    private final MinHeap<PriorityItem> heap;

    /**
     * Instantiates a new Live priority queue.
     */
    public LivePriorityQueue()
    {
        heap = new MinHeap<PriorityItem>();
    }

    /**
     * Peeks for the minimum
     *
     * @return the minimum
     */
    public T peek()
    {
        return heap.findMin().item();
    }

    /**
     * Inserts a new element
     *
     * @param item          the item
     * @param priorityValue the priority value
     */
    public void insert( T item, double priorityValue )
    {
        PriorityItem a = new PriorityItem( item, priorityValue );
        if ( heap.contains( a ) ) throw new IllegalArgumentException();
        heap.insert( a );
    }

    /**
     * Poll the queue
     *
     * @return the minimum element
     */
    public T poll()
    {
        return heap.removeMin().item;
    }

    /**
     * Changes the priority of an element
     *
     * @param item          the item
     * @param priorityValue the priority value
     */
    public void changePriority( T item, double priorityValue )
    {
        heap.update( new PriorityItem( item, priorityValue ) );
    }

    /**
     * Size of the heap
     *
     * @return the size
     */
    public int size()
    {
        return heap.size();
    }

    /**
     * Sees if the queue contains an element
     *
     * @param item the item
     * @return whether the elements are contained
     */
    public boolean contains( T item )
    {
        return heap.contains( new PriorityItem( item, 0 ) );
    }

    @Override
    public String toString()
    {
        return heap.toString();
    }

    /**
     * The type Priority item.
     */
    public class PriorityItem implements Comparable<PriorityItem>
    {
        private final T item;
        private final double priorityValue;

        private PriorityItem( T item, double priorityValue )
        {
            this.item = item;
            this.priorityValue = priorityValue;
        }

        /**
         * Gets an item
         *
         * @return the item
         */
        public T item()
        {
            return this.item;
        }

        /**
         * Gets the priority value of an item
         *
         * @return the priority value
         */
        public double priorityValue()
        {
            return this.priorityValue;
        }

        @Override
        public String toString()
        {
            return "(PriorityItem: " + this.item.toString() + ", "
                    + this.priorityValue + ")";
        }

        @Override
        public int compareTo( PriorityItem o )
        {
            double diff = this.priorityValue - o.priorityValue;
            if ( diff > 0 )
            {
                return 1;
            } else if ( diff < 0 )
            {
                return - 1;
            } else
            {
                return 0;
            }
        }

        @Override
        public boolean equals( Object o )
        {
            if ( o == null )
            {
                return false;
            } else if ( getClass() == o.getClass() )
            {
                PriorityItem p = ( PriorityItem ) o;
                return p.item.equals( item );
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            return item.hashCode();
        }
    }
}
