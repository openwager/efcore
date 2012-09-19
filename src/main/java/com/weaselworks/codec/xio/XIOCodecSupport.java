package com.weaselworks.codec.xio;

import java.io.*;

import org.apache.log4j.*;

import com.weaselworks.io.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class XIOCodecSupport<Type>
	implements XIOCodec<Type>
{
	protected static final Logger logger = Logger.getLogger (XIOCodecSupport.class);
	
	protected
	XIOCodecSupport (final int objectId, final Class<Type> type)
	{
		setObjectId (objectId); 
		setType (type); 
		return; 
	}
	
	protected int objectId; 
	public int getObjectId () { return this.objectId; } 
	protected void setObjectId (final int objectId) { this.objectId = objectId; return; } 
	
	protected Class<Type> type; 
	public Class<Type> getType () { return this.type; } 
	protected void setType (final Class<Type> type) { this.type = type; return; } 
	
	/**
	 * A convenience method used to create a new instance
	 * so that superclass codecs can do their work without having to know
	 * too much. 
	 * 
	 * @return
	 */
	
	protected 
	Type newInstance ()
	{
		try {
	        return getType ().newInstance ();
        } catch (final Exception e) {
        	logger.error (e.getMessage (), e);
        	return null; 
        } 
        
        // NOT REACHED
	}
	
	public
    void write (final Type obj, final XIOOutputStream xos)
        throws IOException
    {
	   if (obj == null) { 
		   xos.writeNull ();
		   return; 
	   }
        xos.rawByte (XIOConstant.OBJECT.INTERNAL);
        xos.rawInteger (getObjectId ());
        writeBody (obj, xos); 
        return;
    }

    /**
     * @see com.twofish.io.PortableSerializer#readObject(PortableInputStream)
     */

    public
    Type read (final XIOInputStream xis)
        throws IOException
    {
        final byte val = (byte) xis.rawByte ();
        if (val == XIOConstant.NULL) {
            return null;
        } else if (val != XIOConstant.OBJECT.INTERNAL) {
            throw new IOException ("Expected '" + XIOConstant.OBJECT.INTERNAL + "'; found '" + val + "'.");
        }
        final int objectId = xis.rawInteger () ;
        if (getObjectId () != objectId) {
            throw new IOException ("Expected type " + HexUtil.encodeInt(getObjectId ()) + "; found " + HexUtil.encodeInt (objectId) + ".");
        }
        return readBody (xis);
    }	
}

// EOF