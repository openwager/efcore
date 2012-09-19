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
public class AddToCollectionTag
	extends TagSupport
{
	public
	AddToCollectionTag ()
	{
		return; 
	}
	
	protected Collection<Object> collection; 
	public Collection<Object> getCollection () { return this.collection; } 
	public void setCollection (final Collection<Object> collection) { this.collection = collection; return; } 

	protected Object value; 
	public Object getValue () { return this.value; } 
	public void setValue (final Object value) { this.value = value; return; } 

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */

    @SuppressWarnings("unchecked")
	@Override
	public
    int doEndTag ()
        throws JspException
    {
    	final Object value = getValue (); 
    	if (value instanceof Collection) { 
    		getCollection ().addAll ((Collection<Object>) value); 
    	} else { 
    		getCollection ().add (value);
    	}
    	return EVAL_PAGE;
    }
}

// EOF
