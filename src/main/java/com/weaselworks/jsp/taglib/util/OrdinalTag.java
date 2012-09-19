package com.weaselworks.jsp.taglib.util;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class OrdinalTag
	extends TagSupport
{
	public
	OrdinalTag ()
	{
		return; 
	}

	public int value; 
	public int getValue () { return this.value; } 
	public void setValue (final int value) { this.value = value; return; } 

	public boolean showValue = true; 
	public boolean getShowValue () { return this.showValue; } 
	public void setShowValue (final boolean showValue) { this.showValue = showValue; return; } 
	
	@Override
    public
	int doEndTag ()
	    throws JspException
	{
	    final JspWriter out = pageContext.getOut ();
	    final String suffix; 
	    
	    try {
	    	if (value >= 21) { 
	    		switch (value % 10) { 
	    			case 1: 
	    				suffix = "st"; 
	    				break; 
	    			case 2: 
	    				suffix = "nd"; 
	    				break; 
	    			case 3: 
	    				suffix = "rd"; 
	    				break; 
	    			default: 
	    				suffix = "th"; 
	    				break;
	    		}
	    	} else { 
	    		switch (value) { 
	    			case 1: 
	    				suffix = "st"; 
	    				break; 
	    			case 2: 
	    				suffix = "nd"; 
	    				break; 
	    			case 3: 
	    				suffix = "rd"; 
	    				break; 
	    			default: 
	    				suffix = "th"; 
	    				break;
	    		}
	    	}
	    	if (showValue) { 
	    		out.print (value); 
	    	}
	    	out.print (suffix);             	
	    }
	    catch (final Exception e) {
	        throw new JspException (e.getMessage (), e);
	    }
	
	    return EVAL_PAGE;
	}
}

// EOF