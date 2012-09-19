package com.weaselworks.util;

import java.util.*;

/**
 * TODO: Needs to be able to properly format times/dates in the future 
 * 
 * @author crawford
 *
 */

public class TimeUtil
{
    private
    TimeUtil ()
    {
            return;
    }

    /**
     * 
     * @author crawford
     *
     */
    
    public interface TimeUnits
    {
        public static final TimeUnit [] DATE  = {
            new TimeUnit (Duration.YEAR, "year"),
            new TimeUnit (Duration.MONTH, "month"),
            new TimeUnit (Duration.WEEK, "week"),
            new TimeUnit (Duration.DAY, "day")
        };
        public static final TimeUnit [] TIME = {
            new TimeUnit (Duration.HOUR, "hour"),
            new TimeUnit (Duration.MINUTE, "minute"),
            new TimeUnit (Duration.SECOND, "second")
        };
        public static final TimeUnit [] DATE_AND_TIME = {
            new TimeUnit (Duration.YEAR, "year"),
            new TimeUnit (Duration.MONTH, "month"),
            new TimeUnit (Duration.WEEK, "week"),
            new TimeUnit (Duration.DAY, "day"),
            new TimeUnit (Duration.HOUR, "hour"),
            new TimeUnit (Duration.MINUTE, "minute"),
            new TimeUnit (Duration.SECOND, "second")
        };
    }

    public static class TimeUnit
    {
        public
        TimeUnit (final long duration, final String name)
        {
            this.duration = duration;
            this.name = name;
            return;
        }

        public
        TimeUnit (final Duration duration, final String name)
        {
        	this (duration.getDuration (), name);
            return;
        }

        protected long duration;
        public long getDuration () { return this.duration; }

        protected String name;
        public String getName () { return this.name; }
    }
    
    public static class TimeContext
    {
        public
        TimeContext ()
        {
                return;
        }

        protected String postpend = "";
        public String getPostpend () { return this.postpend; }
        public void setPostpend (final String postpend) { this.postpend= postpend; return; }

        protected int stopAfter = -1;
        public int getStopAfter () { return this.stopAfter; }
        public void setStopAfter (final int stopAfter) { this.stopAfter= stopAfter; return; }

        protected int maxEmpty = 2;
        public int getMaxEmpty () { return this.maxEmpty; }
        public void setMaxEmpty (final int maxEmpty) { this.maxEmpty = maxEmpty; return; }

        protected String separator = ", ";
        public String getSeparator () { return this.separator; }
        public void setSeparator (final String separator) { this.separator = separator; return; }

        public TimeUnit [] units = TimeUnits.DATE_AND_TIME;
        public void setUnits (final TimeUnit [] units) { this.units = units; return; }
        public TimeUnit [] getUnits () { return this.units; }

        public
        String calculateElapsed (final Date date)
        {
            return calculateElapsed (new Date ().getTime () - date.getTime ());
        }

        public
        String calculateElapsed (final long elapsed)
        {
            final TimeUnit [] units = getUnits ();
            if (elapsed < 0) {
                    return "in the future";
            }
            final long [] ev = elapsedVector (elapsed, units);
            if (ev == null) {
                return "now";
            }

            final StringBuffer buf = new StringBuffer ();
            int emitted = 0;
            int empty = 0;
            for (int i = 0; i < ev.length; i ++) {
                final TimeUnit unit = units [i];
                final long val = ev [i];
                if (val != 0) {
                    if (emitted > 0) {
                        buf.append (getSeparator ());
                    }
                    buf.append (maybePlural (unit.getName (), val));
                    emitted ++;
                    empty = 0;
                    if (getStopAfter () != -1) {
	                    if (emitted == getStopAfter ()) {
	                        break;
	                    }
                    }
                } else {
                    if (emitted > 0) {
                        empty ++;
                        if (empty == getMaxEmpty ()) {
                            break;
                        }
                    }
                }
            }
            buf.append (getPostpend ());
            return buf.toString ();
        }    
            
        public static
        String maybePlural (final String base, final long count)
        {
            if (count == 1) {
                return count + " " + base;
            } else {
                return count + " " + base + "s";
            }
        }

        public
        long [] elapsedVector (final Date date)
        {
            return elapsedVector (new Date ().getTime () - date.getTime ());
        }

        public
        long [] elapsedVector (long elapsed)
        {
            return elapsedVector (elapsed, getUnits ());
        }

        public static
        long [] elapsedVector (long elapsed, final TimeUnit [] units)
        {
            final long [] results = new long [units.length];
            boolean empty = true;
            for (int i = 0; i < units.length; i ++) {
                final TimeUnit unit = units [i];
                final long val = elapsed / unit.getDuration ();
                if (val > 0) {
                    results [i] = val;
                    elapsed -= results [i] * unit.getDuration ();
                    empty = false;
                }
            }
            if (empty) {
                return null;
            }
            return results;
        }
    }

	public static
	long getCurrentTime ()
		throws Exception
	{
		final long ct = System.currentTimeMillis();
		return ct;  
	}
	
	public static
	int toEncodedDate (final int day, final int month, final int year)
	{
		assert day > 0 && day < 32; 
		assert month > 0 && month < 13; 
		assert year > 0 && year < 3000; 
		
		return year + (month * 1000) + (day * (1000 * 100)); 
	}
}

// EOF