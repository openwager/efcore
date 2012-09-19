package com.weaselworks.util;

public class StringUtilTest
{
	public static
	void main (final String [] args)
	{
		final Foo2 f1 = new Foo2 (123, "green", null); 
		final Foo2 f2 = new Foo2 (3, "yellow", f1); 
		System.err.println (StringUtil.curlyToString (f1)); 
		System.err.println (StringUtil.curlyToString (f2)); 
		f1.setSubfoo (f2); 
		System.err.println (StringUtil.curlyToString (f2)); 
		return; 
	}
}

// EOF
