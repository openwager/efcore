package com.weaselworks.jsp.taglib.util;

import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.weaselworks.util.*;
import com.weaselworks.util.TimeUtil.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class ElapsedTimeTag
	extends TagSupport
{
	public
	ElapsedTimeTag ()
	{
		return; 
	}

	protected TimeContext tc = new TimeContext ();
	public TimeContext getTimeContext () { return this.tc; } 
	
	public void setPostpend (final String postpend) { tc.setPostpend (postpend); return; } 
	public void setSeparator (final String separator) { tc.setSeparator (separator); return; } 
	public void setStopAfter (final int stopAfter) { tc.setStopAfter (stopAfter); return; } 
	public void setMaxEmpty (final int maxEmpty) { tc.setMaxEmpty (maxEmpty); return; } 
	
	protected String var; 
	public String getVar () { return this.var; } 
	public void setVar (final String var) { this.var = var; return; } 
	
	protected boolean elapsed; 
	protected boolean getElapsed () { return this.elapsed; } 
	public void setElapsed (final boolean elapsed) { this.elapsed = elapsed; return;} 

	protected Long value; 
	
	public 
	void setValue (final Object obj)
	{
		if (obj != null) { 
			if (obj instanceof Date) { 
				value = ((Date) obj).getTime (); 
			} else if (obj instanceof Long) { 
				value = ((Long) obj).longValue (); 
			} else if (obj instanceof String) {
				value = Long.parseLong((String)obj);
			} else { 
				throw new IllegalArgumentException ("Invalid argument type: " + obj.getClass().getName ()); 
			}
		}
	}

    public
    void setUnits (final String units)
            throws JspException
    {
            final TimeUnit [] tu;
            if (units.equals ("DATE")) {
                tu = TimeUtil.TimeUnits.DATE;
            } else if (units.equals ("DATE_AND_TIME")) {
                tu = TimeUtil.TimeUnits.DATE_AND_TIME;
            } else if (units.equals ("TIME")) {
                tu = TimeUtil.TimeUnits.TIME;
            } else {
                throw new JspException ("Unrecognized units: " + units);
            }
            tc.setUnits (tu);
            return;
    }

	@Override
    public
	int doEndTag ()
	    throws JspException
	{
	    final JspWriter out = pageContext.getOut ();
	    final String result; 
	    
	    try {
            if (value != null) {
                if (! getElapsed ()) {
                    value = System.currentTimeMillis () - value;
                }
                result = tc.calculateElapsed (value);
                if (getVar () != null) {
                	pageContext.setAttribute (getVar (), result); 
                } else { 
                	out.print (result);             	
                }
            }
	    }
	    catch (final Exception e) {
	        throw new JspException (e.getMessage (), e);
	    }
	
	    return EVAL_PAGE;
	}
}


// EOF
