package com.weaselworks.io.codec;

import java.beans.*;
import java.io.*;
import java.lang.reflect.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 * @param <Type>
 */

public class ReflectionCodec<A>
	extends XIOCodecSupport<A>
		implements XIOCodec<A>
{
	public
	ReflectionCodec (final int objectId, final Class<A> type)
		throws IntrospectionException
	{
		super (objectId, type); 
		init (); 
		return; 
	}
	
	protected PropertyDescriptor [] pds; 
	
	protected
	void init () 
		throws IntrospectionException
	{
		final BeanInfo bi = Introspector.getBeanInfo (getType ());
		pds = bi.getPropertyDescriptors ();
		return; 
	}
	
	/**
	 * 
	 */
	
    public 
    A readBody (final XIOInputStream xis)
        throws IOException
    {
    	final A obj; 
    	try { 
    		obj = getType ().newInstance (); 
			for (final PropertyDescriptor pd : pds) { 
				final String field = pd.getName (); 
				if (field.equals ("class")) { 
					continue; 
				}
				final Class<?> type = pd.getPropertyType ();
				final Method m = pd.getWriteMethod (); 
				if (type == Void.TYPE) {
					xis.readNull (); 
				} else if (type == Byte.TYPE) {
					final byte val = xis.rawByte ();
					m.invoke (obj, new Object [] { new Byte (val) }); 
				} else if (type == Short.TYPE) {
					final short val = xis.rawShort (); 
					m.invoke (obj, new Object [] { new Short (val) }); 
				} else if (type == Integer.TYPE) {
					final int val = xis.rawInteger (); 
					m.invoke (obj, new Object [] { new Integer (val) }); 
				} else if (type == Long.TYPE) {
					final long val = xis.rawLong (); 
					m.invoke (obj, new Object [] { new Long (val) }); 
				} else if (type == Float.TYPE) {
					final float val = xis.rawFloat (); 
					m.invoke (obj, new Object [] { new Float (val) }); 
				} else if (type == Double.TYPE) {
					final double val = xis.rawDouble (); 
					m.invoke (obj, new Object [] { new Double (val) }); 
				} else if (type == Character.TYPE) {
					final char val = xis.rawChar (); 
					m.invoke (obj, new Object [] { new Character (val) }); 
				} else if (type == Boolean.TYPE) {
					final boolean val = xis.readBoolean (); 
					m.invoke (obj, new Object [] { new Boolean (val) }); 
				} else {
					final Object val = xis.readObject (); 
					m.invoke (obj, new Object [] { val }); 
				}
			}
    	}
		catch (final IOException io_e) {
			throw io_e;
		}
		catch (final Exception e) { 
			throw new IOException (e.getMessage (), e); 
		}
		
		return obj; 
    }
    
    /**
     * 
     */

	public
	void writeBody (final A obj, final XIOOutputStream xos)
        throws IOException
    {
		try { 
			for (final PropertyDescriptor pd : pds) { 
				final String field = pd.getName (); 
				if (field.equals ("class")) { 
					continue; 
				}
				final Class<?> type = pd.getPropertyType ();
				final Method m = pd.getReadMethod (); 
				if (type == Void.TYPE) {
					xos.writeNull (); 
				} else if (type == Byte.TYPE) {
					final byte val = (Byte) m.invoke (obj, (Object []) null); 
					xos.rawByte (val); 
				} else if (type == Short.TYPE) {
					final short val = (Short) m.invoke (obj, (Object []) null);
					xos.rawShort (val); 
				} else if (type == Integer.TYPE) {
					final int val = (Integer) m.invoke (obj, (Object []) null); 
					xos.rawInteger (val); 				
				} else if (type == Long.TYPE) { 
					final long val = (Long) m.invoke (obj, (Object []) null); 
					xos.rawLong (val); 				
				} else if (type == Float.TYPE) {
					final float val = (Float) m.invoke (obj, (Object []) null); 
					xos.rawFloat (val); 
				} else if (type == Double.TYPE) {
					final double val = (Double) m.invoke (obj, (Object []) null); 
					xos.rawDouble (val); 
				} else if (type == Character.TYPE) {
					final char val = (Character) m.invoke (obj, (Object []) null); 
					xos.rawChar (val);
				} else if (type == Boolean.TYPE) {
					final boolean val = (Boolean) m.invoke (obj, (Object []) null); 
					xos.writeBoolean (val); 				
				} else { 
					final Object val = m.invoke (obj, (Object []) null); 
					xos.writeObject (val); 
				}
			}
		}
		catch (final IOException io_e) {
			throw io_e;
		}
		catch (final Exception e) { 
			throw new IOException (e.getMessage (), e); 
		}
		return; 
	}
}

// EOF