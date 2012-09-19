package com.weaselworks.jsp.taglib.util;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings ("serial")
public class OutputHexTag
	extends TagSupport
{
	public
	OutputHexTag ()
	{
		return; 
	}
	
	protected int value; 
	public int getValue () { return this.value; } 
	public void setValue (final int value) { this.value = value; return; } 
	
	protected Integer minDigits;
	public Integer getMinDigits () { return this.minDigits; } 
	public void setMinDigits (final Integer minDigits) { this.minDigits = minDigits; return; } 

	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 

	@Override
    public
	int doEndTag ()
	    throws JspException
	{
		// Figure out the hex value to display
		
		String hex = HexUtil.encodeInt (getValue ());
		if (getMinDigits () != null) { 
			final int missing = getMinDigits () - hex.length (); 
			if (missing > 0) {
				hex = StringUtil.repeat ('0', missing) + hex;   
			}
		}
		
		// And then output it 
		
	    final JspWriter out = pageContext.getOut ();
	    
	    try {
            if (getVar () != null) {
            	pageContext.setAttribute (getVar (), hex); 
            } else { 
            	out.print (hex);             	
            }
	    }
	    catch (final Exception e) {
	        throw new JspException (e.getMessage (), e);
	    }
	
	    return EVAL_PAGE;
	}
}

// EOF