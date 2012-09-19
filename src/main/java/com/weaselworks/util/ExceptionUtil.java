package com.weaselworks.util;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class ExceptionUtil
{
    /**
     * Private constructor to defeat instantiation.
     */

    private
    ExceptionUtil()
    {
        // EMPTY
    }

    /**
     *
     * @param t
     * @return
     */

    public static
    StringBuilder getStackTrace (final Throwable t)
    {
        Throwable cause = t;
        final StringBuilder sb = new StringBuilder();
        do {
            if (cause != t) {
                sb.append("Caused by: ");
            }
            sb.append (cause.toString ()).append("\n");
            StackTraceElement [] stackTrace = cause.getStackTrace();
            for (final StackTraceElement st : stackTrace) {
                sb.append("\t").append("at ").append(st.toString()).append("\n");
            }
            cause = cause.getCause ();
        } while (cause != null);
        sb.append("\n");
        return sb;
    }
}

// EOF