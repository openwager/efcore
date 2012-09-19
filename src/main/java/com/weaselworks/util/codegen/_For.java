package com.weaselworks.util.codegen;

/**
 * 
 * @author crawford
 *
 */

public class _For
	extends _Block
{
	@Override
	public
	String getPrefix ()
	{
		return "for (" + getClause () + ") "; 
	}
	
	protected String clause; 
	public String getClause () { return this.clause; } 
	public void setClause (final String clause) { this.clause = clause; return; } 
}

// EOF