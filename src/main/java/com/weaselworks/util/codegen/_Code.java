package com.weaselworks.util.codegen;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class _Code
	implements Encodeable
{
	public
	_Code (final String code)
	{
		setCode (code); 
		return; 
	}

	protected String code; 
	public String getCode () { return this.code; } 
	public void setCode (final String code) { this.code = code; return; } 
	
	@Override
	public void encode (EncodingContext ec)
		throws IOException 
	{
		ec.writeln (code); 
		return; 
	}
}

// EOF