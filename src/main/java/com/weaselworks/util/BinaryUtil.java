package com.weaselworks.util;

import java.math.*;

/**
 * 
 * @author crawford
 *
 */

public class BinaryUtil
{
	public static
	String toNibbleBits (final int b)
	{
		if (b < 0 || b >= 16) { 
			throw new IllegalArgumentException ("Invalid nibble value: " + b); 
		}
		final StringBuffer sb = new StringBuffer (); 
		
		sb.append (((b & 0x8) == 0x8) ? "1" : "0"); 
		sb.append (((b & 0x4) == 0x4) ? "1" : "0"); 
		sb.append (((b & 0x2) == 0x2) ? "1" : "0"); 
		sb.append (((b & 0x1) == 0x1) ? "1" : "0"); 
		
		return sb.toString (); 
	}
	
	public static
	int countNibbleBits (final int b)
	{
		if (b < 0 || b >= 16) { 
			throw new IllegalArgumentException ("Invalid nibble value: " + b); 
		}
		int bits = 0; 
		for (int i = 0; i < 4; i ++) { 
			final int mask = 1 << i; 
			if ((b & mask) != 0) { 
				bits ++; 
			}
		}
		return bits;		
	}
	
	public static
	String toBits (final byte b)
	{
		return toNibbleBits ((b >> 4) & 0x0f) + toNibbleBits (b & 0x0f); 
	}
	
	public static
	int countBits (final byte b)
	{
		int bits = 0; 
		for (int i = 0; i < 8; i ++) { 
			final int mask = 1 << i; 
			if ((b & mask) != 0) { 
				bits ++; 
			}
		}
		return bits;
	}
	
	/**
	 * 
	 * @param bs
	 * @return
	 */
	
	public static
	int countBits (final byte [] bs) 
	{
		int total = 0; 
		for (final byte b : bs) { 
			total += BinaryUtil.countBits (b) ;
		}
		return total; 
	}
	
	public static
	String toBits (final BigInteger val)
	{
		final byte [] bs = val.toByteArray ();
		return toBits (bs); 
	}
	
	public static
	String toBits (final byte [] bs)
	{
		final StringBuffer buf = new StringBuffer (); 
		for (int i = 0; i < bs.length; i ++) { 
			final byte b = bs [i]; 
			if (i != 0) { 
				buf.append (" ");
			}
			buf.append (toBits (b)); 
		}
		return buf.toString (); 
	}
	
	public static
	void main (final String [] args)
	{
//		for (int i = 0; i < 256; i ++) { 
//			System.out.println ("" + i + ": " + toBits ((byte) i) + " /" + countBits ((byte) i) + "/"); 
//		}
		final String [] nums = { "0", "1", "2", "255", "256", "" + (1 << 15)}; 
		for (final String num : nums) { 
			final BigInteger bigint = new BigInteger (num); 
			System.out.println ("" + bigint + ": " + toBits (bigint)); 
		}
		return; 
	}
}

// EOF