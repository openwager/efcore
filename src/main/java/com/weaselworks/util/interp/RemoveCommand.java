package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 *   A built-in {@linkplain Command} that can be used to remove commands dynamically
 *  from an {@linkplain Interpreter} dynamically at run-time.
 */

class RemoveCommand
    extends CommandSupport
        implements HiddenCommand
{
    public static final String COMMAND = "removeCommand";
    public static final String DESCRIPTION = "removes a command from the Interpreter";
    public static final String USAGE = "USAGE: " + COMMAND + " <command>";

    public
    RemoveCommand ()
    {
        super (COMMAND, DESCRIPTION);
        return;
    }

    /**
     *  @see Command#invoke
     */

    public
    void invoke (final List<String> args, final PrintWriter pw)
        throws Exception
    {
        if (args.size () != 2) {
            pw.println (USAGE);
            return;
        }

        final String name = args.get (1); 
        final Interpreter interp = getInterpreter ();

        if (interp.lookupCommand (name) == null) {
            pw.println ("ERROR: Command not found (" + name + ").");
            return;
        }

        interp.removeCommand (name);
        pw.println ("Command '" + name + "' removed.");
        return;
    }
}

// EOF
