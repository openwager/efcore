package com.weaselworks.util;

import java.io.*;
import java.util.regex.*;

/**
 * A utility application used for experimenting with regular expressions. 
 * 
 * @author crawford
 *
 */

public class RegexTester
{
	/**
	 * A command-line application for experimenting with regular expressions. </p> 
	 * 
	 * <pre>
	 * Regex Tester
	 * pattern> \d{3}-\d{4}
	 * test> 123-1231
	 * TRUE\d
	 * test> 123-123
	 * FALSE
	 * test> 
	 * </pre>
	 * 
	 * @param args
	 * @throws IOException
	 */
	
	@SuppressWarnings("deprecation")
    public static
	void main (final String [] args)
		throws IOException
	{
		System.out.println ("Regex Tester"); 
		final DataInputStream dis = new DataInputStream (System.in);
		Pattern pattern; 
		
		restart:
		while (true) { 			
			System.out.print ("pattern> "); 
			final String patternStr = dis.readLine ();
			if (! StringUtil.isEmpty (patternStr)) { 
				pattern = Pattern.compile (patternStr); 
			} else { 
				break;				
			}
			while (true) { 
				System.out.print ("test> ");
				final String testStr = dis.readLine (); 
				if (StringUtil.isEmpty (testStr)) { 
					continue restart; 
				} 
				System.out.println (pattern.matcher (testStr).matches () ? "TRUE" : "FALSE"); 
			}
		}

		return; 
	}
}

// EOF
