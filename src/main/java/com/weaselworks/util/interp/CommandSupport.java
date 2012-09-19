package com.weaselworks.util.interp;

/**
 *  A Utility class for implementing Command objects. Most command objects 
 *  may be easily built by extending this support object. 
 *
 *  The following is an example commend object that prints 'Hello, World!' 
 *  when invoked: 
 *
 *  Example: 
 *
 *  <pre>
 *    public class HelloCommand
 *      extends CommandSupport
 *    { 
 *      public
 *      HelloCommand ()
 *      {
 *        super ("hello", "Outputs a friendly 'hello' message."); 
 *        return; 
 *      }
 *
 *      public
 *      void invoke (final String [] args, final PrintWriter out)
 *      {
 *        out.println ("Hello, World!"); 
 *        return;
 *      }
 *    }
 *  </pre>
 *
 *  @see Interpreter
 *  @see Command
 */

abstract public class CommandSupport
    implements Command
{
    public
    CommandSupport (final String name, final String usage)
    {
        setName (name);
        setUsage (usage);
        return;
    }

    /**
     *  @see Command#getName
     */

    public
    String getName ()
    {
        return this.name;
    }

    /**
     *  @see Command#getUsage
     */

    public
    String getUsage ()
    {
        return usage; 
    }
    
    protected String name, usage;
    
    protected
    void setName (final String name)
    {
        this.name = name;
        return;
    }
    
    protected
    void setUsage (final String usage)
    {
        this.usage = usage; 
        return;
    }

    protected Interpreter interp;

    public
    Interpreter getInterpreter ()
    {
        return this.interp;
    }

    public
    void setInterpreter (final Interpreter interp)
    {
        this.interp = interp;
        return;
    }
}

// EOF