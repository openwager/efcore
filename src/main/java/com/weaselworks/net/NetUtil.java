package com.weaselworks.net;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.http.*;

import com.weaselworks.io.*;
import com.weaselworks.io.IOUtil;

import javax.swing.text.html.HTML;

public class NetUtil 
{
	private 
	NetUtil ()
	{
		return; 
	}

	/**
	 * 
	 * @param addr
	 * @return
	 */
	
	public static 
	int parseAddress (final String addr)
	{
		if (addr.indexOf (".") == -1) {
			return Integer.parseInt (addr); 
		} else { 
			final StringTokenizer toker = new StringTokenizer (addr, "."); 
			if (toker.countTokens () != 4) { 
				throw new IllegalArgumentException ("Invalid address: " + addr); 
			}
			int val = 0; 
			for (int i = 0; i < 4; i ++) { 
				val = (val << 8) | Integer.parseInt (toker.nextToken ());  
			}
			return val; 
		}
	}	
	
	/**
	 * A utility method that converts an IP address stored in a long back to the original 
	 * String (e.g., 1.2.3.4) representation. 
	 * 
	 * @param val
	 * @return
	 */
	
	public static 
	String toAddress (final int val)
	{
		return getInetAddress(val).getHostAddress (); 
	}
	
	/**
	 * Returns an InetAddress for an IPv4 32-bit integer IP address
	 * @param srcIPAddress
	 * @return
	 */
	
	public static
	InetAddress getInetAddress (int srcIPAddress)
	{
	    byte[] bytes = new byte[4];
	    for (int i = 3; i >= 0; i--) {
	    	bytes[i] = (byte) (srcIPAddress & 0x000000ff);
	    	srcIPAddress = srcIPAddress >> 8;
	    }
	    try {
	        return InetAddress.getByAddress (bytes);
	    }
	    catch (UnknownHostException e) {
	        throw new IllegalArgumentException("invalid IP address specified: " + srcIPAddress);
	    }
	}
	
	
	/**
	 * 
	 * @param spec
	 * @param method
	 * @param properties
	 * @param doInput
	 * @param doOutput
	 * @param useCaches
	 * @return
	 * @throws IOException
	 */
	
	public static
	HttpURLConnection createHttpUrlConnection (String spec, String method, String[][] properties, boolean doInput, boolean doOutput, boolean useCaches) 
		throws IOException 
	{
	    URL url = new URL(spec);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod(method);
	    for (int i = 0; properties != null && i < properties.length; i ++) {
	        conn.setRequestProperty(properties[i][0], properties[i][1]);
	    }
	    conn.setDoInput(doInput);
	    conn.setDoOutput(doOutput);
	    conn.setUseCaches(useCaches);
	    return conn;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	
	public static
	String getURLContents (final String urlstr)
		throws IOException
	{
//		final HttpURLConnection conn = createHttpUrlConnection (urlstr, "GET", null, true, true, false); 
//		final InputStream is = conn.getInputStream (); 
//		final String content = IOUtil.readFully (is); 

		final URL url = new URL (urlstr); 
		final URLConnection conn = url.openConnection ();
		if (conn instanceof HttpURLConnection) {
			final HttpURLConnection hconn = (HttpURLConnection) conn;
			if (hconn.getResponseCode() != HttpServletResponse.SC_OK) {
				final String msg = String.format ("Invalid response code: %d (%s)", hconn.getResponseCode (), hconn.getResponseMessage ());
				throw new IOException (msg);
			}
		}
		final InputStream is = conn.getInputStream ();
		final String content = IOUtil.readFully(is);
		is.close ();
		return content; 
	}
}

// EOF