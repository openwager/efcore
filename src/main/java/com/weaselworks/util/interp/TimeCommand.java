package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

import com.weaselworks.util.*;

/**
 *  Used to time the execution of another command.
 */

class TimeCommand
    extends CommandSupport
{
    public
    TimeCommand ()
    {
        super ("time", "time another command (usage: time <command ...>");
        return;
    }

    /**
     *  @see Command#invoke
     */

    public
    void invoke (final List<String> args, final PrintWriter out)
    {
        // Verify the command-line arguments

        if (args.size () == 1) {
            out.println ("time <command> [<arg1> [<arg2> ... ]]");
            return;
        }

        final Stopwatch sw = new Stopwatch (); 
        sw.start ();         
        args.remove (0); 
        getInterpreter ().invoke (args, out);
        sw.stop (); 

        // Report on the performance

        out.println ("Elapsed: " + sw);
        return;
    }
}

// EOF
