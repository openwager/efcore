package com.weaselworks.util;

/**
 * 
 * @author crawford
 *
 * @param <T>
 */

public class PredicateInverter<T>
	implements Predicate<T>
{
	public
	PredicateInverter (final PredicateInverter<T> pred)
	{
		this.pred = pred; 
		return; 
	}
	
	protected Predicate<T> pred;

	@Override
	public boolean evaluate (T t) 
	{
		return ! pred.evaluate (t); 
	} 
}

// EOF