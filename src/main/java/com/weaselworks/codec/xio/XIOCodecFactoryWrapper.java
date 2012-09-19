package com.weaselworks.codec.xio;

/**
 * 
 * @author crawford
 *
 */

class XIOCodecFactoryWrapper
	implements XIOCodecFactory
{
	public
	XIOCodecFactoryWrapper (final XIOCodecFactory factory)
	{
		this.factory = factory; 
		return; 
	}
	
	protected XIOCodecFactory factory; 
	
	public
	void addCodec (XIOCodec<?> codec)
	{
		throw new IllegalStateException ("Factory is locked."); 
		// NOT REACHED
	}
	
	public 
	void addCodecs (final XIOCodec<?> [] codecs) 
	{
		throw new IllegalStateException ("Factory is locked."); 
		// NOT REACHED
	}

	public <Type> 
	XIOCodec<? super Type> getCodec (Class<Type> type)
	{
		return factory.getCodec (type); 
	}

	public
	XIOCodec<?> getCodec (int objectId) 
	{
		return factory.getCodec (objectId); 
	}
}

// EOF
