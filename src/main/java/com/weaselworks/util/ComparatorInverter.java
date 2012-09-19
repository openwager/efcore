package com.weaselworks.util;

import java.util.Comparator;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class ComparatorInverter<Type>
    implements Comparator<Type>
{
    public
    ComparatorInverter (final Comparator<Type> comp)
    {
        setComparator (comp);
        return;
    }

    protected Comparator<Type> comp;

    public
    Comparator<Type> getComparator ()
    {
        return this.comp;
    }

    protected
    void setComparator (final Comparator<Type> comp)
    {
        this.comp = comp;
        return;
    }

    /**
     *  @see Comparator#compare(Object, Object)
     */

    public
    int compare (final Type o1, final Type o2)
    {
        return - getComparator ().compare (o1, o2);
    }
}

// EOF