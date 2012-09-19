package com.weaselworks.util;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class IteratorListIterator <Type>
    implements Iterator <Type>
{
    public
    IteratorListIterator ()
    {
        return;
    }

    public
    IteratorListIterator (final Iterator <Type>  iter)
    {
        queue.add (iter);
        return;
    }

    public
    IteratorListIterator (final Iterator <Type> [] iters)
    {
        for (final Iterator <Type> iter : iters) {
            queue.add (iter);
        }
        return;
    }

    public
    IteratorListIterator (final Set<Iterator <Type>> iters)
    {
        queue.addAll (iters);
        return;
    }

    public
    void addIterator (final Iterator <Type> iter)
    {
        queue.add (iter);
        return;
    }

    protected List<Iterator <Type>> queue = new ArrayList<Iterator<Type>>();

    /**
     * @see java.util.Iterator#next()
     */

    public
    boolean hasNext ()
    {
        while (queue.size () > 0) {
            if (queue.get (0).hasNext ()) {
                return true;
            } else {
                queue.remove (0);
            }
        }
        return false;
    }

    /**
     * @see java.util.Iterator#next()
     */

    public
    Type next ()
    {
        return queue.get (0).next ();
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