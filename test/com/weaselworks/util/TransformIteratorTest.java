package com.weaselworks.util;

import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class TransformIteratorTest
{
    @Test
    public
    void test1 ()
    {
        final Iterator<String> iter = IteratorTest.getIterator ();
        final Transform<String, Integer> xform = new Transform<String, Integer> () {
            public Integer transform (final String arg) {
                return arg.length ();
            }
        };
        final Iterator<Integer> iter2 = new TransformIterator<String, Integer> (iter, xform); 
        IteratorUtil.dump (iter2);
        return;
    }
}

// EOF