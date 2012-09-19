package com.weaselworks.util.interp;

import java.io.*;
import java.util.*;

/**
 *  An interface for interpreter commands. An example command:
 *
 *  <pre>
 *  public class EchoCommand
 *  {
 *     public
 *     String getUsername ()
 *     {
 *       return "echo";
 *     }
 *
 *     public
 *     void invoke (final String [] args, final PrintWriter out)
 *     {
 *       for (int i = 0; i < args.length; i ++) {
 *          out.print (args [i]);
 *          if (i != (args.length - 1)) {
 *            out.print (' ');
 *          }
 *       }
 *       out.println ();
 *       return;
 *     }
 *  </pre>
 *
 *  @see CommandSupport
 *  @see Interpreter
 */

public interface Command
{
    /**
     *  Used to retrieve the command-name. 
     *
     *  @return the command-name
     */
     
    public String getName (); 
    
    /**
     *  Used to retrieve a usage message for the command. This is used by
     *  the 'help' commend to display assistance during an interactive session. 
     *
     *  @return the command usage text
     */
     
    public String getUsage (); 
    
    /**
     *  Called interactively when a command-line starting with the 
     *  command is entered. 
     *
     *  @param args the command-line arguments passed with the command
     *  @param pw the output stream for interactive text display
     */
    
    public void invoke (List<String> args, PrintWriter pw)
        throws Exception;
}

// EOF