package com.weaselworks.util;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class GetterSetters
{
	public static
	void main (final String [] args)
		throws Exception
	{
		final BufferedReader br = new BufferedReader (new InputStreamReader (System.in)); 
		
		System.out.println ("USAGE: <type>/<field>,...\n");
		
		while (true) { 
			System.out.print ("<t>(/<f>),> "); 
			final String s = br.readLine (); 
			if (StringUtil.isEmpty (s)) { 
				break; 
			}
			final String [] fields = s.split (","); 
			for (final String field : fields) { 
				final String [] parts = field.split ("/"); 
				final String type = parts[0];
				final String lower; 
				if (parts.length == 1) {
					lower = Character.toLowerCase (type.charAt (0)) + type.substring (1); 
				} else if (parts.length == 2) { 
					lower = parts [1];
				} else { 
					throw new Exception ("Invalid."); 
				}
				final String upper = Character.toUpperCase (lower.charAt (0)) + lower.substring (1); 
				System.out.println ("protected " + type + " " + lower + ";"); 
				System.out.println ("public " + type + " get" + upper + " () { return this." + lower + "; }"); 
				System.out.println ("public void set" + upper + " (final " + type + " " + lower + ") { this."+ lower + " = " + lower + "; return; }");
				System.out.println (); 
			}
		}
		
		return; 
	}
}

// EOF