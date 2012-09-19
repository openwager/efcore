package com.weaselworks.codec.xio;

import com.weaselworks.io.codec.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOCodecFactoryUtil
{
	private
	XIOCodecFactoryUtil ()
	{
		return; 
	}
	
	/**
	 * Used to lock a {@linkplain XIOCodecFactory} so that the set of associated
	 * {@linkplain XIOCodec XIOCodecs} cannot be altered. 
	 * 
	 * @param factory
	 * @return
	 */
	
	public static
	XIOCodecFactory lock (final XIOCodecFactory factory)
	{
		return new XIOCodecFactoryWrapper (factory); 
	}

	/**
	 * 
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
    public static 
	XIOCodecFactory getDefaultFactory ()
	{
		final XIOCodecFactory factory = new XIOCodecFactorySupport (); 
		factory.addCodec (new DateCodec ()); 
		factory.addCodec (new StringCodec ()); 
		factory.addCodec (new MapCodec ()); 
		factory.addCodec (new ListCodec ()); 		
		factory.addCodec (new SetCodec ());
		for (final int objectId : PrimitiveCodec.OBJECT_IDS) { 
			factory.addCodec (new PrimitiveCodec (objectId)); 
		}
		return factory; 
	}
}
