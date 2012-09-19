package com.weaselworks.util;

/**
 * 
 * @author crawford
 *
 */

public class Interval
{
	public
	Interval ()
	{
		return; 
	}

	public
	Interval (final long start, final long end)
	{
		setStart (start); 
		setEnd (end); 
		return; 
	}
	
	protected long start; 
	public long getStart () { return this.start; } 
	public void setStart (final long start) { this.start = start; return; } 
	
	protected long end; 
	public long getEnd () { return this.end; } 
	public void setEnd (final long end) { this.end = end; return; } 
	
	public
	void shift (final long offset)
	{
		this.start += offset; 
		this.end += offset; 
		return; 
	}
	
	public 
	String toString ()
	{
		final StringBuffer buf = new StringBuffer ();
//		buf.append (getClass().getName ()); 
		buf.append ('['); 
//		buf.append ("start=");
		buf.append (start);
		buf.append (", ");
//		buf.append (",end="); 
		buf.append (end); 
		buf.append (']'); 
		return buf.toString (); 
	}

	/**
	 * Used to test whether there is any overlap between the two intervals. 
	 * 
	 * @param i
	 * @return
	 */
	
	public
	boolean overlaps (final Interval i)
	{
		return includes (i.start) || includes (i.end) || i.includes (this.start);
	}
		
	/**
	 * 
	 * @param value
	 * @return
	 */
	
	public
	boolean includes (final long value)
	{
		return value >= this.start && value <= this.end;  
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	
	public
	boolean adjacent (final Interval i)
	{
		return this.start == (i.end  + 1) || i.start == (this.end + 1); 
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	
	public
	Interval merge (final Interval other)
	{
		if (! overlaps (other) && ! adjacent (other)) { 
			throw new IllegalStateException ("Intervals don't overlap."); 
		}
		return new Interval (Math.min (this.start, other.start), Math.max (this.end, other.end)); 
	}

	/**
	 * 
	 * @param permissible
	 * @return
	 */
	
	public
	Interval clip (final Interval permissible)
	{
		final long ps = permissible.getStart (), pe = permissible.getEnd (); 
		if ((getEnd () < ps) ||
			getStart () > pe) { 
			return null; 
		}		
		final long start = this.start < ps ? ps : this.start; 
		final long end = this.end > pe ? pe : this.end; 
		return new Interval (start, end); 
	}
	
	public
	boolean equals (final Object obj)
	{
		if (! (obj instanceof Interval)) { 
			return false; 
		}
		final Interval i = (Interval) obj; 
		return this.start == i.start
			&& this.end == i.end; 
	}
}

// EOF