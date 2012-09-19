package com.weaselworks.util;

import java.util.*;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class ClassUtilTest
{
	@Test
	public 
	void test1 ()
	{
		final List<Class<?>> ifcs = ClassUtil.getInterfaces (C.class); 
		System.err.println ("Interfaces for: " + C2.class.getName ());
		for (final Class<?> ifc : ifcs) { 
			System.out.println (ifc.getSimpleName ()); 
		}
		return; 
	}
	
	public interface A 
	{
		// EMPTY
	}

	public interface B
		extends A
	{
		// EMPTY
	}
	
	public interface C
	{
		// EMPTY
	}
	
	public static class C1	
		implements B
	{
		// EMPTY
	}
	
	public static class C2
		extends C1
			implements C
	{
		// EMPTY
	}
}

// EOF
