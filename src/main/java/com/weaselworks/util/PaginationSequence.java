package com.weaselworks.util;

import java.util.*;

import org.apache.log4j.*;

import com.weaselworks.svc.*;

/**
 * 
 * @author crawford
 *
 */

public class PaginationSequence
	implements Iterable<Long>
{
	private static final Logger logger = Logger.getLogger (PaginationSequence.class); 
	public static final boolean DEBUG = false; 
	
	public
	PaginationSequence (final FetchResults<?> fres, final int sides, final int middle)
	{
		if (fres.getTotal () < 0) { 
			throw new IllegalStateException ("No total found in results."); 
		}
		this.fres = fres; 
		this.sides = sides; 
		this.middle = middle; 
		return; 
	}
	
	protected FetchResults<?> fres; 
	public FetchResults<?> getFetchResults () { return this.fres; } 
	
	protected int sides; 
	public int getSides () { return this.sides; } 
	
	protected int middle; 
	public int getMiddle () { return this.middle; } 	
	
	/**
	 * 
	 * @param ivs
	 */
	
	protected
	void coalesce (final List<Interval> ivs)
	{
		boolean done = false; 
		
		if (DEBUG) { 
			System.err.println ("coalesce"); 
			for (final Interval iv : ivs) { 
				logger.info (iv); 
			}
		}
		
		do { 
			done = true; 
			for (int i = 0; i < ivs.size () - 1; i ++) { 
				final Interval i1 = ivs.get (i); 
				final Interval i2 = ivs.get (i + 1); 
				if (i1.overlaps (i2) || i1.adjacent (i2)) { 
					final Interval i3 = i1.merge (i2);
					ivs.remove (i); 
					ivs.remove (i);
					ivs.add (i, i3);
					done = false;
					if (DEBUG) { 
						dump ("coalesce", ivs); 
					}
					break; 
				} 
			}
		} while (! done); 
		
		return; 
	}
	
    public 
    Iterator<Long> iterator ()
    {
		final List<Interval> ivs = new ArrayList<Interval> ();  
		
		// Calculate the initial intervals
		
		ivs.add (new Interval (0, sides - 1));  
		final long pages = fres.getPageCount (); 
		final long current = fres.getCurrentPage ();
		final long midstart = current - middle / 2; 
		ivs.add (new Interval (midstart, midstart + middle - 1));
		ivs.add (new Interval (pages - sides, pages - 1)); 
		
		if (DEBUG) { 
			dump ("initial", ivs);
		}
		
		// Clip the intervals in case they exceed the permissible range
		
		final Interval permissible = new Interval (0, pages - 1); 
		for (int i = 0; i < ivs.size (); i ++) { 
			final Interval iv = ivs.get (i); 
			final Interval iv2 = iv.clip (permissible); 
			if (! iv.equals (iv2)) { 
				ivs.remove (i); 
				ivs.add (i, iv2); 
			}
		}
		
		if (DEBUG) { 
			dump ("clipped", ivs); 		
		}
		coalesce (ivs); 
						
		// Assemble the intervals into an appropriate sequence
		
		final List<Long> seq = new ArrayList<Long> ();
		Interval last = null; 
		
		for (final Interval iv : ivs) { 
			if (last != null) { 
				seq.add (-1L); 
			}
			for (long v = iv.start; v <= iv.end; v ++) { 
				seq.add (v); 
			}
			last = iv; 
		}
		
		return seq.iterator ();  
    }

	/**
	 * 
	 * @param args
	 */
	
	public static 
	void main (final String [] args)
	{
		final FetchRequest freq = new FetchRequest (0, 10); 
		final FetchResults<Object> fres = new FetchResults<Object> (freq, new ArrayList<Object> (), 100);
		PaginationSequence ps = new PaginationSequence (fres, 2, 3); 

		for (int page = 0; page < 10; page ++) {
			freq.setStart ((int) freq.getBlockSize () * page); 
			System.err.println ("----"); 
			System.err.println ("cp=" + fres.getCurrentPage () + ", bs=" + freq.getBlockSize () + ", t=" + fres.getTotal ()); 
			dump (ps); 
		}
		return; 
	}
	
	/**
	 * 
	 * @param ps
	 */
		
	protected static
	void dump (final PaginationSequence ps)
	{
		for (final Long val : ps) { 
			System.err.print (" [ " + val + " ] "); 			
		}
		System.err.println (); 
		return; 	
	}
	
	/**
	 * 
	 * @param label
	 * @param ivs
	 */
	
	protected static
	void dump (final String label, final List<Interval> ivs)
	{
		System.err.println ("==== " + label + " ===="); 
		for (int i = 0; i < ivs.size (); i ++) { 
			System.err.println ("" + i + ": " + ivs.get (i)); 
		}
		return; 
	}
}

// EOF