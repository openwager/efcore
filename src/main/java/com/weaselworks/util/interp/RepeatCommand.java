package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 *  A Command that provides generic looping support.
 */

class RepeatCommand
    extends CommandSupport
{
    public
    RepeatCommand ()
    {
        super ("repeat", "execute a command a number of times");
        return;
    }

    protected static final String USAGE = "USAGE: repeat <num> <command> [<arg1> [<arg2> ... ]]";

    /**
     *  @see Command#invoke
     */

    public
    void invoke (final List<String> args, final PrintWriter out)
    {
        // Verify the command-line arguments

        if (args.size () < 3) {
            out.println (USAGE);
            return;
        }

        args.remove (0); 
        int cnt = Integer.parseInt (args.remove (0)); 
        
        while (cnt -- > 0) {
            getInterpreter ().invoke (args, out);
        }

        return;
    }
}

// EOF
