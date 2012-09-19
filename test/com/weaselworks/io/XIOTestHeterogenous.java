package com.weaselworks.io;

import java.io.*;

import org.testng.*;
import org.testng.annotations.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOTestHeterogenous
	extends XIOTestSupport
{
	@Test
	public 
	void test1 ()
		throws IOException
	{
		final Struct s1 = new Struct (); 
		s1.flag= true; 
		s1.num0 = 0x12; 
		s1.num1 = 123; 
		s1.num2 = 12345 ; 
		s1.num3 = 1234567L; 
		s1.num4 = 3.14159f;
		s1.num5 = 1.918293233; 
		System.out.println (s1); 
		
		final Pair<XIOInputStream, XIOOutputStream> pair = getPair (); 
		final XIOInputStream xis = pair.getLeft (); 
		final XIOOutputStream xos = pair.getRight (); 

		xos.writeBoolean (s1.flag); 
		xos.writeByte (s1.num0); 
		xos.writeShort (s1.num1); 
		xos.writeInteger (s1.num2); 
		xos.writeLong (s1.num3); 
		xos.writeFloat (s1.num4); 
		xos.writeDouble (s1.num5); 
		
		final Struct s2 = new Struct (); 
		s2.flag = xis.readBoolean (); 
		s2.num0 = xis.readByte (); 
		s2.num1 = xis.readShort (); 
		s2.num2 = xis.readInteger (); 
		s2.num3 = xis.readLong (); 
		s2.num4 = xis.readFloat (); 
		s2.num5 = xis.readDouble (); 
		System.out.println (s2); 
		
		Assert.assertTrue (s1.equals (s2)); 
		return; 
	}

	public static class Struct
	{
		public boolean flag; 
		public byte num0;
		public short num1; 
		public int num2; 
		public long num3; 
		public float num4; 
		public double num5; 
		
		public
		boolean equals (final XIOTestHeterogenous.Struct obj)
		{
			if (! (obj instanceof XIOTestHeterogenous.Struct)) { 
				return false;
			}
			final XIOTestHeterogenous.Struct other = (XIOTestHeterogenous.Struct) obj; 
			return this.flag == obj.flag 
				&& this.num0 == other.num0
				&& this.num1 == other.num1
				&& this.num2 == other.num2
				&& this.num3 == other.num3
				&& this.num4 == other.num4
				&& this.num5 == other.num5 
				; 
		}
		
		public
		String toString () 
		{
			return StringUtil.toString (this); 
		}
	}
}


// EOF