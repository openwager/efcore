package com.weaselworks.util;

import java.io.Serializable;

import com.weaselworks.util.TimeUtil.*;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

@SuppressWarnings("serial")
public class Stopwatch
    implements Serializable
{
    public
    Stopwatch ()
    {
        start ();
        return;
    }

//    interface DURATION
//    {
//        public long SECOND = 1000;
//        public long MINUTE = SECOND * 60;
//        public long HOUR = MINUTE * 60;
//        public long DAY = HOUR * 24;
//        public long WEEK = DAY * 7;
//        public long MONTH = DAY * 30;
//        public long YEAR = DAY * 365;
//    }

    protected long start;
    protected Long stop;

    public
    long start ()
    {
        start = System.currentTimeMillis();
        return start;
    }

    public
    long stop ()
    {
        stop = System.currentTimeMillis();
        return stop;
    }

    public
    long getElapsed ()
    {
        if (stop != null) {
            return stop - start;
        } else {
            return System.currentTimeMillis () - start;
        }
        // NOT REACHED
    }

    @Override
    public
    String toString ()
    {
        return elapsedToString (getElapsed ());
    }

    protected static TimeContext tc = new TimeContext (); 
    
    public static
    String elapsedToString (long elapsed)
    {
    	return tc.calculateElapsed (elapsed);     	
    }

    protected static
    void append (final StringBuffer buf, final int val, final String name)
    {
        if (val == 0) {
            return;
        }
        if (buf.length()  != 0) {
            buf.append (", ");
        }
        buf.append (val);
        buf.append (' ');
        buf.append (name);
        if (val != 1) {
            buf.append ('s');
        }
        return;
    }

    /**
     * @see Object#equals
     */

    @Override
    public
    boolean equals (final Object obj)
    {
        if (! (obj instanceof Stopwatch)) {
            return false;
        }
        final Stopwatch s = (Stopwatch) obj;
        return start == s.start 
        	&& stop == s.stop;
    }
}

// EOF