package com.weaselworks.util;

import org.testng.annotations.Test;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class IteratorListIteratorTest
{
    @Test
    public
    void test1 ()
    {
        final IteratorListIterator <String> iter = new IteratorListIterator<String> ();
        iter.addIterator (IteratorTest.getIterator());
        iter.addIterator (IteratorTest.getIterator());
        IteratorUtil.dump (iter);
        return; 
    }
}

// EOF