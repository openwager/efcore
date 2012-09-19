package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 *  Implements an interpreter command for terminating the JVM. 
 *
 *  @see Interpreter
 *  @see Command
 *  @see CommandSupport
 */
 
public class QuitCommand
    extends CommandSupport
{
    public
    QuitCommand ()
    {
        super ("quit", "ends the interpreter session");
        return;
    }

    /**
     *  @see Command#invoke
     */

    public
    void invoke (final List<String> args, final PrintWriter out)
    {
    	final Interpreter interp = getInterpreter (); 
    	interp.setQuit (true); 
    	return; 
    }
}

// EOF