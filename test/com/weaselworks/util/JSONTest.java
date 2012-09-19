package com.weaselworks.util;

import java.util.*;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class JSONTest
{
	protected static
	void dump (final Object obj)
	{
		System.err.println ("= " + ( obj != null ? obj.getClass ().getName () : "") + " =");
		System.err.println (obj); 
		System.err.println ("---------------"); 
		final String str = JSONUtil.toJson (obj); 
		System.err.println (str); 
		System.err.println ("============"); 
		return; 
	}
	
	@Test
	public
	void test1 ()
		throws Exception
	{
		dump (null); 
		dump ("Testing"); 
		dump ((byte) 1); 
		dump ((short) 2); 
		dump (3); 
		dump (4L); 
		dump (5.0f); 
		dump (6.0); 
		return; 
	}

	@Test
	public
	void test2 ()
		throws Exception
	{
		final String [] strings = { "one", "two", "three" };
		final List<String> list = Arrays.asList (strings); 
		final Set<String> set = new HashSet<String> ();
		set.addAll (list); 
		
		dump (strings); 
		dump (list); 
		dump (set); 
		return; 		
	}

	@Test
	public
	void test3 ()
		throws Exception
	{
		final Map<String, Object> map = getMap (); 
		dump (map); 
		map.put ("map", getMap ()); 
		dump (map); 
		return; 
	}

	public static 
	Map<String, Object> getMap ()
	{
		final Map<String, Object> map = new HashMap<String, Object> (); 
		final String [][] ss = {
			{ "a", "b" }, 
			{ "c", "d" }, 
			{ "e", "f" }
		}; 
		for (final String [] s : ss) { 
			map.put (s[0], s[1]); 
		}
		return map; 
	}
	
	@Test
	public
	void test4 ()
		throws Exception
	{
		final TestBean b = new TestBean (); 
		b.setId (123L); 
		b.setName ("Fred"); 
		b.setHappy (true); 
		b.setWeight (2323); 
		b.setProperties (getMap ()); 
		dump (b); 
		return; 
	}

	
	@Test
	public
	void test5 ()
		throws Exception
	{
		
	}

}

// EOF