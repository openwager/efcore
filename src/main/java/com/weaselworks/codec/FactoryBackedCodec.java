package com.weaselworks.codec;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class FactoryBackedCodec
	implements Codec<Object>
{
	public
	FactoryBackedCodec (final CodecFactory factory)
	{
		setFactory (factory); 
		return; 
	}
	
	protected CodecFactory factory; 
	
	public
	CodecFactory getFactory () 
	{ 
		return factory; 
	}
	
	public
	void setFactory (final CodecFactory factory) 
	{
		this.factory = factory; 
		return; 
	}
	
    public Object decode (InputStream is)
        throws IOException
    {
		return null; 
    }
	

    public <Type>
	void encode (Type type, OutputStream os)
        throws IOException
    {
//		final Codec<Type> codec = getFactory ().getCodec (type); 
//		codec.encode (type, os); 
		return; 	   
    }
}

// EOF