package com.weaselworks.codec.xio;

import java.util.*;

import org.apache.log4j.*;

import com.weaselworks.io.codec.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOCodecFactorySupport
	implements XIOCodecFactory
{
	protected static final Logger logger = Logger.getLogger (XIOCodecFactorySupport.class); 
	
	public
	XIOCodecFactorySupport ()
	{
		return; 
	}

	@SuppressWarnings ("unchecked")
	public
	void addBaseCodecs ()
	{
		addCodec (new DateCodec ()); 
		addCodec (new StringCodec ()); 
		addCodec (new MapCodec ()); 
		addCodec (new ListCodec ()); 		
		addCodec (new SetCodec ());
		for (final int objectId : PrimitiveCodec.OBJECT_IDS) { 
			addCodec (new PrimitiveCodec (objectId)); 
		}
		return; 
	}
	
	public
	void addCodec (final XIOCodec<?> codec)
    {
	   final Class<?> type = codec.getType (); 
	   if (codecs.containsKey (type)) { 
		   throw new IllegalArgumentException ("Type already registered: " + type.getName ()); 
	   }
	   final int objectId = codec.getObjectId (); 
	   if (byid.containsKey (objectId)) {
		   throw new IllegalArgumentException ("Object id already registered: 0x" + HexUtil.encodeLong (objectId));
	   }
	   codecs.put (type, codec); 
	   byid.put (objectId, codec); 
	   return; 
    }
	
	public
	void addCodecs (final XIOCodec<?> [] codecs)
	{
		for (final XIOCodec<?> codec: codecs) { 
			addCodec (codec); 
		}
		return; 
	}

	protected Map<Class<?>, XIOCodec<?>> codecs = new HashMap<Class<?>, XIOCodec<?>> (); 
	
	public 
	Collection<XIOCodec<?>> getCodecs () 
	{
		return codecs.values (); 
	}
	
	protected Map<Class<?>, XIOCodec<?>> cached = new HashMap<Class<?>, XIOCodec<?>> (); 
	
	@SuppressWarnings("unchecked")
    public <Type> 
	XIOCodec<? super Type> getCodec (final Class<Type> type)
    {
		// See if we have an explicit code for objects of this type
		
		XIOCodec<? super Type> codec = (XIOCodec<? super Type>) codecs.get (type); 
		if (codec != null) { 
			return codec;
		}
		
		// Otherwise, check and see if we've cached an implicit codec for the type
		
		codec = (XIOCodec<? super Type>) cached.get (type);
		if (codec != null) { 
			return codec; 
		}
		
		// Failing that, let's search through the codes that we do have and see
		// if there isn't one that's suitable based on an interface
		
		for (final Class<?> ifc : ClassUtil.getInterfaces (type)) {
			codec = (XIOCodec <? super Type>) codecs.get (ifc); 
			if (codec != null) { 
				logger.info ("Mapping interface codec " + type.getSimpleName () + " -> " + codec.getType ().getSimpleName ()); 
				cached.put (type, codec); 
				return codec; 
			}
		}
		
		return null; 
    }
	
	protected Map<Integer, XIOCodec<?>> byid = new HashMap<Integer, XIOCodec<?>> (); 

	public 
	XIOCodec<?> getCodec (final int objectId)
    {
	    return byid.get (objectId); 
    }
}

// EOF
