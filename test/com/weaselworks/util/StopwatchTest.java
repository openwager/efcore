package com.weaselworks.util;

import org.testng.annotations.Test;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class StopwatchTest
{
    @Test
    public
    void test1 ()
    {
        final Stopwatch s = new Stopwatch ();
        s.start ();
        Util.delay (1234); 
        s.stop ();
        System.out.println (s);
        System.out.println (Stopwatch.elapsedToString(123456));
        return;
    }
}

// EOF