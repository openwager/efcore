package com.weaselworks.io;

import java.io.*;
import java.util.*;

import org.testng.*;
import org.testng.annotations.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOObjectTest
	extends XIOTestSupport
{
	@Test
	public
	void testStrings ()
	throws Exception
	{
		test (new String [] { "a", "b", "sdfasdfasdfasdF", null }); 		
		return; 
	}
	
	public static
	void expect (final XIOInputStream xis, final Object expected)
		throws IOException
	{
		final Object obj = xis.readObject ();
		System.err.println ("READ [" + obj + "]"); 
		if (! Util.isEqual (expected, obj)) { 
			Assert.fail ("" + expected + " != " + obj); 
		}
		return; 
	}	
	
	@Test 
	public
	void testDates ()
		throws Exception
	{
		final Date [] dates = { new Date (), new Date (1), null }; 
		test (dates); 
		return; 
	}
		
	
	@Test 
	public 
	void testMap ()
		throws Exception
	{
		final Map<String, Date> map = new HashMap<String, Date> (); 
		map.put ("a", new Date ()); 
		map.put ("b", new Date (1)); 
		test (new Map [] { map, null }); 
		return; 
	}
	
	@Test 
	public
	void testSet ()
		throws Exception
	{
		final Set<Object> set = new HashSet<Object> (); 
		set.add (1); 
		set.add (new Date ()); 
		set.add ("Hello!"); 
		test (new Set [] { set }); 
		return; 
	}
	
	@Test
	public
	void testList ()
		throws Exception
	{
		final List<Object> list = new ArrayList<Object> (); 
		list.add (1); 
		list.add ("hello"); 
		list.add (new ArrayList<Object> ()); 
		final Object [] data = new Object [] { list, null }; 
		test (data); 
		return; 
	}
}

// EOF
