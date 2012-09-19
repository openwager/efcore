package com.weaselworks.io;

import java.io.*;

import org.testng.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOTestSupport
{
	protected
	void log (final String str)
	{
		System.err.println (str); 
		return; 
	}
	
	public
	Pair<XIOInputStream, XIOOutputStream> getPair ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = XIOUtil.getPair (); 
		return pair;  
	}

	public
	Pair<XIOInputStream, XIOOutputStream> getPair (final XIOCodecFactory factory)
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = XIOUtil.getPair (factory); 
		return pair;  
	}

	public static
	void expect (final XIOInputStream xis, final Object expected)
		throws IOException
	{
		final Object obj = xis.readObject ();
		System.err.println ("    READ [" + obj + "]");
		System.err.println ("EXPECTED [" + expected + "]"); 
		if (! Util.isEqual (expected, obj)) { 
			Assert.fail ("" + expected + " != " + obj); 
		}
		return; 
	}	

	public
	void test (final Object [] objs) 
		throws Exception
	{
		test (objs, getPair ()); 
		return; 
	}

	public
	void test (final Object [] objs, final XIOCodecFactory factory) 
		throws Exception
	{
		test (objs, getPair (factory)); 
		return; 
	}

	public 
	void test (final Object [] objs, final Pair<XIOInputStream, XIOOutputStream> pair)
		throws Exception
	{ 
		final XIOOutputStream xos = pair.getRight (); 
		for (final Object obj : objs) { 
			xos.writeObject (obj); 
		}
		
		final XIOInputStream xis = pair.getLeft (); 
		for (final Object expected : objs) { 
			expect (xis, expected); 
		}
		return; 
	}
	
	protected
	void expectBoolean (final XIOInputStream xis, final Boolean expected)
		throws IOException
	{
		final boolean val = xis.readBoolean (); 
		log ("expectBoolean (" + expected + ", " + val + ")"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.booleanValue ()); 
		}
		return; 
	}
	
	protected 
	void expectByte (final XIOInputStream xis, final Byte expected)
		throws IOException
	{
		final byte val = xis.readByte (); 
		log ("expectByte (" + expected + ", " + val + ")"); 
		log ("EXPECTED [" + HexUtil.encodeByte (expected) + "]"); 
		log ("RECEIVED [" + HexUtil.encodeByte (val) + "]"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.byteValue ()); 
		}
		return;
	}
	
	protected 
	void expectShort (final XIOInputStream xis, final Short expected)
		throws IOException
	{
		final short val = xis.readShort (); 
		log ("expectShort (" + expected + ", " + val + ")"); 
		log ("EXPECTED [" + HexUtil.encodeShort (expected) + "]"); 
		log ("RECEIVED [" + HexUtil.encodeShort (val) + "]"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.shortValue ()); 
		}
		return; 
	}

	protected
	void expectInteger (final XIOInputStream xis, final Integer expected)
		throws IOException
	{
		final int val = xis.readInteger (); 
		log ("expectInteger (" + expected + ", " + val + ")"); 
		log ("EXPECTED [" + HexUtil.encodeInt (expected) + "]"); 
		log ("RECEIVED [" + HexUtil.encodeInt (val) + "]"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.intValue ()); 
		}
		return; 
	}
	
	protected
	void expectLong (final XIOInputStream xis, final Long expected)
		throws IOException
	{
		final long val = xis.readLong (); 
		log ("expectLong (" + expected + ", " + val + ")"); 
		log ("EXPECTED [" + HexUtil.encodeLong (expected) + "]"); 
		log ("RECEIVED [" + HexUtil.encodeLong (val) + "]"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.longValue ()); 
		}
		return; 
	}
	
	protected
	void expectFloat (final XIOInputStream xis, final Float expected)
		throws IOException
	{
		final float val = xis.readFloat (); 
		log ("expectFloat (" + expected + ", " + val + ")"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.floatValue ()); 
		}
		return; 
	}
	
	protected 
	void expectDouble (final XIOInputStream xis, final Double expected)
		throws IOException
	{
		final double val = xis.readDouble (); 
		log ("expectDouble (" + expected + ", " + val + ")"); 
		if (expected != null) { 
			Assert.assertEquals (val, expected.doubleValue ()); 
		}
		return; 
	}
	
//	protected 
//	void expectString (final XIOInputStream xis, final String expected)
//		throws IOException
//	{
//		final String val = xis.readString (); 
//		log ("expectString (" + expected + ", " + val + ")"); 
//		if (expected != null) { 
//			Assert.assertTrue (Util.isEqual (val, expected)); 
//		}
//		return; 
//	}
	
//	protected
//	void expectDate (final XIOInputStream xis, final Date expected)
//		throws IOException
//	{
//		final Date val = xis.readDate (); 
//		log ("expectDate (" + expected + ", " + val + ")"); 
//		if (expected != null) { 
//			Assert.assertTrue (Util.isEqual (val, expected)); 
//		}
//		return; 
//	}
}

// EOF