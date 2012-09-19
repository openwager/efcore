package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class DumpStateCommand
	extends CommandSupport
		implements HiddenCommand
{
	public static final String NAME = "dumpState"; 
	public static final String HELP = "dumps the state table"; 
	
	public DumpStateCommand ()
	{
		super (NAME, HELP);
		return; 
	}
	

	@Override
	public void invoke (List<String> args, PrintWriter pw)
		throws Exception 
	{
		final Map<String, Object> map = getInterpreter ().getState (); 
		if (map.keySet ().isEmpty ()) { 
			pw.println ("EMPTY"); 
		} else { 
			pw.println ("Internal state map: {"); 
			for (final String key : map.keySet ()) { 
				pw.println ("  '" + key + "' := " + map.get (key)); 			
			}
			pw.println ("}"); 		
		}
		return; 
	}
}

// EOF