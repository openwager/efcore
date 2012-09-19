package com.weaselworks.util;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public enum Duration
{
    SECOND (1000),
    MINUTE (SECOND.getDuration () * 60),
    HOUR (MINUTE.getDuration () * 60),
    DAY (HOUR.getDuration () * 24),
    WEEK (DAY.getDuration () * 7),
    MONTH (DAY.getDuration () * 30), 
    YEAR (DAY.getDuration () * 365);

    Duration (final long duration)
    {
        this.duration = duration;
        return;
    }

    protected long duration;
    public long getDuration () { return this.duration; }
    protected void setDuration (final long duration) { this.duration = duration; return; }

    public
    String toString ()
    {
        return name () + "(duration=" + duration + ")";
    }

    public String getName () { return this.name (); }
    public int getOrdinal () { return this.ordinal (); } 

}

// EOF
