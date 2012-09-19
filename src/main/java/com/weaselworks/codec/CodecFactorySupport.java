package com.weaselworks.codec;

import java.util.*;

import org.apache.log4j.*;

/**
 * 
 * @author crawford
 *
 */

public class CodecFactorySupport
	implements CodecFactory
{
	protected static final Logger logger = Logger.getLogger (CodecFactorySupport.class); 
	
	public
	CodecFactorySupport ()
	{
		return; 
	}
	
	/**
	 * Contains the set of registered codecs. 
	 */
	
	protected Map<Class<?>, Codec<?>> cache = new HashMap<Class<?>, Codec<?>> (); 
	
	public <Type>
	void registerCodec (final Class<?> type, final Codec<?> codec)
	{
		cache.put (type, codec); 
		return; 
	}
	
	@SuppressWarnings("unchecked")
    public <Type> 
	Codec<Type> getCodec (Class<?> type)
		throws CodecNotFoundException
    {
	    Codec<Type> codec = (Codec<Type>) cache.get (type);
	    if (codec == null) { 
	    	if (resolver != null) { 
	    		codec = (Codec<Type>) resolver.resolveCodec (type); 
	    		if (codec != null) { 
	    			cache.put (type, codec); 
	    		} else { 
	    			cache.put (type, VoidCodec.INSTANCE); 
	    		}
	    	}
	    }
	    if (codec == VoidCodec.INSTANCE || codec == null) { 
	    	throw new CodecNotFoundException ("Codec not found: " + type.getName ());  
	    }
	    return codec; 
    }

	public
	boolean hashCodec (final Class<?> type) 
	{
		return cache.containsKey (type); 
	}

	protected CodecResolver resolver; 
	
	public
	void setResolver (final CodecResolver resolver)
	{
		this.resolver = resolver; 
		return; 
	}
	
	public
	CodecResolver getResolver ()
	{
		return this.resolver; 
	}

	public
	boolean hasResolver ()
	{
		return getResolver () != null; 
	}
}

// EOF