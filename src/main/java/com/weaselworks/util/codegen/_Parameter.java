package com.weaselworks.util.codegen;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class _Parameter
	implements Encodeable
{
	public
	_Parameter ()
	{
		return; 
	}
	
	public
	_Parameter (final String type, final String name)
	{
		this (false, type, name); 
		return; 
	}
	
	public 
	_Parameter (final boolean isFinal , final String type, final String name)
	{
		setIsFinal (isFinal); 
		setType (type); 
		setName (name); 
		return; 
	}
	
	protected boolean isFinal;
	public boolean getIsFinal () { return this.isFinal; }
	public void setIsFinal (final boolean isFinal) { this.isFinal = isFinal; return; }

	protected String type;
	public String getType () { return this.type; }
	public void setType (final String type) { this.type = type; return; }

	protected String name;
	public String getName () { return this.name; }
	public void setName (final String name) { this.name = name; return; }
	
	protected
	void validate ()
	{
		// UNIMPLEMENTED
		return; 
	}
	
	public void encode (EncodingContext out)
		throws IOException 
	{
		if (getIsFinal ()) { 
			out.write ("final "); 
		}
		out.write (getType () + " "); 
		out.write (getName ()); 
		return; 
	}
}

// EOF