package com.weaselworks.util;

import java.io.File;
import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class DirectoryIterator
    extends IteratorDecorator <File>
        implements Iterator<File>
{
    public
    DirectoryIterator (final String path)
    {
        this (new File (path));
        return;
    }

    public
    DirectoryIterator (final File file)
    {
        if (! file.exists ()) {
            throw new IllegalArgumentException ("File not found: " + file);
        }
        if (! file.isDirectory ()) {
            throw new IllegalArgumentException ("Not a directory: " + file);
        }
        setIterator (IteratorUtil.toIterator (file.listFiles ()));
        return;
    }

    /**
     * @see Iterator#hasNext
     */

    public
    boolean hasNext ()
    {
        return getIterator ().hasNext () ;
    }

    /**
     * @see java.util.Iterator#next()
     */

    public File next ()
    {
        return getIterator ().next ();
    }

    /**
     * @see java.util.Iterator#remove()
     */

    public
    void remove ()
    {
        throw new UnsupportedOperationException ();
    }
}

// EOF