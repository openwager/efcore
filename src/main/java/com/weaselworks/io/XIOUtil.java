package com.weaselworks.io;

import java.io.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOUtil
{
	public static final String MIME_TYPE = "application/xio"; 
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	
//	public static
//	byte [] toBytes (final Object obj)
//		throws IOException		
//	{
//		final ByteArrayOutputStream baos = new ByteArrayOutputStream (); 
//		final XIOOutputStream pos = new XIOOutputStream (baos); 
//		pos.writeObject (obj); 
//		return baos.toByteArray (); 
//		// TODO: Closing of streams
//	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	
	public static
	Pair<XIOInputStream, XIOOutputStream> getPair ()
		throws IOException
	{
		final Pair<InputStream, OutputStream> pair = IOUtil.getPipePair ();
		final XIOInputStream xis = new XIOInputStream (pair.getLeft ()); 
		final XIOOutputStream xos = new XIOOutputStream (pair.getRight ()); 
		return new Pair <XIOInputStream, XIOOutputStream> (xis, xos);
	}

	/**
	 * 
	 * @param factory
	 * @return
	 * @throws IOException
	 */
	
	public static
	Pair<XIOInputStream, XIOOutputStream> getPair (final XIOCodecFactory factory)
		throws IOException
	{
		final Pair<InputStream, OutputStream> pair = IOUtil.getPipePair ();
		final XIOInputStream xis = new XIOInputStream (pair.getLeft (), factory); 
		final XIOOutputStream xos = new XIOOutputStream (pair.getRight (), factory); 
		return new Pair <XIOInputStream, XIOOutputStream> (xis, xos);		
	}

	/**
	 * 
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	
	public static
	byte [] encodeBytes (final Object msg)
		throws IOException
	{
		return encodeBytes (msg, XIOCodecFactoryUtil.getDefaultFactory ()); 
	}
	
	/**
	 * 
	 * @param msg
	 * @param factory
	 * @return
	 * @throws IOException
	 */
	
	public static
	byte [] encodeBytes (final Object msg, final XIOCodecFactory factory)
		throws IOException
	{		
		final ByteArrayOutputStream baos = new ByteArrayOutputStream (); 
		final XIOOutputStream xos = new XIOOutputStream (baos, factory); 
		xos.writeObject (msg); 
		xos.close (); 
		return baos.toByteArray (); 
	}
	
	/**
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	
	public static
	Object decodeBytes (final byte [] bytes)
		throws IOException
	{
		return decodeBytes (bytes, XIOCodecFactoryUtil.getDefaultFactory ());
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	
	public static 
	Object decodeBytes (final byte [] bytes, final XIOCodecFactory factory)
		throws IOException
	{
		final ByteArrayInputStream bais = new ByteArrayInputStream (bytes); 
		final XIOInputStream xis = new XIOInputStream (bais, factory); 
		return xis.readObject (); 
	}
}

// EOF