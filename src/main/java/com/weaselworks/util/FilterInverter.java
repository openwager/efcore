package com.weaselworks.util;

/**
 * 
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class FilterInverter<Type>
    implements Filter<Type>
{
    public
    FilterInverter (final Filter<Type> filter)
    {
        setFilter (filter);
        return;
    }

    private Filter<Type> filter;

    public
    Filter<Type> getFilter ()
    {
        return this.filter;
    }

    protected
    void setFilter (final Filter<Type> filter)
    {
        this.filter = filter;
        return;
    }

    /**
     *  @see Filter#filter
     */

    public
    boolean filter (final Type obj)
    {
        return ! getFilter ().filter (obj);
    }
}

// EOF