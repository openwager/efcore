package com.weaselworks.util.state;

import java.util.logging.*;

/**
 *  An example {@link StateExitListener} that reports state exit events to the log.
 */

public class DebugStateExitListener
    implements StateExitListener
{
    protected static final Logger logger = Logger.getLogger ("tictactoe.state");

    public
    DebugStateExitListener ()
    {
        return;
    }

    /**
     *  @see weaselworks.state.StateExitListener#stateExited(weaselworks.state.TransitionEvent)
     */

    public
    void stateExited (final TransitionEvent tevt)
    {
        logger.log (Level.INFO, "STATE [" + tevt.getStartState ().getName () + "] EXITED");
        return;
    }
}

// EOF