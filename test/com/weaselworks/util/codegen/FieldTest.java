package com.weaselworks.util.codegen;

import java.io.*;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class FieldTest
	extends CodegenTestSupport
{
	public static 
	_Field getField1 ()
	{
		final _Field f = new _Field (); 
		f.setType ("String");
		f.setName ("foo"); 
		f.setVisibility (Visibility.PUBLIC);
		return f; 
	}
	
	public static
	_Field getField2 ()
	{
		final _Field f = new _Field (); 
		f.setType ("String");
		f.setName ("foo");
		f.setIsFinal (true); 
		f.setVisibility (Visibility.PROTECTED); 
		return f; 
	}
	
	public static
	_Field getField3 ()
	{
		final _Field f = new _Field (); 
		f.setType ("String");
		f.setName ("foo");
		f.setIsStatic (true); 
		f.setVisibility (Visibility.PRIVATE); 
		return f; 
	}
	
	@Test
	public
	void test1 ()
		throws IOException
	{
		output (getField1 ()); 
		output (getField2 ()); 
		return ;
	}
}

// EOF