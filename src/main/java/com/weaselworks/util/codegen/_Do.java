package com.weaselworks.util.codegen;

/**
 * 
 * @author crawford
 *
 */

public class _Do
	extends _Block
{
	@Override
	public 
	String getPrefix ()
	{
		return "do "; 
	}
	
	@Override
	public 
	String getPostfix ()
	{
		return " while (" + getClause () + ");"; 
	}
	
	protected String clause; 
	public String getClause () { return this.clause; } 
	public void setClause (final String clause) { this.clause = clause; return; } 
}

// EOF