package com.weaselworks.codec;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class VoidCodec
	implements Codec<Void>
{
	private 
	VoidCodec ()
	{
		return; 
	}
	
	public static final VoidCodec INSTANCE = new VoidCodec (); 
	
    public Void decode (InputStream is)
        throws IOException
    {
		return null; 
    }

    public void encode (Void type, OutputStream os)
        throws IOException
    {
	    return; 	    
    }
}

// EOF