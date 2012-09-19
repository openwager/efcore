package com.weaselworks.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*; 

import com.weaselworks.io.IOUtil;

/**
 * 
 * @author crawford
 *
 */

public class EchoServerClient 
{
	public static final String USAGE = "USAGE: " + EchoServerClient.class.getName () + " <host> <addr> <message>"; 
	
	public static
	void main (final String [] args)
		throws Exception
	{
		if (args.length != 3) { 
			System.err.println (USAGE); 
			System.exit (-1); 
			// NOT REACHED
		}
		
		final String host = args [0]; 
		final int port = Integer.parseInt (args [1]); 
		final String message = args [2]; 
		
		System.err.println ("Connecting to " + host + ":" + port + "... "); 
		final Socket socket = new Socket (host, port); 
		System.err.println ("connected."); 
		
		final InputStream is = socket.getInputStream (); 
		final OutputStream os = socket.getOutputStream (); 
		System.err.println ("Sending: " + message);
		os.write(message.getBytes ()); 
		os.write (0); 
		
		final String read = IOUtil.readFully (is);
		System.err.println ("Received: " + read); 
		return; 
	}
}

// EOF
