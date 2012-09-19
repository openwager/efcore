package com.weaselworks.util.codegen;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class ParameterTest
	extends CodegenTestSupport
{
	public static
	_Parameter getParameter (final String type, final String name, final boolean isFinal)
	{
		final _Parameter p = new _Parameter (); 
		p.setType (type); 
		p.setName (name); 
		p.setIsFinal (isFinal); 
		return p; 
	}
	
	@Test
	public
	void test ()
		throws Exception
	{
		output (getParameter ("String", "name", false)); 
		output (getParameter ("int", "foo", true)); 
		return; 
	}
}

// EOF