package com.weaselworks.util;

import java.util.*;

import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.*;

/**
 * 
 * @author crawford
 *
 */

public class JNDIUtil
{
	protected static final Logger logger = Logger.getLogger (JNDIUtil.class); 
	
	private
	JNDIUtil ()
	{
		return; 
	}

	/**
	 * 
	 * @param <T>
	 * @param type
	 * @param name
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
    public static <T>
	T lookupService (final Class<? extends T> type, final String name)
		throws NamingException
	{
		final Context ctx = new InitialContext (); 
		final Object ref = ctx.lookup (name); 
		try { 
			return (T) PortableRemoteObject.narrow (ref, type); 
		}
		catch (final ClassCastException cc_e) { 
			logger.error ("Failed cast of " + ref.getClass ().getName () + " to " + type.getName ()); 
			throw cc_e; 
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param ctx
	 * @param c
	 * @param name
	 * @return
	 * @throws NamingException
	 */
	
    @SuppressWarnings("unchecked")
    public static <T>
    T lookupService (final Context ctx, final Class <? extends T> c, final String name)
    	throws NamingException
	{
	    return (T) ctx.lookup (name);
	}

    protected static Map<String, InitialContext> cache = new HashMap<String, InitialContext> ();

    public static final String UNSPECIFIED = "*";

    /**
     *
     * @return
     * @throws NamingException
     */

    public static
    InitialContext getInitialContext ()
        throws NamingException
    {
        return getInitialContext (UNSPECIFIED);
    }

    /**
     *
     * @param comp
     * @return
     * @throws NamingException
     */

    public static
    InitialContext getInitialContext (final String comp)
        throws NamingException
    {
        if (comp == null) {
            throw new NullPointerException ("Null comp specified.");
        }

        InitialContext ctx = cache.get (comp);

        if (ctx == null) {
            String prop = null;
            if (! UNSPECIFIED.equals (comp)) {
                prop = System.getProperty ("mfp." + comp + ".host");
            }
            if (prop == null) {
                prop = System.getProperty ("mfp.host");
            }
            if (prop == null) {
                ctx = new InitialContext ();
            } else {
                final Properties props = new Properties ();
                // JBoss configuration
                props.setProperty (Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
                props.setProperty (Context.PROVIDER_URL, prop);
                props.setProperty (Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jboss.interfaces");
                ctx = new InitialContext (props);
            }
        }

        return ctx;
    }
}

// EOF
