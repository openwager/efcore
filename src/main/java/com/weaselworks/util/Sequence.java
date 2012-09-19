package com.weaselworks.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class Sequence
	implements Iterable<Integer>
{
	public
	Sequence (final int start, final int stop)
	{
		this (start, stop, start <= stop ? 1 : -1); 
		return; 
	}
	
    public
    Sequence (int start, int stop, int step)
    {
        if (stop != start) {
            if (sign (stop - start) != sign (step)) {
                throw new IllegalArgumentException ("Invalid step sign: " + step);
            }
        }
        this.start = start;
        this.stop = stop;
        this.step = step;
        this.sign = sign (step);
        return;
    }	

    protected int start; 
	protected int stop;
	protected int step; 
	protected int sign; 

	/**
	 * 
	 * @param val
	 * @return
	 */
	
    public static
    int sign (final int val)
    {
        if (val < 0) {
            return -1;
        } else if (val > 0) { 
        	return 1;
        } else {
            return 0;
        }
    }

    public
    int getStart ()
    {
            return this.start;
    }

    public
    int getStop ()
    {
            return this.stop;
    }
    
    public
    Iterator<Integer> getIterator ()
    {
    	return iterator (); 
    }

    /**
     * 
     */
    
    public Iterator<Integer> iterator ()
    {
        return new Iterator<Integer> () {
            private int i = start;

            public boolean hasNext ()
            {
                return sign == 1 ? i <= stop : i >= stop;
            }

            public Integer next ()
            {
                try {
                    return i;
                    }
                finally {
                    i += step;
                }
            }

            public void remove ()
            {
                throw new UnsupportedOperationException ();
            }
        };
    }

    public
    String toString ()
    {
            String s = getClass().getName () + "[";
            s += "start=" + start;
            s += ",stop=" + stop;
            s += ",step=" + step;
            return s + "]";
    }

    public static
    void main (final String [] a) {
            for (int i : new Sequence (1, 10, 1)) {
                    System.out.println (i);
            }
            final Sequence r = new Sequence (1, 2);
            System.err.println (r instanceof Iterable<?>);
            return;
    }
}

// EOF