package com.weaselworks.io.codec;

import com.weaselworks.codec.xio.*;

/**
 * 
 * @author crawford
 *
 */

public interface Portable<Type>
{
	/**
	 * 
	 * @return
	 */
	
	public 
	XIOCodec<Type> getCodec (); 
}

// EOF