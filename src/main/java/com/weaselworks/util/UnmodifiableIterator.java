package com.weaselworks.util;

import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class UnmodifiableIterator <Type>
    extends IteratorDecorator <Type>
{
    public
    UnmodifiableIterator (final Iterator<Type> iter)
    {
        super (iter);
        return;
    }

    /**
     *  @see Iterator#hasNext
     */

    public
    boolean hasNext ()
    {
        return getIterator ().hasNext ();
    }

    /**
     *  @see Iterator#next
     */

    public
    Type next ()
    {
        return getIterator ().next ();
    }

    /**
     *  Throws an UnsupportedOperationException instead of forwarding the method
     *  to the 'wrapped' Iterator.
     *
     *  @see Iterator#remove
     */

    public
    void remove ()
    {
        throw new UnsupportedOperationException ();
    }
}

// EOF