package com.weaselworks.jsp.taglib.util;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class ShiftTag
	extends TagSupport
{
	public
	ShiftTag ()
	{
		return; 
	}
	
	protected int value; 
	public int getValue () { return this.value; } 
	public void setValue (final int value) { this.value = value; return; } 
	
	protected int exponent;
	public int getExponent () { return this.exponent; } 
	public void setExponent (final int exponent) { this.exponent = exponent; return; } 
	
	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @Override
	public
    int doEndTag ()
        throws JspException
    {
    	final int i = value << exponent; 
    	pageContext.setAttribute (getVar (), new Integer (i)); 
        return EVAL_PAGE;
    }
}

// EOF