package com.weaselworks.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class FilterUtil
{
    private
    FilterUtil ()
    {
        return;
    }

    /**
     *
     * @param filter
     * @param coll
     * @param <Type>
     */

    public <Type>
    void filter (final Filter<Type> filter, final Collection<Type> coll)
    {
        final Iterator<Type> iter = coll.iterator();
        while (iter.hasNext ()) {
            final Type val = iter.next ();
            if (filter.filter(val)) {
                iter.remove();
            }
        }
        return;
    }

    /**
     *
     * @param filter
     * @param <Type>
     * @return
     */

    public <Type>
    Filter<Type> invertFilter (final Filter<Type> filter)
    {
        return new FilterInverter<Type> (filter);
    }
}

// EOF