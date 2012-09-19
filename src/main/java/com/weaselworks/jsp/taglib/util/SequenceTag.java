package com.weaselworks.jsp.taglib.util;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class SequenceTag
	extends TagSupport
{
	public 
	SequenceTag ()
	{
		return;
	}

	protected int start; 
	public int getStart () { return this.start; } 
	public void setStart (final int start) { this.start = start; return; } 
	
	protected int stop; 
	public int getStop () { return this.stop; } 
	public void setStop (final int stop) { this.stop = stop; return; } 
	
	protected int step = 1; 
	public int getStep () { return this.step; } 
	public void setStep (final int step) { this.step = step; return; } 
	
	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var)  { this.var = var; return; } 
	
    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @Override
	public
    int doEndTag ()
        throws JspException
    {
    	final Sequence seq = new Sequence (getStart (), getStop (), getStep ()); 
    	pageContext.setAttribute (getVar (), seq); 
        return EVAL_PAGE;
    }
}

// EOF
