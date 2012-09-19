package com.weaselworks.util;

import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class FilterTest
{
    @Test
    public
    void test1 ()
    {
        final Iterator<String> iter = IteratorTest.getIterator ();
        final Filter<String> filter = new RegexFilter (".*o.*");
        final FilterIterator<String> fiter = new FilterIterator<String> (iter, filter);
        IteratorUtil.dump (fiter);
        return;
    }

    @Test
    public
    void test2 ()
    {
        final Iterator<String> iter = IteratorTest.getIterator ();
        final Filter<String> filter = new RegexFilter (".*o.*");
        final Filter<String> filter2 = new FilterInverter<String> (filter); 
        final FilterIterator<String> fiter = new FilterIterator<String> (iter, filter2);
        IteratorUtil.dump (fiter);
        return;
    }
}

// EOF