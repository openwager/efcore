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

public class DateCodec	
	extends XIOCodecSupport<Date>
		implements XIOCodec<Date>
{
	public
	DateCodec ()
	{
		super (XIOConstant.OBJECT.DATE, Date.class); 
		return; 
	}

	public
	Date readBody (final XIOInputStream xis)
        throws IOException
    {
		final long time = xis.rawLong (); 
		final Date date = new Date (time); 
		return date;
    }

	public
	void writeBody (final Date type, final XIOOutputStream xos)
        throws IOException
    {
		xos.rawLong (type.getTime ()); 
		return; 
    }
}

// EOF 