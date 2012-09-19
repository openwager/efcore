package com.weaselworks.svc;

import java.io.Serializable;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class FetchRequest implements Serializable
{
	private static final long serialVersionUID = 2206200861953194726L;
	
	
	public
	FetchRequest ()
	{
		return; 
	}

	public
	FetchRequest (final int start, final int blockSize)
	{
		setStart (start); 
		setBlockSize (blockSize); 
		return; 
	}

	public
	FetchRequest (final int start, final int blockSize, final boolean skipCount)
	{
		setStart (start); 
		setBlockSize (blockSize); 
		setSkipCount (skipCount); 
		return; 
	}
	
	public static final FetchRequest LONE = new FetchRequest (0, 1, true);
	public static final FetchRequest COUNT = new FetchRequest (0, 0, false); 
	
	public static final int DEFAULT_START = 0; 
	protected int start = DEFAULT_START;  
	public int getStart () { return this.start; }
	public void setStart (final int start) { this.start = start; return; } 
	
	public static final int DEFAULT_BLOCK_SIZE = -1;
	protected int blockSize = DEFAULT_BLOCK_SIZE;  
	public int getBlockSize () { return this.blockSize; } 
	public void setBlockSize (final int blockSize) { this.blockSize = blockSize; return; } 
	
	public static final boolean DEFAULT_SKIP_COUNT = false; 
	protected boolean skipCount; 
	public boolean getSkipCount () { return this.skipCount; } 
	public void setSkipCount (final boolean skipCount) { this.skipCount = skipCount; return; }
	
	@Override
	public
	String toString ()
	{
		final StringBuffer buf = new StringBuffer ();
		buf.append (getClass ().getName ());
		buf.append ('['); 
		buf.append ("start="); 
		buf.append (start); 
		buf.append (",blockSize="); 
		buf.append (blockSize); 
		buf.append (",skipCount="); 
		buf.append (skipCount);
		buf.append (']'); 
		return buf.toString (); 
	}
	
	@Override
	public
	int hashCode ()
	{
		final HashBuilder hash = new HashBuilder (); 
		hash.add (start); 
		hash.add (blockSize); 
		hash.add (skipCount); 
		return hash.getHashCode (); 
	}

	@Override
	public
	boolean equals (final Object obj)
	{
		if (! (obj instanceof FetchRequest)) { 
			return false; 
		}
		final FetchRequest fr = (FetchRequest) obj; 
		return 
			this.start == fr.start 
			&& this.blockSize == fr.blockSize
			&& this.skipCount == fr.skipCount; 		
	}
	
	public
	FetchRequest getPrevious ()
	{
		return getPrevious (this); 
	}
	
	public static
	FetchRequest getPrevious (final FetchRequest freq)
	{
		if (freq.start == 0) { 
			return freq; 
		} else { 
			return new FetchRequest (Math.max (0, freq.start - freq.blockSize), freq.blockSize);  
		}
		
		// NOT REACHED
	}
	
	public
	FetchRequest getNext ()
	{
		return getNext (this); 
	}
	
	public static
	FetchRequest getNext (final FetchRequest freq)
	{
		return new FetchRequest (freq.start + freq.blockSize, freq.blockSize); 
	}
}

// EOF