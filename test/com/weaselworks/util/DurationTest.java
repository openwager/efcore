package com.weaselworks.util;

import org.testng.annotations.Test;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class DurationTest
{
    @Test
    public
    void dump ()
    {
        for (final Duration dur : Duration.values()) {
            System.err.println (dur); 
        }
        return;
    }

}

// EOF