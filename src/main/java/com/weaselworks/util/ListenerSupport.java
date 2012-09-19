package com.weaselworks.util;

import java.util.EventListener;
import java.util.List;
import java.util.ArrayList;
import java.util.EventObject;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public abstract class ListenerSupport
{
    /**
     *  Default constructor.
     */

    public
    ListenerSupport ()
    {
        return;
    }

    protected List<EventListener> listeners = null;

    /**
     *
     * @param ler
     */

    public
    void addListener (final EventListener ler)
    {
        if (listeners == null) {
            listeners = new ArrayList<EventListener>();
        }
        if (! listeners.contains (ler)) {
            listeners.add (ler);
        }
        return;
    }

    /**
     *
     * @param ler
     */

    public
    void removeListener (final EventListener ler)
    {
        if (listeners == null || ! listeners.contains (ler)) {
            return;
        }
        listeners.remove (ler);
        if (listeners.size () == 0) {
            listeners = null;
        }
        return;
    }

    /**
     *
     * @@param evt
     */

    @SuppressWarnings("unchecked")
    public
    void perform (final EventObject evt)
    {
        if (listeners == null) {
            return;
        }

        // We need to make a copy of the listeners list before we can begin iterating
        // through it and sending out the event objects. If we didn't do this and the
        // listeners modified the listener list (e.g., removed themselves) during
        // the callback event then our iterator would throw a ConcurrentModificationException
        // upon return. [crawford]
        //
        // Note that if the list if large this can get to be a fairly expensive operation
        // and it might be worth considering a 'copy-on-write' facade to minimize
        // unecessary list copies. But, for small listener lists the performance of this
        // approach is quite acceptable. [crawford]

        synchronized (this) {
            final List<EventListener> copy = (List<EventListener>) ((ArrayList<EventListener>) listeners).clone ();
            for (final EventListener ler : copy) {
                try {
                    perform (ler, evt);
                }
                catch (final Exception e) {
                    e.printStackTrace ();
                }
            }
        }

        return;
    }

    /**
     *
     * @param ler
     * @param evt
     */

    public abstract
    void perform (final EventListener ler, final EventObject evt);
}
// EOF