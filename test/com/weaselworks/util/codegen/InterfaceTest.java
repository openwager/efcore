package com.weaselworks.util.codegen;

import java.io.*;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class InterfaceTest
	extends CodegenTestSupport
{
	public static
	_Interface getInterface1 ()
	{
		final _Interface i = new _Interface (); 
		i.setName ("Foo"); 
		i.setVisibility (Visibility.PUBLIC); 
		i.addImport ("java.util.*"); 
		i.addImport ("foo.bar.baz");
		i.setPackage ("com.foo.bar.baz"); 
		i.addEncodeable (getInterface2()); 
		return i; 
	}
	
	public static 
	_Interface getInterface2 ()
	{
		final _Interface i = new _Interface (); 
		i.setName ("JSON"); 
		i.setVisibility (Visibility.PUBLIC);
		i.setIsInner (true); 
		
		final _Interface i2 = new _Interface (); 
		i2.setName ("F");
		i2.setIsInner (true); 
		i2.setVisibility (Visibility.PUBLIC);
		i2.addEncodeable (FieldTest.getField3 ());
		i.addEncodeable (i2); 
		return i; 
	}
	
	@Test
	public
	void test ()
		throws IOException
	{
		output (getInterface1 ()); 
		output (getInterface2 ()); 
		return; 
	}
}

// EOF