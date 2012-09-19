package com.weaselworks.io;

import java.io.*;

import org.apache.log4j.Logger;

import com.weaselworks.codec.xio.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOOutputStream
	extends XIOStream
{
	protected static final Logger logger = Logger.getLogger (XIOOutputStream.class); 
	
	public
	XIOOutputStream (final OutputStream os)
	{
		this (os, XIOCodecFactoryUtil.getDefaultFactory ()); 
		return; 
	}

	public
	XIOOutputStream (final OutputStream os, final XIOCodecFactory factory)
	{
		setOutputStream (os);
		setFactory (factory); 
		return; 
	}

	private static boolean DEBUG = false;  
//	private static boolean DEBUG = true;  
	
	protected
	void emit (final String str)
	{
		logger.info (str); 
		return; 
	}
	
	protected OutputStream os; 
	public OutputStream getOutputStream () { return this.os; } 
	public void setOutputStream (final OutputStream os) { this.os = os; return; } 

	public
	void close ()
	throws IOException
	{
		if (DEBUG) { 
			emit ("close()"); 
		}
		os.close (); 
		return; 
	}
	
	public
	void safeClose ()
		throws IOException
	{
		IOUtil.safeClose (os); 
		return; 
	}
	
	public
	void flush ()
		throws IOException
	{
		if (DEBUG) { 
			emit ("flush()"); 
		}
		os.flush (); 
		return; 
	}
	
	public
	void writeNull ()
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeNull ()"); 
		}
		rawByte (XIOConstant.NULL); 
		return; 
	}
	
	public
	void writeMarker ()
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeMarker()"); 
		}
		rawByte (XIOConstant.MARKER); 
		return; 
	}
	
	public
	void writeBoolean (boolean val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeBoolean(" + val + ")"); 
		}
		if (val) {
			rawByte (XIOConstant.BOOLEAN.TRUE); 
		} else { 
			rawByte (XIOConstant.BOOLEAN.FALSE); 
		}
		return;
	}
	
	public
	void writeByte (byte val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeByte (" + HexUtil.encodeByte (val) + ")"); 
		}
		rawByte (XIOConstant.BYTE);
		rawByte (val);
		return; 
	}
	
	public final 
	void rawByte (final byte val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawByte (" + HexUtil.encodeByte (val) + ")"); 
		}
		os.write (val); 
		return; 
	}	
	
    protected 
    String maybeDisplayable (final byte val)
    {
    	if (val > 0x31 && val < 0x127) { 
    		return "'" + (char) val + "'";
    	} else { 
    		return ""; 
    	}
    }
    
    public final 
	void rawBytes (final byte [] data)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawBytes (" + data + ")"); 
		}
		rawBytes (data, 0, data.length);
		return; 
	}
	
	public final
	void rawBytes (final byte [] data, final int offset, final int len)
		throws IOException
	{
    	if (DEBUG) { 
    		emit ("rawBytes(byte[], " + offset + ", " + len + ")"); 
    	}
		os.write (data, offset, len); 
    	if (DEBUG) { 
    		for (int i = 0; i < len; i ++) {
    			emit ("  " + (i + 1) + ": 0x" + HexUtil.encodeByte (data [offset + i]) + " " + maybeDisplayable (data [offset + i]));
    		}
    	}
	return; 
	}

	public
	void writeShort (final short val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeShort(" + HexUtil.encodeShort (val) + ")"); 
		}
		rawByte (XIOConstant.SHORT); 
		rawShort (val); 
		return; 
	}
	
	public
	void rawShort (final short val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawShort ("+ HexUtil.encodeShort (val) + ")"); 
		}
		rawByte ((byte) (val >> 8)); 
		rawByte ((byte) val); 
		return; 
	}
	
	public
	void writeInteger (final int val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeInteger (" + HexUtil.encodeInt (val) + ")"); 
		}
		rawByte (XIOConstant.INTEGER); 
		rawInteger (val); 
		return; 
	}
	
	public
	void rawInteger (final int val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawInteger (" + HexUtil.encodeInt (val) + ")"); 
		}
		rawByte ((byte) (val >> 24)); 
		rawByte ((byte) (val >> 16)); 
		rawByte ((byte) (val >> 8)); 
		rawByte ((byte) val); 
		return;
	}
	
	public
	void writeLong (final long val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeLong (" + HexUtil.encodeLong (val) + ")"); 
		}
		rawByte (XIOConstant.LONG); 
		rawLong (val); 
		return; 
	}
	
	public
	void rawLong (final long val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawLong(" + HexUtil.encodeLong(val) + ")"); 
		}
		rawByte ((byte) (val >> 56)); 
		rawByte ((byte) (val >> 48)); 
		rawByte ((byte) (val >> 40)); 
		rawByte ((byte) (val >> 32)); 
		rawByte ((byte) (val >> 24)); 
		rawByte ((byte) (val >> 16)); 
		rawByte ((byte) (val >> 8)); 
		rawByte ((byte) val); 
		return; 
	}
	
	public
	void writeFloat (final float val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeFloat (" + val + ")"); 
		}
		rawByte (XIOConstant.FLOAT); 
		rawFloat (val); 
		return; 
	}
	
	public
	void rawFloat (final float val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawFloat (" + val + ")"); 
		}
		final int ival = Float.floatToIntBits (val); 
		rawInteger (ival); 
		return; 
	}
	
	public 
	void writeDouble (final double val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeDouble (" + val + ")"); 
		}
		rawByte (XIOConstant.DOUBLE); 
		rawDouble (val); 
		return;
	}

	public
	void rawDouble (final double val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawDouble (" + val + ")"); 
		}
		final long lval = Double.doubleToLongBits (val); 
		rawLong (lval); 
		return; 
	}
	
	public
	void writeChar (final char val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeChar ( " + val + ")"); 
		}
		rawByte (XIOConstant.CHAR); 
		rawChar (val); 
		return;
	}
	
	public
	void rawChar (final char val)
		throws IOException
	{
		if (DEBUG) { 
			emit ("rawChar (" + val + ")"); 
		}
		if (val < 0x80) {
			rawByte ((byte) val); 
		} else if (val < 0x800) {
			rawByte ((byte) (0xc0 + ((val >> 6) & 0x1f))); 
			rawByte ((byte) (0x80 + (val & 0x3d))); 
		} else { 
			rawByte ((byte) (0xe0 + ((val >> 12) & 0xf)));
			rawByte ((byte) (0x80 + ((val >> 6) & 0x3f))); 
			rawByte ((byte) (0x80 + (val & 0x3f))); 
		}
		return; 
	}
		
