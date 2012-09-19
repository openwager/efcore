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
public class GetMapKeysTag
	extends TagSupport
{
	public
	GetMapKeysTag ()
	{
		return; 
	}
	
	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var)  { this.var = var; return; } 
	
	protected Map<?, ?> map; 
	public Map<?, ?> getMap () { return this.map; } 
	public void setMap (final Map<?, ?> map) { this.map = map; return; } 

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @Override
	public
    int doEndTag ()
        throws JspException
    {
    	pageContext.setAttribute (getVar (), getMap ().keySet ()); 
        return EVAL_PAGE;
    }
}

// EOF