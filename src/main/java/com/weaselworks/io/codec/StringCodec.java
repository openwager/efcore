package com.weaselworks.io.codec;

import java.io.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class StringCodec
	extends XIOCodecSupport<String>
		implements XIOCodec<String>
{
	public
	StringCodec ()
	{
		super (XIOObjectConstant.STRING, String.class); 
		return; 
	}

	public 
	String readBody (final XIOInputStream xis)
		throws IOException 
	{
		final String val = xis.rawString (); 
		return val; 
	}

	public
	void writeBody (final String val, final XIOOutputStream xos)
		throws IOException
	{
//		xos.rawString (val);
		final byte [] bytes = val.getBytes ("UTF-8"); 
		xos.writeBytes (bytes); 
		return; 
	}
}

// EOF