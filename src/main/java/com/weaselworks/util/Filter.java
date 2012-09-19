package com.weaselworks.util;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public interface Filter<T>
{
    /**
     *
     * @param value
     * @return
     */

    public
    boolean filter (T value);
}

// EOF