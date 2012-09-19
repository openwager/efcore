package com.weaselworks.util;

import java.util.regex.Pattern;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class RegexFilter
     implements Filter<String>
{
    public
    RegexFilter (final String pattern)
    {
        this.pattern = Pattern.compile (pattern);
        return;
    }

    final Pattern pattern;

    /**
     * @see Filter#filter(Object)
     */

    public
    boolean filter (final String value)
    {
        return pattern.matcher (value).matches ();
    }
}

// EOF