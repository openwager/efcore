package com.weaselworks.util;

import java.io.*;
import java.util.*;

import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class ServerIdentityUtil
{
	private
	ServerIdentityUtil ()
	{
		return; 
	}
	
	protected static String name; 
	
	public static
	String getServerIdentity ()
	{
		if (ServerIdentityUtil.name == null) { 
			String name = getClasspathIdentity (); 
			if (name == null) { 
				name = getRandomIdentity (); 
			}
			ServerIdentityUtil.name = name; 
		}
		return ServerIdentityUtil.name; 
	}

	protected static
	String getClasspathIdentity ()
	{
		final ClassLoader cl = java.lang.Thread.currentThread ().getContextClassLoader (); 
		final InputStream is = cl.getResourceAsStream ("server.identity");
		
		if (is != null) { 
			try { 
				final String id = IOUtil.readFully (is);
				return id; 
			}
			catch (final IOException io_e) { 
				return null; 
			}
			finally { 
				IOUtil.safeClose (is);
			}
		}  
			
		return null; 
	}
	
	protected static 
	String getRandomIdentity ()
	{
		final long now = System.currentTimeMillis (); 
		final Random rand = new Random (now);
		final int value = rand.nextInt ();
		final String id = HexUtil.encodeInt (value); 
		return id; 
	}
	
	public static
	void setServerIdentity (final String name)
	{
		ServerIdentityUtil.name = name; 
		return; 
	}
}
