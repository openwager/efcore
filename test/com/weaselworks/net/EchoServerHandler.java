package com.weaselworks.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.weaselworks.io.IOUtil;

/**
 * 
 * @author crawford
 *
 */

public class EchoServerHandler 
	implements Runnable
{
	public
	EchoServerHandler (final Socket sock)
	{
		this (sock, false); 
		return; 
	}
	
	public
	EchoServerHandler (final Socket sock, final boolean start)
	{
		setSocket (sock); 
		if (start) { 
			start (); 
		}
		return;
	}

	protected Thread thread; 
	protected Thread getThread () { return this.thread; } 
	public void setThread (final Thread thread) { this.thread = thread; return; } 
	
	protected Socket sock; 
	protected Socket getSocket () { return this.sock; } 
	public void setSocket (final Socket sock) { this.sock = sock; return; } 
	
	public
	void start ()
	{
		synchronized (this) { 
			if (getThread () != null) { 
				throw new IllegalStateException ("Thread already started."); 
			}
			final Thread thread = new Thread (this); 
			setThread (thread); 
			thread.start (); 
		}
		return; 
	}

	public
	void run ()
	{
		System.err.println ("Servicing client on " + sock.getRemoteSocketAddress()); 
		InputStream is = null; 
		OutputStream os = null; 
		try { 
			is = sock.getInputStream (); 
			os = sock.getOutputStream (); 
			while (true) { 
				int c = is.read (); 
				if (c < 0 || c == (byte) '!') { 
					System.out.println ("ENDING"); 
					break; 
				}
				os.write ((byte) c); 
			}
		}
		catch (final IOException io_e) { 
			io_e.printStackTrace(); 
		}
		finally { 
			IOUtil.safeClose (os); 
			IOUtil.safeClose (is); 
		}
		System.err.println ("Ending client services on " + sock.getRemoteSocketAddress()); 
		return; 
	}
}

// EOF