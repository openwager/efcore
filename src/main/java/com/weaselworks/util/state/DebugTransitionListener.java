package com.weaselworks.util.state;

import java.util.logging.*;

/**
 *  An example {@link TransitionListener} that reports state transition events to the log.
 */

public class DebugTransitionListener
    implements TransitionListener
{
    protected static final Logger logger = Logger.getLogger ("tictactoe.state");

    public
    DebugTransitionListener ()
    {
        return;
    }

    /**
     *  @see weaselworks.state.TransitionListener#transitionOccurred
     */

    public
    void transitionOccurred (final TransitionEvent evt)
    {
        logger.log (Level.INFO, "TRANSITION [" + evt.getStartState ().getName () + " -> " + evt.getEndState ().getName () + "]");
        return;
    }
}

// EOF