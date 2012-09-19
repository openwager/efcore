package com.weaselworks.util;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Enumeration;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class EnumerationIteratorTest
{
	/**
	 * 
	 */
	
    @Test
    public
    void test1 ()
    {
        final Enumeration<String> e = EnumerationTest.getEnumeration ();
        final Iterator<String> iter = EnumerationUtil.toIterator (e);
        IteratorUtil.dump (iter);
        return;
    }
}

// EOF