package com.weaselworks.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author crawford
 *
 */

public class SocketService 
	implements Runnable
{
	public
	SocketService (final ServerSocket sock, final SocketHandlerFactory factory)
	{
		setSocket (sock); 
		setFactory (factory); 
		return; 
	}
	
	public 
	SocketService (final int port, final SocketHandlerFactory factory)
		throws IOException
	{
		this (new ServerSocket (port), factory);
		setFactory (factory); 
		return; 
	}
	
	protected ServerSocket socket; 
	public ServerSocket getSocket () { return this.socket; } 
	public void setSocket (final ServerSocket socket) { this.socket = socket; return; }

	protected SocketHandlerFactory factory; 
	public SocketHandlerFactory getFactory () { return this.factory; } 
	public void setFactory (final SocketHandlerFactory factory) { this.factory = factory; return; } 
	
	protected Thread thread; 
	protected Thread getThread () { return this.thread; } 
	protected void setThread (final Thread thread) { this.thread = thread; return; } 
	
	public 
	void start ()
	{
		synchronized (this) { 
			if (getThread () != null) { 
				throw new IllegalStateException ("Already running."); 
			}
			final Thread thread = new Thread (this); 
			setThread (thread); 
			thread.start(); 
		}
		return; 
	}
	
	/**
	 * @see Runnable#run
	 */
	
	public void run() 
	{
		try { 
			System.err.println ("Socket server starting."); 
			while (true) { 
				System.err.println ("Awaiting connection."); 
				final Socket sock = getSocket ().accept (); 
				System.err.print ("Accepted connection from: " + sock.getRemoteSocketAddress()); 
				getFactory ().handleConnection (sock); 
			}
		}
		catch (final IOException io_e) { 
			io_e.printStackTrace (); 
		}
		System.err.println ("Socket server stopping.");
		return; 
	} 
}

// EOF
