package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 *  A built-in {@linkplain Command} that can be used to add new commands to an
 *  {@linkplain Interpreter} during run-time use.
 */

class AddCommand
    extends CommandSupport
        implements HiddenCommand
{
    public static final String COMMAND = "addCommand";
    public static final String DESCRIPTION = "adds a command to the Interpreter";
    public static final String USAGE = "USAGE: " + COMMAND + " <className>";

    public
    AddCommand ()
    {
        super (COMMAND, DESCRIPTION);
        return;
    }

    /**
     *  @see Command#invoke
     */

    @SuppressWarnings ("unchecked")
	public
    void invoke (final List<String> args, final PrintWriter pw)
    {
        if (args.size () != 2) {
            pw.println (USAGE);
            return;
        }
        final String className = args.get (1);

        // Get an instance of the specified command class

        final Class<? extends Command> clazz;

        try {
            clazz = (Class<? extends Command>) Class.forName (className);
        }
        catch (ClassNotFoundException e) {
            pw.println ("ERROR: Class not found (" + className + ").");
            return;
        }
        catch (final NoClassDefFoundError ncdf_e) {
            pw.println ("ERROR: " + ncdf_e.getMessage ());
            return;
        }

        final Object obj;

        try {
            obj = clazz.newInstance ();
        }
        catch (InstantiationException e) {
            pw.println ("ERROR: Cannot instantiate: " + e.getMessage ());
            return;
        }
        catch (IllegalAccessException e) {
            pw.println ("ERROR: Cannot instantiate: " + e.getMessage ());
            return;
        }

        if (! (obj instanceof Command)) {
            pw.println ("ERROR: Not a Command type (" + className + ").");
            return;
        }

        final Command command = (Command) obj;
        final Interpreter interp = getInterpreter ();

        if (interp.lookupCommand (command.getName ()) != null) {
            pw.println ("ERROR: Command '" + command.getName () + "' already present.");
            return;
        }

        // Install the new command

        interp.addCommand (command);
        pw.println ("Command '" + command.getName() + "' added.");
        return;
    }
}

// EOF
