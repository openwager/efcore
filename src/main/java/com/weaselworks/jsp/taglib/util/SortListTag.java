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
public class SortListTag
	extends TagSupport
{
	public
	SortListTag ()
	{
		return; 
	}
	
	protected Collection<?> collection; 
	public Collection<?> getCollection () { return this.collection; } 
	public void setCollection (final Collection<?> collection) { this.collection = collection; return; } 

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @SuppressWarnings("unchecked")
	@Override
	public
    int doEndTag ()
        throws JspException
    {
    	Collections.sort ((List) getCollection ());
        return EVAL_PAGE;
    }
}

// EOF
