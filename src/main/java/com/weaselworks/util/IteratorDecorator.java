package com.weaselworks.util;

import java.util.Iterator;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

abstract public class IteratorDecorator <Type>
    implements Iterator <Type>
{
    protected
    IteratorDecorator ()
    {
        return;
    }

    protected
    IteratorDecorator (final Iterator <Type> iter)
    {
        setIterator (iter);
        return;
    }

    protected Iterator <Type> iter;

    public
    Iterator <Type> getIterator ()
    {
        return this.iter;
    }

    public
    void setIterator (final Iterator<Type> iter)
    {
        this.iter = iter;
        return;
    }
}

// EOF