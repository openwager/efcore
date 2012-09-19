package com.weaselworks.util.codegen;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class BlockTest
	extends CodegenTestSupport
{
	public static
	_Block getBlock1 ()
	{
		final _Block b = new _Block (); 
		b.addCode ("int i = 0;"); 
		b.addEncodeable (getFor1 ()); 
		b.addCode ("return;"); 
		return b; 
	}
	
	public static
	_For getFor1 () 
	{
		final _For f = new _For (); 
		f.setClause ("int i = 0; i < 10; i ++");
		f.addCode ("// UNIMPLEMENTED");
		return f; 
	}
	
	public static
	_Do getDo1 ()
	{
		final _Do d = new _Do ();
		d.setClause ("somethingIsTrue ()");
		d.addCode ("System.err.println (System.currentTimeMillis ());");
		d.addCode ("i++;"); 
		return d; 
	}
	
	public static
	_While getWhile1 ()
	{
		final _While w = new _While (); 
		w.setClause ("somethingIsTrue ()"); 
		return w; 
	}
	
	@Test
	public
	void test ()
		throws Exception
	{
		output (getBlock1 ()); 
		output (getFor1 ()); 
		output (getWhile1 ()); 
		output (getDo1 ()); 
		return ;
	}
}

// EOF