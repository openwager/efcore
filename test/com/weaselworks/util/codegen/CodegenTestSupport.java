package com.weaselworks.util.codegen;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class CodegenTestSupport
{
	/**
	 * 
	 * @param e
	 * @throws IOException
	 */
	
	public static
	void output (final Encodeable e)
		throws IOException
	{
		final EncodingContext ec = new EncodingContext (); 
		e.encode (ec);
		ec.getOutput ().write ("==================\n"); 
		ec.getOutput ().flush (); 
		return; 
	}
}
