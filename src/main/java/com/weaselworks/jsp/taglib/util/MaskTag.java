package com.weaselworks.jsp.taglib.util;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class MaskTag
	extends TagSupport
{
	public 
	MaskTag ()
	{
		return; 
	}
	
	protected int value; 
	public int getValue () { return this.value; } 
	public void setValue (final int value) { this.value = value; return; } 
	
	protected int mask;
	public int getMask () { return this.mask; } 
	public void setMask (final int mask) { this.mask = mask; return; } 
	
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
    	final int i = value & mask; 
    	pageContext.setAttribute (getVar (), new Integer (i)); 
        return EVAL_PAGE;
    }
}

// EOF
