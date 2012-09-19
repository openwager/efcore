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
public class SetCodec
	extends XIOCodecSupport<Set>
		implements XIOCodec<Set>
{
	public
	SetCodec ()
	{
		super (XIOConstant.OBJECT.SET, Set.class); 
		return; 
	}

    public
    Set readBody (final XIOInputStream xis)
        throws IOException
    {
    	final int len = xis.rawInteger (); 
    	final Set set = new HashSet (len); 
    	for (int i = 0; i < len; i ++) { 
    		set.add (xis.readObject ()); 
    	}
    	return set; 
    }

    public
    void writeBody (final Set set, final XIOOutputStream xos)
        throws IOException
    {
    	xos.rawInteger (set.size ()); 
    	for (final Object element : set) { 
    		xos.writeObject (element); 
    	}
    	return;
    }
}

// EOF
