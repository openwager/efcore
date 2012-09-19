package com.weaselworks.util.codegen;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public interface Encodeable
{
	/**
	 * 
	 * @param out
	 */
	
	public
	void encode (final EncodingContext out)
		throws IOException;
}

// EOF