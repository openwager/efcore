package com.weaselworks.svc;

import javax.persistence.*;


/**
 * 
 * @author crawford
 *
 */

public class QueryUtil
{
	private
	QueryUtil ()
	{
		return; 
	}
	
	/**
	 * 
	 * @param query
	 * @param fr
	 */
	
	public static
	void limitQuery (final Query query, final FetchRequest fr)
	{
		if (fr != null) {
			query.setFirstResult (fr.getStart ());
			if (fr.getBlockSize () > 0) {
				query.setMaxResults (fr.getBlockSize ());
			}
		}
       return;
   }
}

// EOF