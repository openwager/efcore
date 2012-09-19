package com.weaselworks.io;

import java.io.*;

import org.apache.log4j.*;

import com.weaselworks.codec.xio.*;

/**
 * 
 * @author crawford
 *
 */

public class XIODebugInputStream
	extends XIOInputStream 
{
	protected static final Logger logger = Logger.getLogger (XIODebugInputStream.class); 
	
	public
	XIODebugInputStream (final InputStream is)
	{
		super (is); 
		return; 
	}
	
	public
	XIODebugInputStream (final InputStream is, final XIOCodecFactory factory)
	{
		super (is, factory); 
		return; 
	}
	
}

// EOF