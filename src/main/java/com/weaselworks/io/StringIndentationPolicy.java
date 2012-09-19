package com.weaselworks.io;

/**
 * 
 * @author crawford
 *
 */

public class StringIndentationPolicy
	implements IndentationPolicy
{
	public
	StringIndentationPolicy ()
	{
		this ("  ") ;
		return; 	
	}
	
	public
	StringIndentationPolicy (final String prefix)
	{
		setPrefix (prefix); 
		return; 
	}
	
	protected String prefix;
	public String getPrefix () { return this.prefix; }
	public void setPrefix (final String prefix) { this.prefix = prefix; return; }

	public 
	String getIndent (int level) 
	{
		StringBuffer buf = new StringBuffer (); 
		for (int i = 0; i < level; i ++) { 
			buf.append (prefix); 
		}
		return buf.toString ();
	}
}

// EOF