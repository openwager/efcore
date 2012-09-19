package com.weaselworks.util.codegen;

/**
 * 
 * @author crawford
 *
 */

public enum Visibility
{
	PUBLIC ("public"), 
	PROTECTED ("protected"), 
	PACKAGE (""), 
	PRIVATE ("private"); 

	private
	Visibility (final String value)
	{
		setValue (value); 
		return; 
	}
	
	protected String value; 
	public String getValue () { return this.value; } 
	public void setValue (final String value) { this.value = value; return; } 
}


// EOF