package com.weaselworks.util;

import java.util.concurrent.Callable;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class Singleton<T>
{
        public
        Singleton (final Callable<T> factory)
        {
                this.factory = factory;
                return;
        }

        protected Callable<T> factory;
        protected volatile T singleton;

        /**
         * Returns the enclosed singleton instance. If this is the first time that this method
         * has been called, the singleton instance is lazily instantiated using the factory
         * that was provided when the singleton was constructed.
         *
         * @return
         * @throws Exception
         */

        public
        T get ()
                throws Exception
        {
                if (singleton == null) {
                        synchronized (factory) {
                                if (singleton == null) {
                                        singleton = factory.call ();
                                }
                        }
                }
                return singleton;
        }
}

// EOF