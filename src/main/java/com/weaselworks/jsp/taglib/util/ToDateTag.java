package com.weaselworks.jsp.taglib.util;

import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class ToDateTag
    extends TagSupport
{
	public
	ToDateTag ()
	{
	        return;
	}
	
	protected String var;
	public String getVar () { return this.var; }
	public void setVar (final String var) { this.var = var; return; }
	
	protected Long value;
	public Long getValue () { return this.value; }
	public void setValue (final Long value) { this.value = value; return; }

    @Override
    public
    int doEndTag ()
        throws JspException
    {
        final Date date;
        if (getValue () != null) {
                date = new Date (getValue ());
        } else {
                date = null;
        }
        pageContext.setAttribute (getVar (), date);
        return EVAL_PAGE;
    }
}

// EOF