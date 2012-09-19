package com.weaselworks.util;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class EnumerationTest
{
	/**
	 * 
	 * @return
	 */
	
    public static
    Enumeration<String> getEnumeration ()
    {
        final String [] strs = { "one", "two", "three", "four", "five" };
        final Vector<String> vector = new Vector <String>();
        for (final String str : strs) {
            vector.add (str);
        }
        return vector.elements();
    }

    /**
     * 
     */
    
    @Test
    public
    void test1 ()
    {
        final Enumeration<String> e = getEnumeration();
        EnumerationUtil.dump (e);
        return;
    }

    

    
}

// EOF