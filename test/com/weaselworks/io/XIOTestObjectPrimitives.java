package com.weaselworks.io;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOTestObjectPrimitives
	extends XIOTestSupport
{	
	@Test
	public 
	void test1 ()
		throws Exception
	{
		final Object [] data = { 
			new Boolean (true), 
			new Boolean (false), 
			new Byte ((byte) 12),
			new Short ((short) 123), 
			new Integer (123),
			new Long (123L), 
			new Float (1.23f), 
			new Double (1.23), 
			new Character ('h'), 
			null 
		};
		
		test (data); 
		return;
	}
}

// EOF
