package com.weaselworks.jsp.taglib.cache;

import javax.servlet.http.*;
import javax.servlet.jsp.*;

/**
 *
 */

public enum CacheScope
{
	PAGE, 
	REQUEST, 
	SESSION, 
	APPLICATION; 
	
	public
	String getName () 
	{
		return name (); 
	}
	
	public
	int getOrdinal ()
	{
		return ordinal (); 
	}
	
	/**
	 * 
	 * @param req
	 * @param key
	 * @param value
	 */
	
	public
	void setAttribute (final HttpServletRequest req, final String key, final Object value)
	{
		switch (this) { 
			case REQUEST: 
				req.setAttribute (key, value); 
				break; 
			case SESSION: 
				req.getSession ().setAttribute (key, value); 
				break; 
			case APPLICATION: 
				req.getSession ().getServletContext ().setAttribute (key, value); 
				break; 
			case PAGE: 
			default: 
				throw new IllegalStateException (); 
		}
		return; 
	}
	
	/**
	 * 
	 * @param req
	 * @param key
	 * @param value
	 */
	
	public
	void setAttribute (final PageContext pc,  final String key, final Object value)
	{		
		switch (this) {
			case PAGE: 
				pc.setAttribute (key, value); 
				break; 
			case REQUEST: 
				pc.getRequest ().setAttribute (key, value); 
				break; 
			case SESSION:
				pc.getSession ().setAttribute (key, value); 
				break;
			case APPLICATION:
				pc.getServletContext ().setAttribute (key, value); 
				break; 
			default: 
				throw new IllegalStateException (); 
		}
		return; 
	}

	/**
	 * 
	 * @param req
	 * @param key
	 * @param value
	 * @return
	 */
	
	public
	Object getAttribute (final HttpServletRequest req, final String key)
	{
		switch (this) {
			case REQUEST: 
				return req.getAttribute (key); 
			case SESSION:
				return req.getSession ().getAttribute (key); 
			case APPLICATION:
				return req.getSession ().getServletContext ().getAttribute (key); 
			case PAGE: 
			default: 
				throw new IllegalStateException (); 
		}

		// NOT REACHED
	}
	
	/**
	 * 
	 * @param req
	 * @param key
	 * @return
	 */
	
	public
	Object getAttribute (final PageContext pc, final String key)
	{
		switch (this) { 
			case PAGE: 
				return pc.getAttribute (key); 
			case REQUEST: 
				return pc.getRequest ().getAttribute (key); 
			case SESSION: 
				return pc.getSession ().getAttribute (key);
			case APPLICATION: 
				return pc.getServletContext ().getAttribute (key); 
			default: 
				throw new IllegalStateException (); 
		}
	}

	/**
	 * 
	 * @param req
	 * @param key
	 */

	public
	void removeAttribute (final HttpServletRequest req, final String key)
	{
		switch (this) {
			case REQUEST: 
				req.removeAttribute (key); 
				break; 
			case SESSION: 
				req.getSession ().removeAttribute (key); 
				break; 
			case APPLICATION: 
				req.getSession ().getServletContext ().removeAttribute (key);
				break; 
			case PAGE: 
			default: 
				throw new IllegalStateException (); 
		}
	
		return; 
	}

	/**
	 * 
	 * @param req
	 * @param key
	 */
	
	public
	void removeAttribute (final PageContext pc, final String key)
	{
		switch (this) {
			case PAGE: 
				pc.removeAttribute (key); 
				break; 
			case REQUEST: 
				pc.getRequest ().removeAttribute (key); 
				break; 
			case SESSION: 
				pc.getSession ().removeAttribute (key); 
				break; 
			case APPLICATION: 
				pc.getServletContext ().removeAttribute (key);
				break; 
			default: 
				throw new IllegalStateException (); 
		}
	
		return; 
	}
}

// EOF