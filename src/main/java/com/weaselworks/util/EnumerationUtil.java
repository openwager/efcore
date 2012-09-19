package com.weaselworks.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class EnumerationUtil
{
    private
    EnumerationUtil ()
    {
        return;
    }

    /**
     *
     * @param e
     * @return
     */

    public static <Type> 
    Iterator<Type> toIterator (final Enumeration<Type> e)
    {
        return new EnumerationIterator<Type> (e);
    }

    /**
     *
     * @param e
     */

    public static
    void dump (final Enumeration<?> e)
    {
        dump (e, System.out); 
        return;
    }

    /**
     * 
     * @param e
     * @param os
     */

    public static
    void dump (final Enumeration<?> e, final OutputStream os)
    {
        final PrintStream ps = new PrintStream (os);
        int cnt = 0;

        while (e.hasMoreElements()) {
            final Object elem = e.nextElement();
            ps.println ("" + (++ cnt) + ": " + elem);
        }

        return; 
    }
}

// EOF