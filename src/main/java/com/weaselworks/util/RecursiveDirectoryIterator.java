package com.weaselworks.util;

import java.io.File;
import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class RecursiveDirectoryIterator
    extends IteratorListIterator <File>
        implements Iterator<File>
{
    public
    RecursiveDirectoryIterator (final String path)
    {
        super (new DirectoryIterator (path));
        return;
    }

    public
    RecursiveDirectoryIterator (final File file)
    {
        super (new DirectoryIterator (file));
        return;
    }

    /**
     * Caches the next file that's not a directory
     */

    protected File next;

    /**
     * @see java.util.Iterator#hasNext()
     */

    @Override
        public
    boolean hasNext ()
    {
        if (next != null) {
            return true;
        }

        while (true) {
            if (queue.size () == 0) {
                return false;
            }
            if (! queue.get (0).hasNext ()) {
                queue.remove (0);
                continue;
            }
            final File file = queue.get (0).next ();
            if (file.isDirectory () && (null!=file.listFiles ())) {
                final DirectoryIterator iter = new DirectoryIterator (file);
                queue.add (0, iter);
            } else {
                next = file;
                return true;
            }
        }

        // NOT REACHED
    }

    /**
     * @see java.util.Iterator#next()
     */

    @Override
    public
    File next ()
    {
        if (hasNext ()) {
            final File f = next;
            next = null;
            return f;
        } else {
            return null;
        }
    }
}

// EOF