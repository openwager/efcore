package com.weaselworks.util;

import java.util.Comparator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class StringComparator
    implements Comparator<String>
{
    /**
     * @see Comparator#compare(Object, Object)
     */

    public
    int compare (final String s1, final String s2)
    {
        return s1.compareTo (s2);
    }
}

// EOF