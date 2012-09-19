package com.weaselworks.util;

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * 
 * @author crawford
 *
 */

public class IntervalTest
{
	@Test
	public
	void test1 ()
	{
		final Interval i1 = new Interval (0, 2); 
		final Interval i2 = new Interval (1, 3); 
		final Interval i3 = new Interval (2, 4); 
		final Interval i4 = new Interval (3, 5);
		final Interval i5 = new Interval (-1, 6); 
		
		testOverlap (i1, i1, true); 
		testOverlap (i1, i2, true); 
		testOverlap (i1, i3, true); 
		testOverlap (i1, i4, false);
		testOverlap (i1, i5, true); 
		testOverlap (i2, i3, true); 
		testOverlap (i2, i4, true); 
		testOverlap (i2, i5, true); 
		testOverlap (i3, i4, true); 
		testOverlap (i3, i5, true); 
		testOverlap (i4, i5, true); 
	
		return; 
	}
	
	protected static
	void testOverlap (final Interval i1, final Interval i2, final boolean expected)
	{
		System.err.println ("testing overlap of " + i1 + " and " + i2 + " [expect " + expected + "]"); 
		final boolean overlap = i1.overlaps (i2); 
		assertTrue (overlap == expected); 
		return; 
	}

	@Test
	public
	void testAdjacency ()
	{
		final Interval i1 = new Interval (1, 2); 
		final Interval i2 = new Interval (-1, 0); 
		final Interval i3 = new Interval (0, 1); 
		final Interval i4 = new Interval (2, 5); 
		final Interval i5 = new Interval (3, 5); 
	
		testAdjacency (i1, i2, false); 
		testAdjacency (i1, i3, true); 
		testAdjacency (i1, i4, true); 
		testAdjacency (i1, i5, false); 
		
		return; 
	}

	protected static
	void testAdjacency (final Interval i1, final Interval i2, final boolean expected)
	{
		System.err.println ("testing adjacency of " + i1 + " and " + i2 + " [expect " + expected + "]"); 
		final boolean adjacent = i1.adjacent (i2); 
		assertTrue (adjacent == expected); 
		if (adjacent) { 
			System.err.println ("   " + i1); 
			System.err.println (" + " + i2); 
			System.err.println (" = " + i1.merge (i2)); 
		}
		return; 
	}
}

// EOF