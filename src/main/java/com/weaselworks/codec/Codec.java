package com.weaselworks.codec;

import java.io.*;

/**
 * 
 * @author crawford
 */

public interface Codec <Type>
{
	/**
	 * 
	 * @param type
	 * @param os
	 * @throws IOException
	 */
	
	public <Subtype extends Type>
	void encode (final Subtype type, final OutputStream os)
		throws IOException;

	/**
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	
	public 
	Type decode (final InputStream is)
		throws IOException; 
	
}

// eOF