package com.weaselworks.jsp.taglib.util;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class FindAncestorTag
	extends TagSupport
{
	public
	FindAncestorTag ()
	{
		return;
	}
	
	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var)  { this.var = var; return; } 

	protected String type; 
	public String getType () { return this.type; } 
	public void setType (final String type) { this.type = type; return; }  
	
	/**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @Override
	public
    int doEndTag ()
        throws JspException
    {
    	final ClassLoader cl = Thread.currentThread ().getContextClassLoader ();
    	Class<?> clazz;
		try {
			clazz = cl.loadClass (getType ());
		}
		catch (ClassNotFoundException e) {
			throw new JspException (e.getMessage (), e); 
		}
    	final Object obj = findAncestorWithClass (this, clazz); 
    	pageContext.setAttribute (getVar (), obj); 
        return EVAL_PAGE;
    }	
}

// EOF
