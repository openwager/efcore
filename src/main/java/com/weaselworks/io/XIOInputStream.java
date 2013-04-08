package com.weaselworks.io;

import java.io.*;

import org.apache.log4j.*;

import com.weaselworks.codec.xio.*;
import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class XIOInputStream
	extends XIOStream
{
	private static final Logger logger = Logger.getLogger (XIOInputStream.class); 
	
	public
	XIOInputStream (final InputStream is)
	{
		this (is, XIOCodecFactoryUtil.getDefaultFactory ()); 
		return; 
	}
	
	public
	XIOInputStream (final InputStream is, final XIOCodecFactory factory)
	{
		setInputStream (is); 
		setFactory (factory); 
		return; 
	}
	
	private static boolean DEBUG = false;  
//	private static boolean DEBUG = true;  
	
	protected
	void emit (final String str)
	{
		logger.info ("<- " + str); 
		return; 
	}
	
	protected InputStream is; 
	public InputStream getInputStream () { return this.is; } 
	public void setInputStream (final InputStream is) { this.is = is; return; } 

	public
	void close ()
		throws IOException
	{
		if (DEBUG) { 
			emit ("close()"); 
		}
		is.close (); 
		return; 
	}
	
	public
	void safeClose ()
		throws IOException
	{
		IOUtil.safeClose (is); 
		return; 
	}
	
    protected int peek = -1;

    protected
    int peek ()
        throws IOException
    {
        return peek (false);
    }

    protected
    int peek (final boolean eofAllowed)
        throws IOException
    {
        if (peek >= 0) {
            return peek;
        }
        peek = is.read ();
//		if (DEBUG) { 
//			if (peek == -1) { 
//				emit ("peek() : EOF"); 
//			} else { 
//				emit ("peek() : 0x" + HexUtil.encodeByte ((byte) peek)); 
//			}
//		}
        if (peek < 0 && ! eofAllowed) {
            throw new EOFException ();
        }
        return peek;
    }

    public
    boolean hasNext ()
        throws IOException
    {
        return peek (true) != -1;
    }

    public
    byte peekType ()
        throws IOException
    {
        return (byte) peek (false);
    }

    protected
    int read ()
        throws IOException
    {
        final int c = read (false);
		return c; 
    }

    protected
    int read (final boolean eofAllowed)
        throws IOException
    {
    	final int c; 
        if (peek >= 0) {
            c = peek;
            peek = -1;
        } else { 
        	c = is.read (); 
    		if (c < 0 && ! eofAllowed) {
                throw new EOFException ();
            }
        }
		if (DEBUG) { 
			if (c == -1) { 
				emit ("read () : EOF"); 
			} else { 
				emit ("read () : 0x" + HexUtil.encodeByte ( (byte) c)); 
			}
		}
        return c;
    }
	
    public
    Object readNull ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readNull()"); 
    	}
    	expect (XIOConstant.NULL); 
    	return null; 
    }
    
    public
    void readMarker ()
    	throws IOException
   	{
    	if (DEBUG) { 
    		emit ("readMarker()"); 
    	}
    	expect (XIOConstant.MARKER); 
    	return; 
   	}
    
    public
    boolean readBoolean ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readBoolean()"); 
    	}
    	final int found = read (false);
    	if (found == XIOConstant.BOOLEAN.TRUE) { 
    		return true; 
    	} else if (found == XIOConstant.BOOLEAN.FALSE) { 
    		return false; 
    	} else { 
    		throw new IOException ("Expected boolean found '0x" + HexUtil.encodeByte ((byte) found) + "'."); 
    	}
    	// NOT REACHED
    }
    
    public
    byte readByte ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readByte()"); 
    	}
    	expect (XIOConstant.BYTE);
    	final byte val = rawByte (); 
    	return val; 
    }
    
    public 
    byte rawByte ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("rawByte()"); 
    	}
    	final byte val = (byte) read (); 
    	return val;
    }
    
