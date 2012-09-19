package com.weaselworks.io;

import com.weaselworks.codec.xio.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOStream
{
	public
	XIOStream ()
	{
		return; 
	}
	
	public 
	XIOStream (final XIOCodecFactory factory)
	{
		setFactory (factory); 
		return; 
	}
	
	protected XIOCodecFactory factory; 
	public XIOCodecFactory getFactory () { return this.factory; }
	public void setFactory (final XIOCodecFactory factory) { this.factory = factory; return; } 
}

// EOF
