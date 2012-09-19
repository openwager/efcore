package com.weaselworks.util;

import org.testng.annotations.Test;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */
public class ExceptionUtilTest
{
    @Test
    public
    void test1 ()
    {
        try {
            a ();
        }
        catch (final Throwable t) {
            System.out.println (ExceptionUtil.getStackTrace(t));
        }

        return; 
    }

    public static
    void a ()
    {
        try {
            b ();
        }
        catch (final Throwable t) {
            throw new Error ("Doh!", t);
        }
        return;
    }

    public static
    void b ()
    {
        c ();
        return;
    }

    public static
    void c ()
    {
        throw new Error ("Hello, World!");
        // NOT REACHED
    }
}

// EOF