//	public
//	void rawString (final String val)
//		throws IOException
//	{
//        final byte [] bytes = val.getBytes ("UTF-8"); 
//        writeBytes (bytes); 
//        return; 
//	}
	
	/**
	 * 
	 */
	
	public <T extends Enum<T>>
	void writeEnum (final T e) 
		throws IOException
	{
		writeObject (e.name ()); 
		return; 
	}
	
    public 
	void writeObject (final Object obj)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeObject (" + (obj == null ? null : obj.getClass ().getSimpleName ()) + ")"); 
		}
		if (obj == null) { 
			writeNull (); 			
		} else { 
	    	final Class<?> type = obj == null ? null : obj.getClass (); 
			writeObject (obj, type); 
		}
		return; 
	}
	
	@SuppressWarnings("unchecked")
	public
	void writeObject (final Object obj, final Class<?> type)
		throws IOException
	{
		if (DEBUG) { 
			emit ("writeObject (" + (obj == null ? null : obj.getClass ().getSimpleName ()) + ", " + type.getSimpleName () + ")"); 
		}
		if (obj == null) { 
			writeNull (); 			
		} else { 
			final XIOCodec codec = getFactory ().getCodec (type); 
			if (codec == null) {
				throw new IOException ("No codec found for object: " + type.getName ()); 
			} else { 
				codec.write (obj, this); 
			}		
		}
		return; 		
	}
	
	/**
	 * Used to send an array of boolean values. 
	 * 
	 * @param data
	 * @throws IOException
	 */
	
    public 
    void writeBooleans (final boolean [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeBooleans (" + data + ")"); 
    	}
    	if (data == null ) {
    		writeNull (); 
    	} else { 
	    	writeArrayHeader (XIOConstant.BOOLEAN.FALSE, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		writeBoolean (data [i]); 
	    	}
    	}    
    	return;     	
    }
    
    /**
     * Used to send an array of byte values. 
     * 
     * @param data
     * @throws IOException
     */
    
    public
    void writeBytes (final byte [] data)
   		throws IOException 
    {
    	if (DEBUG) { 
    		emit ("writeBytes (" + data + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.BYTE, data, data.length); 
	    	rawBytes (data);
    	}
    	return;    	
    }
    
    /**
     * Used to send an array of short values.
     *  
     * @param data
     */
    
    public 
    void writeShorts (final short [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeShorts (" +  data + ")");
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.SHORT, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		rawShort (data [i]); 
	    	}
    	}
    	return;     	
    	
    }
    
    /**
     * Used to send an array of int values.
     *  
     * @param data
     */
    
    public
    void writeIntegers (final int [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeIntegers (" + data + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
    		writeArrayHeader (XIOConstant.INTEGER, data, data.length); 
    		for (int i = 0; i < data.length; i ++) { 
    			rawInteger (data [i]); 
    		}
    	}
    	return;     	
    	
    }

    /**
     * Used to send an array of long values. 
     *
     * @param data
     */
    
    public 
    void writeLongs (final long [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeLongs (" + data + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.LONG, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		rawLong (data [i]); 
	    	}
    	}
    	return;     	    	
    }

    /**
     * Used to send an array of float values. 
     *  
     * @param data
     */
    
    public
    void writeFloats (final float [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeFloat (" + data + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.FLOAT, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		rawFloat (data [i]); 
	    	}
    	}
    	return;     	
    }    
    
    /**
     * Used to send an array of double values. 
     * 
     * @param data
     */
    
    public
    void writeDoubles (final double [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeDouble (" + data + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.DOUBLE, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		rawDouble (data [i]); 
	    	}
    	}
    	return;     		
    }    

    /**
     * Used to send an array of char values. 
     * 
     * @param data
     */
    
    public
    void writeChars (final char [] data)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeChars (" + (data == null ? null : "char[]") + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.CHAR, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		rawChar (data [i]); 
	    	}
    	}
    	return;     		
    }    
    
    /**
     * 
     * @param data
     * @throws IOException
     */
    
    public
    void writeObjects (final Object [] data)
    	throws IOException
	{
    	if (DEBUG) { 
    		emit ("writeObjects(" + data + ")"); 
    	}
    	if (data == null) { 
    		writeNull ();
    	} else { 
	    	writeArrayHeader (XIOConstant.OBJECT.INTERNAL, data, data.length); 
	    	for (int i = 0; i < data.length; i ++) { 
	    		writeObject (data [i]); 
	    	}
    	}
    	return;     		
	}

    /**
     * A utility method used to send the header bytes for a primitive array.
     * 
     * @param type
     * @param data
     * @param length
     * @throws IOException
     */
    
    protected
    void writeArrayHeader (final byte type, final Object data, final int length)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("writeArrayHeader (" + type + ", " + data.getClass().getSimpleName () + ", " + length + ")"); 
    	}
        if (data == null) {
            writeNull ();
            return;
        }
    	if (length > Integer.MAX_VALUE) { 
    		throw new IllegalArgumentException ("Array too long: " + length); 
    	}
    	rawByte (XIOConstant.ARRAY); 
    	rawByte (type); 
    	rawInteger (length);
    	return; 
    }
}

// EOF