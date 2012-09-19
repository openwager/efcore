package com.weaselworks.util;

import java.util.Iterator;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class FilterIterator <Type>
    extends IteratorDecorator <Type>
{
    public
    FilterIterator (final Iterator<Type> iter, final Filter <Type> filter)
    {
        super (iter);
        setFilter (filter);
        return;
    }

    public
    FilterIterator (final Iterator <Type> iter, final Filter <Type> filter, final boolean remove)
    {
        super (iter);
        setFilter (filter);
        setDoRemoval (remove);
        return;
    }

    private Filter <Type> filter;

    public
    Filter <Type> getFilter ()
    {
        return this.filter;
    }

    protected
    void setFilter (final Filter <Type> filter)
    {
        this.filter = filter;
        return;
    }

    private boolean doRemoval;

    public
    boolean getDoRemoval()
    {
        return this.doRemoval;
    }

    protected
    void setDoRemoval(final boolean doRemoval)
    {
        this.doRemoval = doRemoval;
        return;
    }

    protected Type next;

    /**
     *  @see java.util.Iterator#hasNext
     */

    public
    boolean hasNext ()
    {
        if (next != null) {
            return true;
        }

        while (true) {
            if (! iter.hasNext ()) {
                return false;
            }
            next = iter.next ();
            if (filter == null) {
                return true;
            }
            if (! getFilter ().filter (next)) {
                return true;
            }
            if (getDoRemoval ()) {
                iter.remove ();
            }
        }

        // NOT REACHED
    }

    /**
     *  @see java.util.Iterator#next
     */

    public
    Type next ()
    {
        if (! hasNext ()) {
            return null;
        } else {
            final Type obj = next;
            next = null;
            return obj;
        }

        // NOT REACHED
    }

    /**
     *  @see Iterator#remove()
     */

    public
    void remove ()
    {
        throw new IllegalStateException ("Operation not supported.");
    }
}

// EOF