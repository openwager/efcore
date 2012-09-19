package com.weaselworks.jsp.taglib.cache;

import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 *
 */

@SuppressWarnings ("serial")
public class IfCachedTag
	extends BodyTagSupport
{
	public
	IfCachedTag ()
	{
		return; 
	}
	
	protected String name = ""; 
	public String getName () { return this.name; } 
	public void setName (final String name) { this.name = name; return; } 
	
	protected String key; 
	public String getKey () { return this.key; } 
	public void setKey (final String key) { this.key = key; return; } 
	
	protected CacheScope scope = CacheScope.PAGE; 
	public void setCacheScope (final String cacheScope) { this.scope = CacheScope.valueOf (cacheScope); return; }
	protected CacheScope getScope () { return this.scope; } 

	protected
	boolean predicate ()
	{
		final Map<String, String> cache = CacheTagUtil.getCache (pageContext, scope, name);
		return cache.containsKey (key); 
	}
	
	public
	int doEndTag ()
		throws JspException 
	{
		if (predicate ()) {
			return EVAL_PAGE; 
		} else { 
			return SKIP_PAGE; 
		}
	}
}

// EOF