package com.weaselworks.io.codec;

import java.io.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class PrimitiveCodec<Type>
	extends XIOCodecSupport<Type>
{
	@SuppressWarnings("unchecked")
    public 
	PrimitiveCodec (final int objectId)
	{
		super (objectId, (Class<Type>) toType (objectId)); 
		return; 
	}
	
	/**
	 * 
	 */
	
	public static final int [] OBJECT_IDS = { 
		XIOConstant.OBJECT.BOOLEAN,
		XIOConstant.OBJECT.BYTE,
		XIOConstant.OBJECT.SHORT,
		XIOConstant.OBJECT.INTEGER,
		XIOConstant.OBJECT.LONG,
		XIOConstant.OBJECT.FLOAT,
		XIOConstant.OBJECT.DOUBLE,
		XIOConstant.OBJECT.CHARACTER		
	};
	
	/**
	 * 
	 * @param objectId
	 * @return
	 */
	
    protected static 
	Class<?> toType (final int objectId)
	{
		switch (objectId) { 
			case XIOConstant.OBJECT.BOOLEAN: 
				return Boolean.class; 
			case XIOConstant.OBJECT.BYTE: 
				return Byte.class; 
			case XIOConstant.OBJECT.SHORT: 
				return Short.class; 
			case XIOConstant.OBJECT.INTEGER: 
				return Integer.class; 
			case XIOConstant.OBJECT.LONG: 
				return Long.class; 
			case XIOConstant.OBJECT.FLOAT: 
				return Float.class; 
			case XIOConstant.OBJECT.DOUBLE: 
				return Double.class; 
			case XIOConstant.OBJECT.CHARACTER: 
				return Character.class;
			default: 
				throw new IllegalArgumentException ("Unrecognized objectId: " + objectId); 
		}
		
		// NOT REACHED 
	}

    @SuppressWarnings("unchecked")
    public
    Type readBody (final XIOInputStream xis)
        throws IOException
    {
		switch (getObjectId ()) { 
			case XIOConstant.OBJECT.BOOLEAN:
				return (Type) new Boolean (xis.readBoolean ());   
			case XIOConstant.OBJECT.BYTE: 
				return (Type) new Byte (xis.rawByte ()); 
			case XIOConstant.OBJECT.SHORT:
				return (Type) new Short (xis.rawShort ()); 
			case XIOConstant.OBJECT.INTEGER: 
				return (Type) new Integer (xis.rawInteger ());  
			case XIOConstant.OBJECT.LONG:
				return (Type) new Long (xis.rawLong ()); 
			case XIOConstant.OBJECT.FLOAT:
				return (Type) new Float (xis.rawFloat ()); 
			case XIOConstant.OBJECT.DOUBLE: 
				return (Type) new Double (xis.rawDouble ()); 
			case XIOConstant.OBJECT.CHARACTER:
				return (Type) new Character (xis.rawChar ()); 
			default:
				throw new IllegalStateException ("Unrecognized objectId: " + objectId); 
		}

		// NOT REACHED
    }

    public
    void writeBody (final Type type, final XIOOutputStream xos)
        throws IOException
    {
		switch (getObjectId ()) { 
			case XIOConstant.OBJECT.BOOLEAN:
				xos.writeBoolean ((Boolean) type); 
				return; 
			case XIOConstant.OBJECT.BYTE: 
				xos.rawByte ((Byte) type); 
				return; 
			case XIOConstant.OBJECT.SHORT: 
				xos.rawShort ((Short) type); 
				return; 
			case XIOConstant.OBJECT.INTEGER: 
				xos.rawInteger ((Integer) type); 
				return; 
			case XIOConstant.OBJECT.LONG: 
				xos.rawLong ((Long) type); 
				return; 
			case XIOConstant.OBJECT.FLOAT: 
				xos.rawFloat ((Float) type); 
				return; 
			case XIOConstant.OBJECT.DOUBLE: 
				xos.rawDouble ((Double) type); 
				return; 
			case XIOConstant.OBJECT.CHARACTER: 
				xos.rawChar ((Character) type); 
				return; 
			default:
				throw new IllegalStateException ("Unrecognized objectId: " + objectId); 
		}

		// NOT REACHED
    }
}

// EOF