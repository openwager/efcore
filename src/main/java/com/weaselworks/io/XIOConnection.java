package com.weaselworks.io;

import java.io.*;
import java.net.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOConnection
{
	public
	XIOConnection ()
	{
		return; 
	}

	public
	XIOConnection (final XIOInputStream xis, final XIOOutputStream xos) 
	{
		setInputStream (xis); 
		setOutputStream (xos); 
		return; 
	}

	public
	XIOConnection (final InputStream is, final OutputStream os)
	{
		this (is, os, null); 
		return; 
	}
	
	public
	XIOConnection (final InputStream is, final OutputStream os, final XIOCodecFactory factory)
	{
		this (new XIOInputStream (is, factory), new XIOOutputStream (os, factory)); 
		return; 
	}
	
	public
	XIOConnection (final Socket sock, final XIOCodecFactory factory)
		throws IOException
	{
		this (sock.getInputStream (), sock.getOutputStream (), factory); 
		return; 
	}
	
	protected XIOInputStream xis; 
	public XIOInputStream getInputStream () { return this.xis; } 
	public void setInputStream (final XIOInputStream xis) { this.xis = xis; return; } 
	
	protected XIOOutputStream xos; 
	public XIOOutputStream getOutputStream () { return this.xos; } 
	public void setOutputStream (final XIOOutputStream xos) { this.xos = xos; return; } 

	public
	void close ()
		throws IOException
	{
		xis.close ();
		xos.close (); 
		return; 
	}
	
	public
	Object readObject ()
		throws IOException
	{
		return xis.readObject (); 
	}
	
	public
	Object readObject (final Class<?> type)
		throws IOException
	{
		return xis.readObject (type); 
	}
	
	public
	void writeObject (final Object obj)
		throws IOException
	{
		xos.writeObject (obj); 
		return; 
	}
	
	public
	void writeObject (final Object obj, final Class<?> type)
		throws IOException
	{
		xos.writeObject (obj, type);
		return; 
	}
	
	public static
	XIOConnection getPipedPair () 
		throws IOException
	{
		final Pair<InputStream, OutputStream> pair = IOUtil.getPipePair ();
		final XIOInputStream xis = new XIOInputStream (pair.getLeft ()); 
		final XIOOutputStream xos = new XIOOutputStream (pair.getRight ()); 
		return new XIOConnection (xis, xos); 
	}
}

// EOF