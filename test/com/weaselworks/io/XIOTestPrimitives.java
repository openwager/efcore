package com.weaselworks.io;

import java.io.*;

import org.testng.annotations.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOTestPrimitives
	extends XIOTestSupport
{
	/**
	 * 
	 * @return
	 */
		
	@Test
	@SuppressWarnings("unused")
	public
	void testNull ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		for (final int i : new Sequence (0, 10)) { 
			xos.writeNull (); 
			xis.readNull (); 
		}
		return; 
	}

	
	@Test
	public
	void testBoolean ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final boolean [] sequence = new boolean [] { 
			true, false, true, true, false, true, false, false, false, true
		};
		for (final boolean val : sequence) { 
			xos.writeBoolean (val);
		}
		for (final boolean expected : sequence) { 
			expectBoolean (xis, expected); 
		}
		return; 
	}
	
	
	@Test
	public
	void testByte ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final byte [] sequence = new byte [] { 
			0x00, 0x01, 0x02, 0x03, 0x10, 0x40, (byte) 0x90, (byte) 0xab, (byte) 0xe0, (byte) 0xf9, (byte) 0xff
		};
		for (final byte val : sequence) { 
			xos.writeByte (val);
		}
		for (final byte expected : sequence) { 
			expectByte (xis, expected); 
		}
		return; 
	}
	
	@Test
	public
	void testShort ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final short [] sequence = new short [] { 
			Short.MIN_VALUE, -123, -1, 0, 1, 123, Short.MAX_VALUE
		}; 
		for (final short val : sequence) { 
			xos.writeShort (val);
		}
		for (final short expected : sequence) { 
			expectShort (xis, expected); 
		}
		return; 
	}
	
	@Test
	public
	void testInteger ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final int [] sequence = { 
			Integer.MIN_VALUE, -123, -1, 0, 1, 123, Integer.MAX_VALUE
		}; 
		for (final int val : sequence) { 
			xos.writeInteger (val);
		}
		for (final int expected : sequence) { 
			expectInteger (xis, expected); 
		}
		return; 
	}

	@Test
	public 
	void testLong ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final long [] sequence = { 
			Long.MIN_VALUE, -123L, -1L, 0L, 1L, 123L, Long.MAX_VALUE
		}; 
		for (final long val : sequence) { 
			xos.writeLong (val);
		}
		for (final long expected : sequence) { 
			expectLong (xis, expected); 
		}
		return; 
	}
	
	@Test
	public
	void testFloat ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final float  [] sequence = { 
			Float.MIN_VALUE, -123.123f, -1f, 0f, 1f, 123.123f, Float.MAX_VALUE
		}; 
		for (final float val : sequence) { 
			xos.writeFloat (val);
		}
		for (final float expected : sequence) { 
			expectFloat (xis, expected); 
		}
		return; 
		
	}
	
	@Test
	public
	void testDouble ()
		throws IOException
	{
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 
		final double [] sequence = { 
			Double.MIN_VALUE, -123.1233, -1, 0, 1, 123.1233, Double.MAX_VALUE
		}; 
		for (final double val : sequence) { 
			xos.writeDouble (val);
		}
		for (final double expected : sequence) { 
			expectDouble (xis, expected); 
		}
		return; 
		
	}	
}

// EOF