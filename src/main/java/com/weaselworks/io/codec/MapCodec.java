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

@SuppressWarnings("rawtypes")
public class MapCodec
	extends XIOCodecSupport<Map>
		implements XIOCodec<Map>
{
	public
	MapCodec ()
	{
		super (XIOConstant.OBJECT.MAP, Map.class); 
		return; 
	}

    public
    Map readBody (final XIOInputStream xis)
        throws IOException
    {
    	final int len = xis.rawInteger (); 
    	final Map map = new HashMap (); 
    	for (int i = 0; i < len; i ++) { 
    		final Object key = xis.readObject (); 
    		final Object value = xis.readObject (); 
    		map.put (key, value); 
    	}
    	return map; 
    }

    public
    void writeBody (final Map map, final XIOOutputStream xos)
        throws IOException
    {
	    final int len = map.size ();
	    xos.rawInteger (len); 
	    for (final Object key : map.keySet ()) { 
	    	xos.writeObject (key); 
	    	xos.writeObject (map.get (key)); 
	    }
	    return; 
    }
}

// EOF
