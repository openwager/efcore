package com.weaselworks.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class LongSequence
	implements Iterable<Long>
{
	public
	LongSequence (final long start, final long stop)
	{
		this (start, stop, start <= stop ? 1 : -1); 
		return; 
	}
	
    public
    LongSequence (long start, long stop, long step)
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

    protected long start; 
	protected long stop;
	protected long step; 
	protected long sign; 

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
        
        // NOT REACHED
    }

    /**
     * 
     * @param val
     * @return
     */
    
    public static
    int sign (final long val)
    {
    	if (val < 0L) { 
    		return -1; 
    	} else if (val > 0L) { 
    		return 1; 
    	} else { 
    		return 0; 
    	}
    	
    	// NOT REACHED
    }
    
    public
    long getStart ()
    {
        return this.start;
    }

    public
    long getStop ()
    {
        return this.stop;
    }
    
    public
    Iterator<Long> getIterator ()
    {
    	return iterator (); 
    }

    /**
     * 
     */
    
    public Iterator<Long> iterator ()
    {
        return new Iterator<Long> () {
            private long i = start;

            public boolean hasNext ()
            {
                return sign == 1 ? i <= stop : i >= stop;
            }

            public Long next ()
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
            for (long i : new LongSequence (1L, 10L, 1L)) {
                    System.out.println (i);
            }
            final LongSequence r = new LongSequence (1, 2);
            System.err.println (r instanceof Iterable<?>);
            return;
    }
}

// EOF