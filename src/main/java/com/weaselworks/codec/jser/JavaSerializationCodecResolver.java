package com.weaselworks.codec.jser;

import com.weaselworks.codec.*;

/**
 * 
 * @author crawford
 *
 * @param <Type>
 */

public class JavaSerializationCodecResolver
	implements CodecResolver 
{
	public
	JavaSerializationCodecResolver ()
	{
		return; 
	}

	protected final JavaSerializationCodec SINGLETON = new JavaSerializationCodec (); 
	
	@SuppressWarnings("unchecked")
    public <Type>
	Codec<Type> resolveCodec (Class<Type> type)
    {
	    return (Codec<Type>) SINGLETON; 
    }
}

// EOF