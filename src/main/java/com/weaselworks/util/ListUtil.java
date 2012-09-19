package com.weaselworks.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class ListUtil
{
	/**
	 * 
	 * @param <A>
	 * @param <B>
	 * @param list
	 * @param xform
	 * @return
	 */
	
	public static <A, B>
	List<B> transform (final List<A> list, final Transform<A,B> xform)
	{
		final List<B> newlist = new ArrayList<B> (); 
		for (final A a : list) { 
			final B b = xform.transform (a); 
			newlist.add (b); 
		}
		return newlist; 
	}
	
	/**
	 * 
	 * @param <A>
	 * @param list
	 * @param pred
	 */
	
	public static <A>
	void filter (final List<A> list, final Predicate<A> pred)
	{
		final Iterator<A> iter = list.iterator ();
		while (iter.hasNext ()) { 
			final A a = iter.next (); 
			if (pred.evaluate (a)) { 
				iter.remove (); 
			}
		}
		return; 
	}
}
