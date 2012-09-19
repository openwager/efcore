package com.weaselworks.svc;

import java.util.*;

import com.weaselworks.util.*;

/**
 *
 */

public class FetchResultsUtil
{
	private
	FetchResultsUtil ()
	{
		return; 
	}

	/**
	 * Used to transform a {@linkplain FetchResults} from one type into another. 
	 * 
	 * @param <A>
	 * @param <B>
	 * @param fres
	 * @param xform
	 * @return
	 */
	
	public static <A, B>
	FetchResults<B> transform (final FetchResults<A> fres, final Transform<A, B> xform)
	{
		final FetchResults<B> fres2 = new FetchResults<B> (); 
		fres2.setFetchRequest (fres.getFetchRequest ()); 
		fres2.setTotal (fres.getTotal ()); 
		final List<B> list = new ArrayList<B> (); 
		for (final A a : fres.getResults ()) {
			list.add (xform.transform (a)); 
		}
		fres2.setResults (list); 
		return fres2;
	}

	/**
	 * 
	 * @param <A>
	 * @param list
	 * @return
	 */
	
	public static <A>
	FetchResults<A> wrap (final List<A> list)
	{
		final FetchRequest freq = new FetchRequest (0, -1); 
		final FetchResults<A> fres = new FetchResults<A> (freq, list, (long) list.size ()); 
		return fres; 
	}
}


// EOF