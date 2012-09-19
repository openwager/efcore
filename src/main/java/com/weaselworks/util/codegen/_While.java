package com.weaselworks.util.codegen;

/**
 * 
 * @author crawford
 *
 */

public class _While
	extends _Block
{
	@Override
	public
	String getPrefix ()
	{
		return "while (" + getClause () + ") "; 
	}
	
	protected String clause; 
	public String getClause () { return this.clause; } 
	public void setClause (final String clause) { this.clause = clause; return; } 
}

// EOf