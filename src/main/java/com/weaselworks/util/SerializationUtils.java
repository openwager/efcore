package com.weaselworks.util;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class SerializationUtils
{
	private
	SerializationUtils ()
	{
		return; 
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	
	public static 
	byte [] serialize (final Object obj)
		throws IOException
	{
		final ByteArrayOutputStream baos = new ByteArrayOutputStream (); 
		final ObjectOutputStream oos = new ObjectOutputStream (baos); 
		oos.writeObject (obj); 
		oos.flush (); 
		oos.close (); 
		return baos.toByteArray (); 		
	}
	
	/**
	 * 
	 * @param <Type>
	 * @param data
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	@SuppressWarnings("unchecked")
    public static <Type> 
	Type deserialize (final byte [] data) 
		throws IOException, ClassNotFoundException
	{
		final ByteArrayInputStream bais = new ByteArrayInputStream (data); 
		final ObjectInputStream oos = new ObjectInputStream (bais); 
		final Type type = (Type) oos.readObject (); 
		return type; 
	}
	
//	public static 
//	void main (final String [] args)
//		throws Exception
//	{
//		foo ("Hello, World!"); 
//		foo (new String [] { "a", "b", "c" }); 
//		return; 
//	}
//	
//	public static 
//	void foo (final Object obj)
//		throws IOException, ClassNotFoundException 
//	{
//		System.err.println (obj); 
//		final Object obj2 = deserialize (serialize (obj)); 
//		System.err.println (obj2); 
//		return; 
//	}
}

// EOF