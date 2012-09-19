package com.weaselworks.util;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class HashBuilder
{
    public
    HashBuilder ()
    {
        return;
    }

    protected int hashCode = 33;

    public
    void add (final int i)
    {
        hashCode = hashCode * 17 + i;
        return;
    }

    public
    void add (final int [] is)
    {
        for (final int i : is) {
            add (i);
        }
        return;
    }

    public
    void add (final Object obj)
    {
        if (obj != null) {
            hashCode = hashCode * 17 + obj.hashCode ();
        }
        return;
    }

    public
    void add (final Object [] objs)
    {
        for (final Object obj : objs) {
            add (obj);
        }
        return;
    }

    public
    void add (final long l)
    {
        hashCode = hashCode * 17 + (int) (l ^ (l >>> 32));
        return;
    }

    public
    void add (final long [] ls) {
        for (final long l : ls) {
            add (l);
        }
        return;
    }

    public
    void add (final boolean b)
    {
        add (b ? 1 : 0);
        return;
    }

    public
    void add (final boolean [] bs)
    {
        for (final boolean b : bs) {
            add (b);
        }
        return;
    }

    public
    int getHashCode ()
    {
        return hashCode;
    }

    @Override
    public
    int hashCode ()
    {
        return hashCode;
    }
}

// EOF