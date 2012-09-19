package com.weaselworks.util;

import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class RecursiveDirectoryIteratorTest
{
    @Test
    public
    void test1 ()
    {
        final Iterator<File> iter = new RecursiveDirectoryIterator ("c:/work");
        IteratorUtil.dump (iter);
        return;        
    }
}

// EOF