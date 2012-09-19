package com.weaselworks.codec;

/**
 * 
 * @author crawford
 *
 */

public interface CodecResolver
{
	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @return
	 */
	
	public <Type>
	Codec<Type> resolveCodec (final Class<Type> type); 
}

// EOF
