package com.weaselworks.util.codegen;

import java.io.*;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class PropertyTest
	extends CodegenTestSupport
{
	@Test
	public 
	void test1 ()
		throws IOException
	{
		final _Class c = ClassTest.getClass1 ();
		
		final _Property p1 = new _Property (); 
		p1.setType ("String"); 
		p1.setName ("name");
		
		final _Property p2 = new _Property (); 
		p2.setType ("Vehicle"); 
		
		final _Property p3 = new _Property (); 
		p3.setIsStatic (true); 
		p3.setType ("Set<Foo>"); 
		p3.setName ("foos"); 
		
		c.addEncodeable (p1); 
		c.addEncodeable (p2); 
		c.addEncodeable (p3); 
		output (c); 
		return; 
	}
}

// EOf