package com.weaselworks.codec.json;

import java.util.*;

import org.apache.log4j.*;
import org.json.*;

/**
 * 
 * @author crawford
 *
 */

public class JsonCodecFactorySupport
	implements JsonCodecFactory
{
	private static final Logger logger = Logger.getLogger (JsonCodecFactorySupport.class); 
	
	public
	JsonCodecFactorySupport ()
	{
		return; 
	}

	/**
	 * Contains the set of registered codecs. 
	 */
	
	protected Map<Class<?>, JsonCodec<?>> byClass = new HashMap<Class<?>, JsonCodec<?>> (); 
	
	/**
	 * 
	 * @return
	 */
	
	public
	Collection<JsonCodec<?>> getCodecs ()
	{
		return byClass.values ();  
	}
	
	/**
	 * 
	 */
	
	protected Map<String, JsonCodec<?>> byType = new HashMap<String, JsonCodec<?>> (); 
	
	/**
	 * 
	 */
	
	public 
	void dump ()
	{
		System.err.println ("=== JsonCodecFactorySupport (" + this.getClass().getName () + ") ==="); 
		System.err.println ("byClass:"); 
		for (final Class<?> c : byClass.keySet ()) { 
			final JsonCodec<?> codec  = byClass.get (c); 
			System.err.println ("  " + c.getSimpleName () + " -> " + codec); 
		}
		System.err.println ("byType:");
		for (final String t : byType.keySet ()) { 
			final JsonCodec<?> codec  = byType.get (t); 
			System.err.println ("  '" + t + "' -> " + codec); 
		}
		System.err.println ("======================================");
		return; 
	}
	
	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @param codec
	 */
	
	public
	void registerCodec (final Class<?> type, final JsonCodec<?>codec)
	{
		if (byClass.containsKey (type)) { 
			logger.error ("Class '" + type + "' already registered with " + byClass.get (type).getClass ().getName ());
//			throw new IllegalStateException ("Class '" + type + "' already registered with " + byClass.get (type).getClass ().getName ());  
		}
		if (byType.containsKey (codec.getType ())) {
			logger.error ("Type '" + codec.getType () + "' already registered with " + byType.get (codec.getType ()).getClass().getName ());
//			throw new IllegalStateException ("Type '" + codec.getType () + "' already registered with " + byType.get (codec.getType ()).getClass().getName ());  
		}
		byClass.put (type, codec);
		byType.put (codec.getType (), codec); 
		codec.setFactory (this); 
		return; 
	}
	
	/**
	 * 
	 * @param codecs
	 */
	
	public
	void registerCodecs (final JsonCodec<?> [] codecs)
	{
		for (final JsonCodec<?> codec : codecs) { 
			registerCodec (codec.getClassType (), codec); 
		}
		return; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.atmosphir.site.intercept.codec.json.JsonCodecFactory#getCodec(java.lang.Class)
	 */
	
	@SuppressWarnings ("unchecked")
	public  <Type>
	JsonCodec<Type> getCodec (Class<Type> type)
    {
	    final JsonCodec<Type> codec = (JsonCodec<Type>) byClass.get (type);
	    // TODO: really should search the type hierarchy [crawford]
	    return codec; 
    }

	/**
	 * 
	 * @param type
	 * @return
	 */
	
	public
	boolean hasCodec (final Class<?> type) 
	{
		return byClass.containsKey (type); 
	}

	@SuppressWarnings ("unchecked")
	public <Type>
	Type decode (final JSONObject obj)
		throws Exception
	{
		if (! obj.has (JsonConstant.TYPE)) { 
			throw new Exception ("Missing type specifier: _t"); 
		}
		final String type = obj.getString (JsonConstant.TYPE); 
		final JsonCodec<Type> codec = (JsonCodec<Type>) byType.get (type);
		if (codec == null) { 
			throw new Exception ("No codec registered for type: " + type); 
		}
		return codec.decode (obj); 
	}

	/*
	 * (non-Javadoc)
	 * @see com.atmosphir.site.intercept.codec.json.JsonCodecFactory#encode(java.lang.Object)
	 */
	
	@SuppressWarnings ("unchecked")
	public
	JSONObject encode (final Object obj)
		throws Exception
	{
		final JsonCodec codec = (JsonCodec) byClass.get (obj.getClass ()); 
		if (codec == null) { 
			throw new CodecNotFoundException ("Codec not found: " + obj.getClass().getName ()); 
		}
		return codec.encode (obj); 
	}
}

// EOF