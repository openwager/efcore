package com.weaselworks.util;

import org.apache.log4j.*;
import org.testng.*;
import org.testng.annotations.*;

import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class EquivalentTest
{
	protected static final Logger logger = Logger.getLogger (EquivalentTest.class); 
	
	@Test
	public
	void test1 ()
		throws Exception
	{
		final Foo foo1 = new Foo (true, (short) 1, 2, 3L, 4.0f, 5.0, (char) '6',
			"seven", (byte) 8, new Boolean (false), new Short ((short) 9), new Integer (10), new Long (11L), new Float (12f), new Double (13.0), 
			new Character ((char) '4'), new Byte ((byte) 15)); 
		final Foo foo2 = new Foo (true, (short) 1, 2, 3L, 4.0f, 5.0, (char) '6',
			"seven", (byte) 8, new Boolean (false), new Short ((short) 9), new Integer (10), new Long (11L), new Float (12f), new Double (13.0), 
			new Character ((char) '4'), new Byte ((byte) 15)); 
		
		Assert.assertTrue (Util.fieldEquals (foo1, foo2)); 
		return; 
	}
}

// EOF