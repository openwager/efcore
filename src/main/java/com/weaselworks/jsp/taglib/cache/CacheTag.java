package com.weaselworks.jsp.taglib.cache;

import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 *
 */

@SuppressWarnings ("serial")
public class CacheTag
	extends BodyTagSupport
{
	public
	CacheTag ()
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
	public void setScope (final String cacheScope) { this.scope = CacheScope.valueOf (cacheScope); return; }
	protected CacheScope getScope () { return this.scope; } 

	public
	int doEndTag ()
		throws JspException 
	{
		final Map<String, String> cache = CacheTagUtil.getCache (pageContext, scope, name, true);
		try {
			String s = cache.get (key);
			if (s == null) {
				if (bodyContent == null || bodyContent.getString () == null) {
					s = "";
				} else {
					s = bodyContent.getString ().trim ();
				}
				cache.put (key, s);
			}
			pageContext.getOut ().write (s);
		}
		catch (java.io.IOException ex) {
			throw new JspException (ex);
		}
		return EVAL_PAGE;
	}
}

// EOF