package com.weaselworks.util;

import org.testng.annotations.Test;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class HTMLUtilTest
{

    /**
     *
     */

    @Test
    public
    void testToEntityReference ()
    {
        final char cs [] = { 'a', 'b', 'c', '\n', '\r', '&', '"' };
        for (char c : cs) {
            System.out.println ("[" + c + "] => " + HTMLUtil.toEntityRef (c));        
        }
        return;
    }

    /**
     * 
     */

    @Test
    public
    void testEscape ()
    {
        final char cs [] = { 'a', 'b', 'c', '\n', '\r', '\t', '"' };
        for (char c : cs) {
            System.out.println ("[" + c + "] => " + HTMLUtil.escape (c));
        }
        return;
    }
}

// EOF