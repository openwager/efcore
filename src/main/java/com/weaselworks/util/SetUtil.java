package com.weaselworks.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class SetUtil
{
	public static <A, B>
	Set<B> transform (final Set<A> in, final Transform<A,B> xform)
	{
		final Set<B> out = new HashSet<B> (); 
		for (final A a : in) { 
			final B b = xform.transform (a);
			out.add (b); 
		}
		return out; 
	}
}

// EOF