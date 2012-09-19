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
public class MergeSetsTag
	extends TagSupport
{
	public
	MergeSetsTag ()
	{
		return; 
	}
	
	protected Set<?> set1; 
	public Set<?> getSet1 () { return this.set1; } 
	public void setSet1 (final Set<?> set1) { this.set1 = set1; return; } 
	
	protected Set<?> set2; 
	public Set<?> getSet2 () { return this.set2; } 
	public void setSet2 (final Set<?> set2) { this.set2 = set2; return; } 
	
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
    	final Set<Object> set = new HashSet<Object> (); 
    	set.addAll (getSet1 ()); 
    	set.addAll (getSet2 ()); 
    	pageContext.setAttribute (getVar (), set); 
        return EVAL_PAGE;
    }
}

// EOF
