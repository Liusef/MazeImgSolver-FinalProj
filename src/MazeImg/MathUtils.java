package MazeImg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Math utils.
 */
public class MathUtils
{

    /**
     * Find local max array list.
     *
     * @param arr the arr
     * @return the array list
     */
    public static ArrayList<Integer> findLocalMax( double[] arr )
    {
        ArrayList<Integer> lmax = new ArrayList<>();
        if ( arr[0] > arr[1] ) lmax.add( 0 );

        for ( int i = 1; i < arr.length - 1; i++ )
        {
            if ( arr[i - 1] < arr[i] &&
                    arr[i] > arr[i + 1] ) lmax.add( i );
        }

        if ( arr[arr.length - 1] > arr[arr.length - 2] )
            lmax.add( arr.length - 1 );

        return lmax;
    }

    /**
     * Moving avg double [ ].
     *
     * @param arr     the arr
     * @param c       the c
     * @param adjEnds the adj ends
     * @return the double [ ]
     */
    public static double[] movingAvg( double[] arr, int c, boolean adjEnds )
    {
        double[] r = new double[arr.length];
        double d = 0;
        for ( int i = 0; i < arr.length; i++ )
        {
            for ( int a = i - ( c / 2 ); a <= i + ( c / 2 ); a++ )
            {
                if ( a >= 0 && a < arr.length )
                {
                    d += arr[a];
                }
            }
            d = adjEnds && i > ( c / 2 ) && i < ( arr.length ) - ( c / 2 ) ?
                    ( i > arr.length / 2 ? ( d / ( c + ( arr.length -
                            ( i + ( c / 2.0 ) ) ) ) ) :
                            ( d / ( i + ( c / 2.0 ) ) ) ) :
                    ( d + 0.0 ) / c;
            r[i] = d;
            d = 0;
        }
        return r;
    }

    /**
     * Avg dist bw double.
     *
     * @param l the l
     * @return the double
     */
    public static double avgDistBw( List<Integer> l )
    {
        List<Double> delta = new ArrayList<>();
        for ( int i = l.size() - 1; i > 0; i-- )
            delta.add( 0, l.get( i ) - l.get( i - 1 ) + 0.0 );
        return ( median( delta ) );
    }

    /**
     * Median double.
     *
     * @param l the l
     * @return the double
     */
    public static double median( List<Double> l )
    {
        Collections.sort( l );
        System.out.println( l );
        return l.size() % 2 == 1 ? l.get( l.size() / 2 ) :
                ( ( l.get( l.size() / 2 ) + l.get( l.size() / 2 + 1 ) ) / 2 );
    }

    /**
     * Iqr mean double.
     *
     * @param l the l
     * @return the double
     */
    public static double IQRMean( List<Double> l )
    {
        Collections.sort( l );
        System.out.println( l );
        double d = 0;
        int v = ( 5 * l.size() / 8 ) - 3 * l.size() / 8;
        for ( int i = 0; i < v; i++ )
        {
            d += l.get( i + ( 3 * l.size() / 8 ) );
        }
        return d / v;
    }
}

