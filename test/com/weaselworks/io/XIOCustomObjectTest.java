package com.weaselworks.io;

import org.testng.annotations.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.io.codec.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOCustomObjectTest
	extends XIOTestSupport
{
	final Foo foo1 = new Foo (true, (short) 1, 2, 3L, 4.0f, 5.0, (char) '6',
		"seven", (byte) 8, new Boolean (false), new Short ((short) 9), new Integer (10), new Long (11L), new Float (12f), new Double (13.0), 
		new Character ((char) '4'), new Byte ((byte) 15)); 

	final Foo foo2 = new Foo (false, (short) 2, 3, 4L, 5.0f, 6.0, (char) '7',
		"eight", (byte) 9, new Boolean (true), new Short ((short) 10), new Integer (11), new Long (12L), new Float (13f), new Double (14.0), 
		new Character ((char) '5'), new Byte ((byte) 16)); 

	
	@Test
	public
	void test1 ()
		throws Exception
	{
		final Object [] data = new Object [] { foo1, null, foo2 };
		
		final XIOCodecFactory factory = XIOCodecFactoryUtil.getDefaultFactory (); 
		final XIOCodec<Foo> codec = new FooCodec ();  
		factory.addCodec (codec); 
		
		test (data, factory); 
		return; 
	}

	@Test
	public
	void test2 ()
		throws Exception
	{
		final Object [] data = new Object [] { foo1, null, foo2 };
		
		final XIOCodecFactory factory = XIOCodecFactoryUtil.getDefaultFactory (); 
		final XIOCodec<Foo> codec = new ReflectionCodec<Foo> (0xbeef, Foo.class);   
		factory.addCodec (codec); 
		
		test (data, factory); 
		return; 
	}
}

// EOF