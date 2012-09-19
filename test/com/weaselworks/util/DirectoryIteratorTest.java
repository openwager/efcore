package com.weaselworks.util;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.io.File;

/**
 *TODO: dfg
 * 
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */
public class DirectoryIteratorTest
{
    @Test
    public
    void test1 ()
    {
        final Iterator<File> iter = new DirectoryIterator ("c:/");
        IteratorUtil.dump (iter);
        return;         
    }
}

// EOF