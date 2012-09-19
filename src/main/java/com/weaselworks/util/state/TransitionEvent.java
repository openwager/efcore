package com.weaselworks.util.state;

import java.util.*;

/**
 *  An event object representing the occurrence of a {@link weaselworks.state.Transition} within a {@link weaselworks.state.StateMachine}. </p>
 *
 *  Each {@link weaselworks.state.TransitionEvent} contains a reference to the originating or 'start' {@link weaselworks.state.State} and a reference
 *  to the target or 'end' {@link weaselworks.state.State}. Transition events are sent to all {@link weaselworks.state.StateEntryListener
 *  StateEntryListeners}, {@link weaselworks.state.TransitionListener TransitionListeners}, and {@link weaselworks.state.StateExitListener
 *  StateExitListeners} during state machine operation. </p>
 *
 *   Note that When a {@link weaselworks.state.StateMachine} is first started a transition from no
 *  {@link weaselworks.state.State} to the initial state occurs. In this case any {@link weaselworks.state.StateEntryListener StateEntryListeners}
 *  attached to the initial state will receive a {@link weaselworks.state.TransitionEvent} in which the {@link #getStartState start
 *  state} in null. </p>
 *
 *  @see weaselworks.state.StateEntryListener
 *  @see weaselworks.state.StateExitListener
 *  @see weaselworks.state.TransitionListener
 */

@SuppressWarnings ("serial")
public class TransitionEvent
    extends EventObject
{
    public
    TransitionEvent (final State startState, final State endState)
    {
        super (endState.getStateMachine ());
        setStartState (startState);
        setEndState (endState);
        return;
    }

    protected State startState;

    public
    State getStartState ()
    {
        return this.startState;
    }

    protected
    void setStartState (final State startState)
    {
        this.startState = startState;
        return;
    }

    protected State endState;

    public
    State getEndState ()
    {
        return this.endState;
    }

    protected
    void setEndState (final State endState)
    {
        this.endState = endState;
        return;
    }
}

// EOF