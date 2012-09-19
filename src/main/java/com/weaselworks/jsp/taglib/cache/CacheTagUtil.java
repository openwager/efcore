package com.weaselworks.jsp.taglib.cache;

import java.util.*;

import javax.servlet.http.*;
import javax.servlet.jsp.*;

/**
 *
 */

public class CacheTagUtil
{
	private
	CacheTagUtil ()
	{
		return;
	}

	public static String ATTR = "__cache"; 
	
	/**
	 * 
	 * @param pc
	 * @param scope
	 * @return
	 */
	
	public static
	Map<String, Map<String, String>> getCaches (final PageContext pc, final CacheScope scope)
	{
		return getCaches (pc, scope); 
	}

	/**
	 * 
	 * @param req
	 * @param scope
	 * @return
	 */
	
	public static
	Map<String, Map<String, String>> getCaches (final HttpServletRequest req, final CacheScope scope)
	{
		return getCaches (req, scope); 
	}

	/**
	 * 
	 * @param pc
	 * @param scope
	 * @param create
	 * @return
	 */
	
	@SuppressWarnings ("unchecked")
	public static
	Map<String, Map<String, String>> getCaches (final PageContext pc, final CacheScope scope, final boolean create)
	{
		Map<String, Map<String, String>> caches = (Map<String, Map<String, String>>) scope.getAttribute (pc, ATTR); 
		if (caches == null) { 
			if (create) { 
				caches = new HashMap<String, Map<String, String>> (); 
				scope.setAttribute (pc, ATTR, caches); 
			} else { 
				return null; 
			}
		}
		return caches; 
	}

	/**
	 * 
	 * @param req
	 * @param scope
	 * @param create
	 * @return
	 */
	
	@SuppressWarnings ("unchecked")
	public static
	Map<String, Map<String, String>> getCaches (final HttpServletRequest req, final CacheScope scope, final boolean create)
	{
		Map<String, Map<String, String>> caches = (Map<String, Map<String, String>>) scope.getAttribute (req, ATTR); 
		if (caches == null) { 
			if (create) { 
				caches = new HashMap<String, Map<String, String>> (); 
				scope.setAttribute (req, ATTR, caches); 
			} else { 
				return null; 
			}
		}
		return caches; 
	}

	/**
	 * 
	 * @param scope
	 * @param name
	 * @param key
	 * @return
	 */
	
	public static
	Map<String, String> getCache (final PageContext pc, final CacheScope scope, final String name)
	{
		return getCache (pc, scope, name, false); 
	}

	/**
	 * 
	 * @param req
	 * @param scope
	 * @param name
	 * @return
	 */
	
	public static
	Map<String, String> getCache (final HttpServletRequest req, final CacheScope scope, final String name)
	{
		return getCache (req, scope, name, false); 
	}
	
	/**
	 * 
	 * @param pc
	 * @param scope
	 * @param name
	 * @param key
	 * @param create
	 * @return
	 */
	
	public static
	Map<String, String> getCache (final PageContext pc, final CacheScope scope, final String name, final boolean create)
	{
		final Map<String, Map<String, String>> caches = getCaches (pc, scope, create);
		return _getCache (caches, scope, name, create); 
	}
	

	/**
	 * 
	 * @param req
	 * @param scope
	 * @param name
	 * @param create
	 * @return
	 */

	public static
	Map<String, String> getCache (final HttpServletRequest req, final CacheScope scope, final String name, final boolean create)
	{
		final Map<String, Map<String, String>> caches = getCaches (req, scope, create);
		return _getCache (caches, scope, name, create); 
	}
	
	/**
	 * 
	 * @param caches
	 * @param scope
	 * @param name
	 * @param create
	 * @return
	 */
	
	public static
	Map<String, String> _getCache (final Map<String, Map<String, String>> caches, final CacheScope scope, final String name, final boolean create)
	{
		if (caches == null) { 
			return null; 
		}
		Map<String, String> cache = caches.get (name); 
		if (cache == null) { 
			if (create) { 
				cache = new HashMap<String, String> (); 
				caches.put (name, cache); 
			} else { 
				return null; 
			}
		}
		return cache; 
	}


}

// EOF