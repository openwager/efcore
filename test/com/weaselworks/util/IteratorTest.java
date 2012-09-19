package com.weaselworks.util;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class IteratorTest
{
	/**
	 * 
	 * @return
	 */
	
    public static 
    Iterator<String> getIterator ()
    {
        final String [] strs = { "one", "two", "three", "four", "five" };
        final List<String> list = Arrays.asList (strs);
        return list.iterator ();
    }

    /**
     * 
     */
    
    @Test
    public
    void test1 ()
    {
        final Iterator<String> iter = getIterator ();
        IteratorUtil.dump (iter);
        return; 
    }
}

// EOF