package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class HelpCommand
	extends CommandSupport
{
	public 
	HelpCommand ()
	{
		super (NAME, USAGE);
		return; 
	}
	
	public static final String NAME = "help";
	public static final String USAGE = "Used to list the available command descriptions.";    
	
	public 
	void invoke (final List<String> args, final PrintWriter pw) 
	{ 
	    final Map<String, Command> commands = getInterpreter ().getCommands (); 

	    pw.println ("The following commands are available:");
	    for (final String name : commands.keySet ()) { 
	    	final Command cmd = commands.get (name); 
	        if (cmd instanceof HiddenCommand) {
	            continue;
	        }
	        pw.println ("  " + name + " - " + cmd.getUsage ());
	    }
	    return;            
}
}

// EOF