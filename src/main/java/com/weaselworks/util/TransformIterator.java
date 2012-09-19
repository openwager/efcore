package com.weaselworks.util;

import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class TransformIterator <In, Out>
    implements Iterator<Out>
{
    public
    TransformIterator (final Iterator <In> iter, final Transform <In, Out> xform
)
    {
        this.iter = iter;
        setTransform (xform);
        return;
    }

    private Iterator <In> iter;
    private Transform <In, Out> xform;

    public
    Transform <In, Out> getTransform ()
    {
        return this.xform;
    }

    protected
    void setTransform (final Transform <In, Out> xform)
    {
        this.xform = xform;
        return;
    }

    /**
     *  @see Iterator#next()
     */

    public
    Out next ()
    {
        return getTransform ().transform (iter.next ());
    }

    /**
     *  @see Iterator#hasNext()
     */

    public
    boolean hasNext ()
    {
        return iter.hasNext ();
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