//    public
//    byte [] readBytes ()
//        throws IOException
//    {
//        final ByteArrayOutputStream baos = new ByteArrayOutputStream ();
//        boolean first = true;
//
//        for (;;) {
//            final byte tag = (byte) read ();
//            if (tag == XIOConstant.NULL) {
//                if (! first) {
//                    throw new IOException ("Bizarre, embedded null.");
//                }
//                return null;
//            }
//            first = false;
//            if (tag != XIOConstant.BYTES.FIRST && tag != XIOConstant.BYTES.LAST) {
//                throw new IOException ("Expected byte array, found '0x" + HexUtil.encodeByte ((byte) tag) + "'.");
//            }
//            final int len = (read () << 8) + read ();
//            for (int i = 0; i < len; i ++) {
//                baos.write (read ()); 
//            }
//            if (tag == XIOConstant.BYTES.LAST) {
//                break;
//            }
//        }
//
//        return baos.toByteArray ();
//    }
    
    protected 
    String maybeDisplayable (final byte val)
    {
    	if (val > 0x31 && val < 0x127) { 
    		return "'" + (char) val + "'";
    	} else { 
    		return ""; 
    	}
    }
    
    public
    void rawBytes (final byte [] data)
    	throws IOException
	{
    	if (DEBUG) { 
    		emit ("rawBytes()"); 
    	}
    	final int length = is.read (data);
    	if (DEBUG) { 
    		for (int i = 0; i < length; i ++) {
    			emit ("  " + (i + 1) + ": 0x" + HexUtil.encodeByte (data [i]) + " " + maybeDisplayable (data [i]));
    		}
    	}
    	if (length != data.length) { 
    		throw new EOFException (); 
    	}
    	return; 
	}
    
    public
    void rawBytes (final byte [] data, final int offset, final int length)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("rawBytes(byte[], " + offset + ", " + length + ")"); 
    	}
    	final int result = is.read (data, offset, length); 
    	if (DEBUG) { 
    		for (int i = 0; i < length; i ++) {
    			emit ("  " + (i + 1) + ": 0x" + HexUtil.encodeByte (data [offset + i]) + " " + maybeDisplayable (data [offset + i]));
    		}
    	}
    	if (result != length) { 
    		throw new EOFException (); 
    	}
    	return; 
    }
    
    public 
    short readShort ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readShort()"); 
    	}
    	expect (XIOConstant.SHORT); 
    	final short val = rawShort ();  
    	return val;
    }
    
    public
    short rawShort ()
    	throws IOException
	{
    	if (DEBUG) { 
    		emit ("rawShort()"); 
    	}
    	final short val = (short) ((read () << 8) + read ()); 
    	return val; 
	}
    
    public
    int readInteger ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readInteger()"); 
    	}
    	expect (XIOConstant.INTEGER); 
    	final int val = rawInteger (); 
    	return val; 
    }
    
    public
    int rawInteger ()
    	throws IOException
	{
    	if (DEBUG) { 
    		emit ("rawInteger()"); 
    	}
    	final int val =  (read () << 24) + (read () << 16) + (read () << 8) + read (); 
    	return val; 
	}
    
    public
    long readLong ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readLong()"); 
    	}
    	expect (XIOConstant.LONG); 
    	final long val = rawLong (); 
    	return val; 
    }
    
    public
    long rawLong ()
    	throws IOException
    {
        if (DEBUG) { 
        	emit ("rawLong()"); 
        }
        final long i56 = read (), i48 = read (), i40 = read (), i32 = read (), i24 = read (), i16 = read (), i8 = read (), i0 = read ();
        final long val = (i56 << 56) + (i48 << 48) + (i40 << 40) + (i32 << 32) + (i24 << 24) + (i16 << 16) + (i8 << 8) + i0;
        return val;    	
    }
    
    public
    float readFloat ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readFloat()"); 
    	}
    	expect (XIOConstant.FLOAT); 
    	final float val = rawFloat (); 
    	return val; 
    }
    
    public
    float rawFloat ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("rawFloat()");
    	}
    	final int ival = rawInteger (); 
    	final float val = Float.intBitsToFloat (ival);
    	return val; 
    }
    
    public
    double readDouble ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readDouble()"); 
    	}
    	expect (XIOConstant.DOUBLE); 
    	final double val = rawDouble (); 
    	return val;
    }
    
    public 
    double rawDouble ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("rawDouble()"); 
    	}
    	final long lval = rawLong (); 
    	final double val = Double.longBitsToDouble (lval); 
    	return val; 
    }
    
    public
    char readChar ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readChar()"); 
    	}
    	expect (XIOConstant.CHAR); 
    	final char c = rawChar ();
    	return c; 
    }
    
    public
    char rawChar ()
    	throws IOException
	{
    	if (DEBUG) { 
    		emit ("rawChar()"); 
    	}
        int c1 = read ();
        if ( c1 < 0x80) {
            return (char) c1;
        } else if ((c1 & 0xe0) == 0xc0) {
            return (char) (((c1 & 0x1f) << 6) + (read () & 0x3f));
        } else if ((c1 & 0xf0) == 0xe0) {
            return (char) (((c1 &0x0f) << 12) + ((read () & 0x3f) << 6) + (read () & 0x3f));
        } else {
            throw new IOException ("Invalid character encoding.");
        }

        // NOT REACHED	
	}
    
    public
    int expectNullable (final byte expected)
    	throws IOException
    {
    	final byte type = (byte) read ();
    	if (type == XIOConstant.NULL || type == expected) { 
    		return type; 
    	} else { 
    		throw new IOException ("Expected '0x" + HexUtil.encodeByte ((byte) expected) + "', found '0x" + HexUtil.encodeByte ((byte) type) + "'."); 
    	}
    	
    	// NOT REACHED
    }
    
    public
    String rawString ()
    	throws IOException
    {
        final byte [] bytes = readBytes (); 
        return new String (bytes, "UTF-8"); 
    }
    
    /**
     * 
     * @param <T>
     * @param type
     * @return
     * @throws IOException
     */
    
    public <T extends Enum<T>>
    T readEnum (final Class<T> type) 
    	throws IOException
    {
    	final String val = readObject (String.class); 
    	if (val == null) { 
    		return null; 
    	}
    	return Enum.valueOf (type, val);
    }
    
    @SuppressWarnings("rawtypes")
	public
    Object readObject ()
    	throws IOException
	{
    	if (DEBUG) { 
    		emit ("readObject()"); 
    	}
    	
    	final Object result; 
    	final int peek = read (false);  
    	
    	switch (peek) { 
    		case XIOConstant.NULL: 
    			readNull ();
    			result = null; 
    			break; 
    		case XIOConstant.OBJECT.INTERNAL:    			
    			final int objectId = rawInteger ();
    			final XIOCodecFactory factory = getFactory (); 
    			final XIOCodec codec = factory.getCodec (objectId); 
    			if (codec == null) { 
    				throw new IOException ("No codec found for objectId=" + objectId); 
    			}
    			result = codec.readBody (this); 
    			break; 
   			default:
   	            throw new IOException ("Expected '" + XIOConstant.OBJECT.INTERNAL + "'; found '" + peek + "'.");
    	}
    	
    	return result;
	}
    
    @SuppressWarnings("unchecked")
    public <Type>
    Type readObject (Class<Type> type)
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readObject(" + type.getSimpleName () + ")"); 
    	}
    	final Type result; 
    	if (peek () == XIOConstant.NULL) {
    		readNull ();
    		result = null; 
    	} else { 
    		final XIOCodecFactory factory = getFactory (); 
    		final XIOCodec<? super Type> codec = factory.getCodec (type);
    		result = (Type) codec.read (this); 
    	}
    	return result; 
    }
    
    /**
    * 
    * @param type
    * @throws IOException
    */
    
    protected
    void expect (final byte type)
    	throws IOException
    {
    	if (DEBUG) { 
    		final String txt; 
    		switch (type) { 
    			case XIOConstant.ARRAY:  txt = "ARRAY"; break; 
    			case XIOConstant.BYTE: txt = "BYTE"; break; 
    			case XIOConstant.CHAR: txt = "CHAR"; break; 
    			case XIOConstant.DOUBLE: txt = "DOUBLE"; break; 
    			case XIOConstant.FLOAT: txt = "FLOAT"; break; 
    			case XIOConstant.INTEGER: txt = "INTEGER"; break; 
    			case XIOConstant.LONG: txt = "LONG"; break; 
    			case XIOConstant.MARKER: txt = "MARKER"; break; 
    			case XIOConstant.NONE: txt = "NONE"; break; 
    			case XIOConstant.NULL: txt = "NULL"; break; 
    			case XIOConstant.SHORT: txt = "SHORT"; break; 
    			case XIOConstant.OBJECT.INTERNAL: txt = "OBJECT"; break; 
    			default: txt = HexUtil.encodeByte (type); break; 
    		}
    		emit ("expect(" + txt + ")"); 
    	}
    	final int found = read ();
    	if (type != found) { 
    		 throw new IOException ("Expected '0x" + HexUtil.encodeByte (type) + "' found '0x" + HexUtil.encodeByte ((byte) found) + "'."); 
    	}
    	return; 
    }
    
    /**
     * Reads an array of boolean values from the input stream. 
     * 
     * @return
     * @throws IOException
     */

    public 
    boolean [] readBooleans ()
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readBooleans()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	} 
    	final int length = readArrayHeader (XIOConstant.BOOLEAN.FALSE); 
    	final boolean [] data = new boolean [length]; 
    	for (int i = 0; i < length; i ++) { 
    		data [i] = readBoolean (); 
    	}
    	return data; 
    }
    
    public
    byte [] readBytes () 
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readBytes()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	}
    	final int length = readArrayHeader (XIOConstant.BYTE); 
    	final byte [] data = new byte [length]; 
    	rawBytes (data); 
    	return data; 
    }
    
    /**
     * Reads an array of short values from the input stream. 
     */
    
    public 
    short [] readShorts () 
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readShorts()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	} 
    	final int length = readArrayHeader (XIOConstant.SHORT); 
    	final short [] data = new short [length]; 
    	for (int i = 0; i < length; i ++) { 
    		data [i] = rawShort (); 
    	}
    	return data; 
    }
    
    /**
     * Reads an array of integer values from the input stream.
     *  
     * @return
     * @throws IOException
     */
    
    public
    int [] readIntegers ()
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readIntegers()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	} 
    	final int length = readArrayHeader (XIOConstant.INTEGER); 
    	final int [] data = new int [length]; 
    	for (int i = 0; i < length; i ++) { 
    		data [i] = rawInteger (); 
    	}
    	return data;     	
    }
    
    /**
     * Reads an array of long values from the input stream. 
     * 
     * @return
     * @throws IOException
     */

    public 
    long [] readLongs ()
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readLong()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	} 
    	final int length = readArrayHeader (XIOConstant.LONG); 
    	final long [] data = new long [length]; 
    	for (int i = 0; i < length; i ++) { 
    		data [i] = rawLong (); 
    	}
    	return data;     	
    }

    /**
     * Reads an array of float values from the input stream. 
     * 
     * @return
     * @throws IOException
     */
    
    public
    float [] readFloats ()
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readFloats()"); 
    	}
	    	if (peek () == XIOConstant.NULL) { 
	    		return null; 
	    	} 
	    	final int length = readArrayHeader (XIOConstant.FLOAT); 
	    	final float [] data = new float [length]; 
	    	for (int i = 0; i < length; i ++) { 
	    		data [i] = rawFloat (); 
	    	}
	    	return data;     	
    }
    
    /**
     * Reads an array of double values from the input stream. 
     * 
     * @return
     * @throws IOException
     */
    
    public
    double [] readDoubles ()
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readDoubles()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	} 
    	final int length = readArrayHeader (XIOConstant.DOUBLE); 
    	final double [] data = new double [length]; 
    	for (int i = 0; i < length; i ++) { 
    		data [i] = rawDouble (); 
    	}
    	return data;     	
    }
    
    /**
     * Reads an array of char values from the input stream. 
     * 
     * @return
     * @throws IOException
     */
    
    public
    char [] readChars ()
    	throws IOException
    {
    	if (DEBUG) { 
    		emit ("readChars()"); 
    	}
    	if (peek () == XIOConstant.NULL) { 
    		return null; 
    	} 
    	final int length = readArrayHeader (XIOConstant.CHAR); 
    	final char [] data = new char [length]; 
    	for (int i = 0; i < length; i ++) { 
    		data [i] = rawChar (); 
    	}
    	return data;     	
    }
    
    /**
     * A utility method used to read the array header from the stream and return
     * the length of the array that follows. Arrays are not polymorphic and the type of the 
     * array must be known up front. 
     * 
     * @param type
     * @return
     * @throws IOException
     */
    
    protected
    int readArrayHeader (final byte type)
		throws IOException
    {
    	if (DEBUG) { 
    		emit ("readArrayHeader(type=0x" + HexUtil.encodeByte (type) + ")"); 
    	}
    	expect (XIOConstant.ARRAY); 
    	expect (type); 
    	final int length = rawInteger (); 
    	return length; 
    }
}

// EOF