package com.weaselworks.codec.jser;

import com.weaselworks.codec.*;

/**
 * 
 * @author crawford
 *
 */

public class JavaSerializationCodecFactory
	extends CodecFactorySupport
		implements CodecFactory
{
	public
	JavaSerializationCodecFactory()
	{
		setResolver (new JavaSerializationCodecResolver ()); 
		return; 
	}
}

// EOF