package com.weaselworks.net;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author crawford
 *
 */

public class EchoServerHandlerFactory
	implements SocketHandlerFactory
{
	public
	EchoServerHandlerFactory ()
	{
		return; 
	}
	
	public void handleConnection(final Socket socket)
		throws IOException
	{
		final EchoServerHandler handler = new EchoServerHandler (socket); 
		handler.start (); 
		return; 
	}
}

// EOF