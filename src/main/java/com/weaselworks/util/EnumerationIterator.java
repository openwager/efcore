package com.weaselworks.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class EnumerationIterator<Type>
    implements Iterator<Type>
{
    public
    EnumerationIterator (final Enumeration<Type> en)
    {
        setEnumeration (en);
        return;
    }

    protected Enumeration<Type> en;

    public
    Enumeration <Type> getEnumeration ()
    {
        return this.en;
    }

    public
    void setEnumeration (final Enumeration<Type> en)
    {
        this.en = en;
        return;
    }

    /**
     *  @see Iterator#hasNext
     */

    public
    boolean hasNext ()
    {
        return getEnumeration().hasMoreElements ();
    }

    /**
     *  @see Iterator#next
     */

    public
    Type next ()
    {
        return getEnumeration ().nextElement ();
    }

    /**
     *  @see Iterator#remove
     */

    public
    void remove ()
    {
        throw new UnsupportedOperationException ();
    }
}

// EOF