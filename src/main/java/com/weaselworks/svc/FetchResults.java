package com.weaselworks.svc;

import java.io.Serializable;
import java.util.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class FetchResults <Type> implements Serializable
{
	private static final long serialVersionUID = 498200930970906835L;
	
	public
	FetchResults ()
	{
		return; 
	}
	
	public
	FetchResults (final FetchRequest freq)
	{
		setFetchRequest (freq); 
		return; 
	}
	
	public
	FetchResults (final FetchRequest freq, final List<Type> results, final long total)
	{
		setFetchRequest (freq); 
		setResults (results); 
		setTotal (total); 
		return; 
	}
	
	/**
	 * Creates an empty fetch results data structure. 
	 * 
	 * @param <Type>
	 * @param freq
	 * @return
	 */
	
	public static <Type>
	FetchResults<Type> getEmptyResults (final FetchRequest freq)
	{
		final List<Type> results = new ArrayList<Type> (); 
		final FetchResults<Type> fres = new FetchResults<Type> (freq, results, 0);
		return fres; 
	}
	
	public
	boolean getAtStart ()
	{
		return getFetchRequest ().getStart () == 0; 
	}
	
	public 
	boolean getAtEnd ()
	{
		return freq.getStart () + freq.getBlockSize () >= total;
	}
	
    public
    int getBlockIndex ()
    {
            return freq.getStart () / freq.getBlockSize ();
    }

    public
    int getBlockCount ()
    {
            return (int) (total / freq.getBlockSize ()) + (total % freq.getBlockSize() > 0 ? 1 : 0) ;
    }
	
	public
	long getLastBlock ()
	{		
		long start = total - total % freq.getBlockSize ();
		if (start == total) { 
			start -= freq.getBlockSize ();
		}
		return start;  
	}
	
	public
	long getCurrentPage ()
	{
		return freq.getStart () / freq.getBlockSize ();  
	}
	
	public
	long getPageCount ()
	{
		final int blockSize = freq.getBlockSize (); 
		long pages = total / blockSize; 
		if (total % blockSize != 0) { 
			pages ++; 
		}
		return pages; 
	}
	
    public
    FetchRequest getPrevious ()
    {
        if (getAtStart ()) {
            return freq;
        } else {
            return freq.getPrevious ();
        }
        
        // NOT REACHED
    }

    public
    FetchRequest getNext ()
    {
        if (getAtEnd ()) {
            return freq;
        } else {
            return freq.getNext ();
        }
        
        // NOT REACHED
    }

    public
    FetchRequest getEnd ()
    {
        if (getAtEnd ()) {
            return freq;
        } else {
            final int bs = freq.getBlockSize ();
            int start = ((int) total / bs) * bs; 
            if (total % bs == 0) { 
            	start -= 1; 
            }
            return new FetchRequest (start, bs);
        }
        
        // NOT REACHED
    }
    
    public boolean getIsEmpty () { return getResults ().size () == 0; }
	public boolean getNotEmpty () { return this.getResults ().size () > 0; } 
	
	protected FetchRequest freq; 
	public FetchRequest getFetchRequest () { return this.freq; } 
	public void setFetchRequest (final FetchRequest freq) { this.freq = freq; return; } 
	
	protected List<Type> results; 
	public List<Type> getResults () { return this.results; } 
	public void setResults (final List<Type> results) { this.results = results; return; } 
	
	protected long total = 1; 
	public long getTotal () { return this.total; }
	public void setTotal (final long total) { this.total = total; return; } 
	
	@Override
	public
	String toString ()
	{
		final StringBuffer buf = new StringBuffer (); 
		buf.append (getClass ().getName ());
		buf.append ('['); 
		buf.append ("freq="); 
		buf.append (freq); 
		buf.append (",results=");
		buf.append (results); 
		buf.append (",total=");
		buf.append (total);
		buf.append (']');
		return buf.toString ();
	}
	
	@Override 
	public 
	int hashCode ()
	{
		final HashBuilder hash = new HashBuilder ();
		hash.add (freq); 
		hash.add (results); 
		hash.add (total); 
		return hash.getHashCode (); 
	}
	
	@Override 
	public
	boolean equals (final Object obj)
	{
		if (! (obj instanceof FetchResults<?>)) { 
			return false; 
		}
		final FetchResults<?> fr = (FetchResults<?>) obj; 
		return
			this.total == fr.total 
			&& Util.isEqual (this.freq, fr.freq)
			&& Util.isEqual (this.results, fr.results); 		
	}
}

// EOF