package com.weaselworks.net;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author crawford
 *
 */

public interface SocketHandlerFactory 
{
	/**
	 * 
	 * @param socket
	 * @return
	 * @throws Exception
	 */
	
	public 
	void handleConnection (final Socket socket)
		throws IOException; 
}

// EOF