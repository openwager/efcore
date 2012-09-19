package com.weaselworks.jsp.taglib.cache;

import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 *
 */

@SuppressWarnings ("serial")
public class InvalidateTag
	extends TagSupport
{
	public
	InvalidateTag ()
	{
		return ;
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

	public 
	int doStartTag ()
		throws JspException 
	{
		if (key == null) {
			final Map<String, Map<String, String>> caches = CacheTagUtil.getCaches (pageContext, scope); 
			if (caches != null) { 
				caches.remove (name);
			}
		} else {
			final Map<String, String> cache = CacheTagUtil.getCache (pageContext, scope, name);
			if (cache != null) { 
				cache.remove (key); 
			}
		}
		return SKIP_BODY;
	}
}


// EOF