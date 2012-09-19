package com.weaselworks.util.interp;

import java.io.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class InterpreterTest
{
	public static
	void main (final String [] args)
		throws Exception
	{
		// Start a background thread so we can show how the quit command quits
		// the interpreter but it no longer quits the JVM
		
		final Runnable r = new Runnable () {
			public void run () {
				for (int i = 0; i < 10; i ++) { 
					System.err.println ("[" + i + "]"); 
					Util.delay (Duration.SECOND); 
				}
				return;
			} 			
		};
		new Thread (r).start (); 
		
		final Interpreter i = new Interpreter ();
		i.addCommand (new QuitCommand ()); 
		final InputStreamReader isr = new InputStreamReader (System.in); 
		final OutputStreamWriter osw = new OutputStreamWriter (System.out); 
		i.interpret (isr, osw); 
		return; 
	}
}

// EOF