package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 *  A Command that echos the command line arguments passed to it.
 */

class EchoCommand
    extends CommandSupport
{
    public
    EchoCommand ()
    {
        super ("echo", "Echos the command-line arguments.");
        return;
    }

    /**
     *  @see Command#invoke
     */

    public
    void invoke (final List<String> args, final PrintWriter out)
    {
    	int cnt = 0; 
    	for (final String arg : args) {
    		if (cnt != 0) { 
    			if (cnt > 1) { 
    				out.print (" ");
    			}
    			out.print (arg);
    		}
    		cnt ++; 
	    }
    	out.println ();
        return;
    }
}

// EOF
