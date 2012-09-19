package com.weaselworks.net;

/**
 * 
 * @author crawford
 *
 */

public class EchoServer 
{
	public static final String USAGE = "USAGE: java " + EchoServer.class.getName () + " <port>"; 
	
	public static
	void main (final String [] args)
		throws Exception
	{
		if (args.length != 1) { 
			System.err.println (USAGE); 
			System.exit (-1);
			// NOT REACHED
		}
		
		final int port = Integer.parseInt (args [0]); 
		final SocketHandlerFactory handler = new EchoServerHandlerFactory (); 
		final SocketService sock = new SocketService (port, handler); 
		System.err.println ("Starting EchoServer on port:" + port); 
		sock.start (); 
		return; 
	}
}

// EOF