package com.weaselworks.jsp.taglib.cache;

/**
 *
 */

@SuppressWarnings ("serial")
public class IfNotCachedTag
	extends IfCachedTag
{
	public
	IfNotCachedTag ()
	{
		return; 
	}

	@Override
	protected
	boolean predicate ()
	{
		return ! super.predicate (); 
	}
}

// EOF