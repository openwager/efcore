package com.weaselworks.util.codegen;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class MethodTest
	extends CodegenTestSupport
{
	public static 
	_Method getMethod1 ()
	{
		final _Method m = new _Method (); 
		m.setVisibility (Visibility.PUBLIC); 
		m.setIsConstructor (true); 
		m.setType ("String");
		return m; 
	}
	
	public static
	_Method getMethod2 ()
	{
		final _Method m = new _Method (); 
		m.setVisibility (Visibility.PUBLIC); 
		m.setIsStatic (true); 
		m.setIsFinal (true); 
		m.setType ("void"); 
		m.setName ("main"); 
		m.addParameter (new _Parameter (true, "String[]", "args"));
		return m; 
	}
	
	public static
	_Method getMethod3 ()
	{
		final _Method m = new _Method (); 
		m.setVisibility (Visibility.PROTECTED); 
		m.setIsAbstract (true); 
		m.setIsFinal (true); 
		m.setType ("List<String>"); 
		m.setName ("doSomething");
		m.addParameter (new _Parameter (false, "boolean", "flag")); 
		m.addParameter (new _Parameter (false, "int", "count")); 
		m.addParameter (new _Parameter (false, "String", "name")); 
		return m; 
	}
	
	@Test
	public
	void test ()
		throws Exception
	{
		output (getMethod1 ()); 
		output (getMethod2 ());
		output (getMethod3 ()); 
		return; 
	}
}

// EOf