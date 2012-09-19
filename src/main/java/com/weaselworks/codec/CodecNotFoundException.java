package com.weaselworks.codec;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
public class CodecNotFoundException
	extends Exception
{
	public
	CodecNotFoundException (final String msg)
	{
		super (msg); 
		return; 
	}
	
	public
	CodecNotFoundException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}

}

// EOF