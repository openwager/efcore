package com.weaselworks.util.codegen;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class ClassTest
	extends CodegenTestSupport
{
	public static
	_Class getClass1 ()
	{
		final _Class c = new _Class (); 
		c.setPackage ("foo.bar.baz"); 
		c.setName ("Foo");
		c.addImport ("java.util.*"); 
		c.addImport ("java.util.*"); 
		c.addImport ("com.twofish.util.*"); 
		c.setVisibility (Visibility.PUBLIC);

		c.addEncodeable (FieldTest.getField1 ()); 
		c.addEncodeable (FieldTest.getField2 ()); 
		c.addEncodeable (FieldTest.getField3 ());
		c.addEncodeable (getClass2 ()); 
		return c;
		
	}
	
	public static
	_Class getClass2 ()
	{
		_Class c = new _Class (); 
		c.setName ("foo"); 
		c.setVisibility (Visibility.PRIVATE);
		c.setIsInner (true); 
		c.addEncodeable (FieldTest.getField3 ()); 
		return c;
	}
	
	@Test
	public 
	void test ()
		throws Exception
	{
		output (getClass1 ()); 
		output (getClass2 ()); 
		return; 
	}
}

// EOF
