package com.weaselworks.codec.xml;

import java.io.*;

import org.w3c.dom.*;

/**
 * 
 * @author crawford
 *
 * @param <Type>
 */

public interface XMLCodec <Type>
{
	public
	String getNodeName (); 
	
	public
	Class<? extends Type> getType (); 
	
	public
	Type decode (final InputStream is)
		throws Exception; 

	public
	Type decode (Element doc)
		throws Exception; 

	public
	void encode (final Type type, final OutputStream os)
		throws Exception; 

	public
	Element encode (Type type, Document doc)
		throws Exception; 

	public
	void setFactory (final XMLCodecFactory factory); 
}

// EOF