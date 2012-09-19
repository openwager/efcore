package com.weaselworks.util;

/**
 * 
 * @author crawford
 *
 * @param <T>
 */

public interface Predicate<T>
{
	/**
	 * 
	 * @param t
	 * @return
	 */
	
	public
	boolean evaluate (T t); 
}

// EOF