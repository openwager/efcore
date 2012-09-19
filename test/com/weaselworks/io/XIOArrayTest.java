package com.weaselworks.io;

import java.io.*;

import org.testng.*;
import org.testng.annotations.*;

import com.weaselworks.util.*;

/**
 * A set of unit tests for exercising the arrays support in the XIO library. 
 * 
 * @author crawford
 *
 */

public class XIOArrayTest
	extends XIOTestSupport
{
	@Test
	public
	void testBoolean ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final boolean [] data = new boolean [] { false, true, false, true, false, true }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeBooleans (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final boolean [] data2 = xis.readBooleans ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 
	}	
	
	@Test
	public
	void testByte ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final byte [] data = new byte [] { 0x00, 0x01, 0x02, 0x03, 0x04, Byte.MAX_VALUE, Byte.MIN_VALUE }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeBytes (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final byte [] data2 = xis.readBytes ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 		
	}
	
	@Test
	public
	void testShort ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final short [] data = new short [] { 0, 1, 2, 3, 4, 5, Short.MAX_VALUE, Short.MIN_VALUE }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeShorts (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final short [] data2 = xis.readShorts ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 
	}
	
	@Test
	public 
	void testInteger ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final int [] data = new int [] { 0, 1, 2, 3, 4, 5, Integer.MAX_VALUE, Integer.MIN_VALUE }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeIntegers (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final int [] data2 = xis.readIntegers ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 		
	}
	
	@Test
	public
	void testLong ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final long [] data = new long [] { 0, 1, 2, 3, 4, 5, Long.MAX_VALUE, Long.MIN_VALUE }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeLongs (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final long [] data2 = xis.readLongs ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 		
	}
	
	@Test
	public 
	void testFloat ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final float [] data = new float [] { 0.0f, 1.23f, 234.234f, Float.MAX_VALUE, Float.MIN_VALUE }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeFloats (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final float [] data2 = xis.readFloats ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 		
	}
	 
	@Test
	public
	void testDouble ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final double [] data = new double [] { 0.0, 3.14159265358, Double.MAX_VALUE, Double.MIN_VALUE }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeDoubles (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final double [] data2 = xis.readDoubles ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 				
	}
	
	@Test
	public 
	void testChar ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair ();
		final char [] data = new char [] { 'a', 'b', 'c', 'd', 'e', 'f' }; 
		final XIOOutputStream xos = pair.getRight (); 
		xos.writeChars (data); 
		final XIOInputStream xis = pair.getLeft (); 
		final char [] data2 = xis.readChars ();
		Assert.assertTrue (Util.arrayEquals (data, data2)); 
		return; 				
	}
	
}

// EOF