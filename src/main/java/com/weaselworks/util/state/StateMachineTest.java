package com.weaselworks.util.state;


/**
 * 
 * @author crawford
 *
 */

public class StateMachineTest
{
	public static
	void main (final String [] args)	
		throws Exception
	{
		// Create a new 'lifecycle' state machine

		final StateMachine fsm = new StateMachine ();
		final State birth = new State ("birth", fsm);
		final State school = new State ("school", fsm);
		final State work = new State ("work", fsm);
		final State death = new State ("death", fsm);
		fsm.setInitialState (birth);

		// Add the permissible transitions

		birth.addTransition (school);
		birth.addTransition (death);
		// birth.addTransition (work); // third-world mode
		school.addTransition (work);
		school.addTransition (death);
		work.addTransition (death);

		// Add some listeners
		
		final State [] states = new State [] { birth, school, work, death };
		for (final State state : states) {
			state.addEntryListener (new DebugStateEntryListener ()); 
			state.addExitListener (new DebugStateExitListener ());
			for (final Transition trans : state.getTransitions ().values ()) { 
				trans.addTransitionListener (new DebugTransitionListener ()); 
			}
		}
		
		// Start the state machine

		fsm.start ();

		// Execute some transitions (starts in birth)

		fsm.transition (school);
		fsm.transition (work);
		fsm.transition (death);

		return;
	}
}

// EOF