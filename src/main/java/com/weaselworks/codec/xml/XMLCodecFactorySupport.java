package com.weaselworks.codec.xml;

import java.io.*;
import java.util.*;

import org.w3c.dom.*;

import com.weaselworks.codec.*;

/**
 * 
 * @author crawford
 *
 */

public class XMLCodecFactorySupport
	implements XMLCodecFactory
{
	public
	XMLCodecFactorySupport ()
	{
		return; 
	}
	
	protected  Map<Class<?>, XMLCodec<?>> codecs = new HashMap<Class<?>, XMLCodec <?>> (); 
	protected Map<String, XMLCodec<?>> codecsByName = new HashMap<String, XMLCodec<?>> ();

	public
	void addCodecs (final List<XMLCodec<?>> codecs) { 
		for (final XMLCodec<?> codec : codecs) { 
			addCodec (codec); 
		}
		return; 
	}
	
	public 
	void addCodecs (final XMLCodec<?> [] codecs) { 
		for (final XMLCodec<?> codec : codecs) { 
			addCodec (codec); 
		}
		return;
	}
	
	public
	void addCodec (final XMLCodec<?> codec)
	{
		setCodec (codec.getType (), codec); 
		return; 
	}
	
	public
	void setCodec (final Class<?> type, final XMLCodec<?> codec)
	{
		codecs.put (type, codec); 
		if (codec.getNodeName () != null) {
			codecsByName.put (codec.getNodeName (), codec); 
		}
		codec.setFactory (this); 
		return; 
	}
	
	@SuppressWarnings ("unchecked")
	public <Type> 
	XMLCodec<Type> getCodec (Class<?> type)
		throws CodecNotFoundException 
	{
		if (codecs.containsKey (type)) { 
			return (XMLCodec<Type>) codecs.get (type) ;
		} else { 
			throw new CodecNotFoundException ("Codec not found: " + type.getName ());  
		}
		
		// NOT REACHED
	}

	public <Type>
	Type decode (final Class<Type> type, final InputStream is)
		throws Exception
	{
		final XMLCodec<Type> codec = getCodec (type); 
		return codec.decode (is); 
	}

	public <Type> Type decode (Class<Type> type, Element e)
		throws Exception 
	{
//		System.err.println (e); 
//		XMLUtil.dump (e, System.out); 
		final XMLCodec<Type> codec = getCodec (type); 
		return codec.decode (e); 
	}
	
	public <Type>
	Element encode (final Type obj, final Document doc)
		throws Exception
	{
		final Class<Type> type = (Class<Type>) obj.getClass (); 
		final XMLCodec<Type> codec = getCodec (type);
		if (codec == null) { 
			throw new CodecNotFoundException ("Codec not found: " + type.getName ());  
		}
		return codec.encode (obj, doc);
	}
	
	@SuppressWarnings ("unchecked")
	public <Type>
	Type decode (final Element e)
		throws Exception
	{	
		String nodeName = e.getNodeName ();
		if (nodeName.contains (":")) { 
			nodeName = nodeName.substring (nodeName.indexOf (':') + 1); 
		}
		final XMLCodec<?> codec = codecsByName.get (nodeName); 
		if (codec == null) { 
			throw new CodecNotFoundException ("Codec not found: " + nodeName);  
		}
		return (Type) codec.decode (e); 	
	}	

}

// EOF