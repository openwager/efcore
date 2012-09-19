package com.weaselworks.io.codec;

import java.io.*;
import java.util.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("unchecked")
public class ListCodec
	extends XIOCodecSupport<List>
		implements XIOCodec<List>
{
	public
	ListCodec ()
	{
		super (XIOConstant.OBJECT.LIST, List.class); 
		return; 
	}

    public
    List readBody (final XIOInputStream xis)
        throws IOException
    {
    	final int len = xis.rawInteger (); 
    	final List list = new ArrayList (len); 
    	for (int i = 0; i < len; i ++) { 
    		list.add (xis.readObject ()); 
    	}
    	return list; 
    }

    public
    void writeBody (final List list, final XIOOutputStream xos)
        throws IOException
    {
    	xos.rawInteger (list.size ()); 
    	for (final Object o : list) { 
    		xos.writeObject (o); 
    	}
	    return; 	    
    }
}

// EOF
