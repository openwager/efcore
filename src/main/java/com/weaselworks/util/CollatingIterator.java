package com.weaselworks.util;

import java.util.*;

/**
 * TODO: Redo this to not retain the list or the comparator
 * 
 * @author crawford
 *
 */

public class CollatingIterator<Type>
	extends IteratorDecorator<Type>
{
	public
	CollatingIterator (final Iterator<Type> iter, final Comparator<Type> comparator)
	{
	    super (iter);
	    setComparator (comparator);
	    return;
	}
	
	private Comparator<Type> comparator;
	
	public
	Comparator<Type> getComparator ()
	{
	    return this.comparator;
	}
	
	protected
	void setComparator (final Comparator<Type> comparator)
	{
	    this.comparator = comparator;
	    return;
	}
	
	/**
	 *  @see Iterator#next
	 */
	
	public
	Type next ()
	{
	    if (items == null) {
	        initialize ();
	    }
	    if (! hasNext ()) {
	        throw new IllegalStateException ("No more items.");
	    }
	    return items.get (cnt ++);
	}
	
	/**
	 *  @see Iterator#hasNext
	 */
	
	public
	boolean hasNext ()
	{
	    if (items == null) {
	        initialize ();
	    }
	    return cnt != items.size();
	}
	
	/**
	 *  @see Iterator#remove
	 */
	
	public
	void remove ()
	{
	    throw new Error ("Operation unsupported.");
	}
	
	protected List<Type> items;
	protected int cnt = 0;
	
	/**
	 *  Initializes the underlying data structures by consuming the contents of	the user-provided
	 *  Iterator and sorting them into an array where they can be handed out via	the Iterator
	 *  interface.
	 */
	
	protected
	void initialize ()
	{
	    final Iterator<Type> iter = getIterator ();
	    items = new ArrayList<Type> ();
	
	    while (iter.hasNext ()) {
	        final Type o = iter.next ();
	        items.add (o);
	    }
	    Collections.sort (items, getComparator ());
	    return;
	}
} 

// EOF