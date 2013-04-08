package com.weaselworks.util;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.persistence.*;
import javax.transaction.*;

import org.hibernate.ejb.*;

/**
 * 
 * @author crawford
 *
 */

public class JBossUtil
{
	private
	JBossUtil ()
	{
		return; 
	}
	
	public static 
	Context getInitialContext ()
		throws NamingException
	{
		return getInitialContext ("localhost"); 
	}
	
	public static
	Context getInitialContext (final String host)
		throws NamingException
	{
		return getInitialContext (host, 1099); 
	}
	
	public static
	Context getInitialContext (final String host, final int port)
	throws NamingException
	{
        final String url = "jnp://" + host + ":" + port;
        final Properties props = new Properties ();
        props.put (Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put (Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        props.put (Context.PROVIDER_URL, url);
        return new InitialContext (props);
	}

	public static
	Context getClientInitialContext (final String host, final int port)
		throws NamingException
	{
        final String url = "jnp://" + host + ":" + port;
        final Properties props = new Properties ();
        props.put (Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.NamingContextFactory");
        props.put (Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        props.put (Context.PROVIDER_URL, url);
        return new InitialContext (props);		
	}
	
    public static
    String getServerInstanceName()
    {
        return System.getProperty ("jboss.server.name");
    }
	
    public static String getDBURL (final EntityManager entityManager)
        throws SQLException
    {
        final HibernateEntityManager hem = (HibernateEntityManager) entityManager;
        final org.hibernate.Session session = hem.getSession();
        final Connection connection = session.connection();
        return connection.getMetaData ().getURL ();
    }    
    
    public static
    UserTransaction getUserTransaction()
            throws NamingException
	{
	    final Context ctx = getClientInitialContext ("localhost", 1099);
	    final UserTransaction ut = JNDIUtil.lookupService (ctx, UserTransaction.class, "UserTransaction");
	    return ut;
	}

}
