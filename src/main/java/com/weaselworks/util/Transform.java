package com.weaselworks.util;

/**
 *
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public interface Transform <In, Out> 
{
    /**
     *
     * @param arg
     * @return
     */

    public
    Out transform (In arg);
}

// EOF