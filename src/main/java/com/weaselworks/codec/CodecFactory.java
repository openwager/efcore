package com.weaselworks.codec;

/**
 * 
 * @author crawford
 *
 */

public interface CodecFactory
{
	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @return
	 */
	
	public <Type>
	Codec<Type> getCodec (final Class<?> type)
		throws CodecNotFoundException; 
}

// EOF