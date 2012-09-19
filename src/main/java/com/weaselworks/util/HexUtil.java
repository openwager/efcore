package com.weaselworks.util;

import java.io.*;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class HexUtil
{
    /**
     *  Private constructor to defeat instantiation.
     */

    public
    HexUtil ()
    {
        return;
    }

    private final static char [] HEX_DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     *  Used to convert a byte to it's hex representation.
     *
     *  @param b the byte to be converted to a hex string
     */

    public static
    String encodeByte (final byte bval)
    {
        return "" + HEX_DIGITS [(bval >> 4) & 0x0f] +
                    HEX_DIGITS [bval & 0x0f];
    }

    public static
    String encodeShort (final short sval)
    {
        return "" + HEX_DIGITS [(sval >> 12) & 0x0f] +
                    HEX_DIGITS [(sval >>  8) & 0x0f] +
                    HEX_DIGITS [(sval >>  4) & 0x0f] +
                    HEX_DIGITS [sval & 0x0f];
    }

    /**
     *  Used to convert an int to it's hex representation.
     *
     *  @param ival the int value to be converted to a hex string
     */

    public static
    String encodeInt (final int ival)
    {
        return "" + HEX_DIGITS [(ival >> 28) & 0x0f] +
                    HEX_DIGITS [(ival >> 24) & 0x0f] +
                    HEX_DIGITS [(ival >> 20) & 0x0f] +
                    HEX_DIGITS [(ival >> 16) & 0x0f] +
                    HEX_DIGITS [(ival >> 12) & 0x0f] +
                    HEX_DIGITS [(ival >>  8) & 0x0f] +
                    HEX_DIGITS [(ival >>  4) & 0x0f] +
                    HEX_DIGITS [ival & 0x0f];
    }

    public static
    String encodeLong (final long val)
    {
        return "" + HEX_DIGITS [(int) ((val >> 60) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 56) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 52) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 48) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 44) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 40) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 36) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 32) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 28) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 24) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 24) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 20) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 16) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >> 12) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >>  8) & 0x0f)] +
                    HEX_DIGITS [(int) ((val >>  4) & 0x0f)] +
                    HEX_DIGITS [(int) (val & 0x0f)];
    }

    /**
     *
     * @param bytes
     * @return
     */

    public static
    String encodeBytes (final byte [] bytes)
    {
        final StringBuffer buf = new StringBuffer ();
        if (bytes == null) { 
        		buf.append ("null"); 
        } else { 
            for (int i = 0; i < bytes.length; i ++) {
                buf.append (HexUtil.encodeByte (bytes [i]));
            }
        }
        return buf.toString ();
    }

    public static
    byte[] decodeString(final String s)
    {
        int len = s.length();
        if (len <= 0 || (len % 2) != 0) {
            throw new NumberFormatException("Invalid string length: " + s.length());
        }

        byte[] ba = new byte[len / 2]; int n = 0;
        for (int i = 0; i < len; i = i + 2) {
            ba[n++] = (byte) ((decodeChar(s.charAt(i)) & 0x0F) << 4 | decodeChar(s.charAt(i + 1)) & 0x0F);
        }
        return ba;
    }

    /**
     * 
     * @param ch
     * @return
     */
    
    public static
    int decodeChar(final char ch) 
    {
//        for (int i = 0; i < HEX_DIGITS.length; i ++) {
//            if (HEX_DIGITS[i] == ch) {
//                return i;
//            }
//        }
    	if (ch >= '0' && ch <= '9') { 
    		return ch - '0'; 
    	} else if (ch >= 'a' && ch <= 'f') {
    		return ch - ('a' - 10); 
    	} else if (ch >= 'A' && ch <= 'F') { 
    		return ch - ('A' - 10); 
    	}
        throw new NumberFormatException("" + ch);
    }

    public static final int BYTES_PER_ROW = 16; 
    public static final int BYTES_PER_BREAK = 4; 
    public static final String PREFIX = "  "; 
    public static final String BREAK = " BREAK "; 
    public static final String SPACER = " | "; 
    
//    public static
//    void hexDump (final byte [] data, final OutputStream os)
//    	throws IOException
//    {
//    	final StringBuffer buf = new StringBuffer (); 
//    	
//    	// Figure out how many rows we need to display 
//    	
//    	int rows = data.length / BYTES_PER_ROW;
//    	if (data.length % BYTES_PER_ROW != 0) { 
//    		rows ++; 
//    	}
//    	
//    	// Accumulate each row in the buffer
//    	
//    	for (int row = 0; row < rows; row ++) {
//    		buf.append (PREFIX); 
//    		if (rows < 0x100) { 
//    			buf.append (encodeByte ((byte) row)); 
//    		} else { 
//    			buf.append (encodeShort ((short) row)); 
//    		}
//    		buf.append (": "); 
//    		final StringBuffer subbuf = new StringBuffer (); 
//    		for (int col = 0; col < BYTES_PER_ROW; col ++)  {
//        		final int offset = row * BYTES_PER_ROW + col;
//        		if (offset >= data.length) { 
//        			buf.append ("  ");
//        			subbuf.append (' '); 
//        		} else { 
//            		final byte c = data [offset]; 
//        			buf.append (encodeByte (c));
//        			if (c > 31 && c < 127) { 
//        				subbuf.append ((char) c); 
//        			} else { 
//        				subbuf.append ('.'); 
//        			}        		
//        		}
//    			if (col != 0 && (col % BYTES_PER_BREAK == 0)) { 
//    				buf.append (' ');;
//    				subbuf.append (' '); 
//    			}
//    		}
//    		buf.append (SPACER); 
//    		buf.append (subbuf.toString ());
//    		buf.append ('\n');     	
//    	}
//
//    	os.write (buf.toString ().getBytes ()); 
//    	return; 
//    }
    
    public static
    String getHexDump (final byte [] data)
    	throws IOException
    {
    	final StringBuffer buf = new StringBuffer (); 
    	
    	// Figure out how many rows we need to display 
    	
    	int rows = data.length / BYTES_PER_ROW;
    	if (data.length % BYTES_PER_ROW != 0) { 
    		rows ++; 
    	}
    	
    	// Accumulate each row in the buffer
    	
    	for (int row = 0; row < rows; row ++) {
    		buf.append (PREFIX); 
    		if (rows < 0x100) { 
    			buf.append (encodeByte ((byte) row)); 
    		} else { 
    			buf.append (encodeShort ((short) row)); 
    		}
    		buf.append (": "); 
    		final StringBuffer subbuf = new StringBuffer (); 
    		for (int col = 0; col < BYTES_PER_ROW; col ++)  {
        		final int offset = row * BYTES_PER_ROW + col;
    			if (col != 0 && (col % BYTES_PER_BREAK == 0)) { 
    				buf.append (' ');;
    				subbuf.append (' '); 
    			}
        		if (offset >= data.length) { 
        			buf.append ("  ");
        			subbuf.append (' '); 
        		} else { 
            		final byte c = data [offset]; 
        			buf.append (encodeByte (c));
        			if (c > 31 && c < 127) { 
        				subbuf.append ((char) c); 
        			} else { 
        				subbuf.append ('.'); 
        			}        		
        		}
    		}
    		buf.append (SPACER); 
    		buf.append (subbuf.toString ());
    		buf.append ('\n');     	
    	}

    	return buf.toString (); 
    }
    
    public static
    void hexDump (final byte [] bytes)	
    	throws IOException
    {
    	System.err.println (getHexDump (bytes)); 
    	return; 
    }
    
    public static
    void main (final String [] args)
    	throws IOException
    {
    	final byte [] data =new byte [253]; 
    	for (int i = 0; i < data.length; i ++) { 
    		data [i] = (byte) i; 
    	}
    	hexDump (data); 
    	
    	final String s = HexUtil.encodeBytes (data); 
    	System.out.println (s); 
    	final byte [] data2 = decodeString (s); 
    	final String s2 = HexUtil.encodeBytes (data2); 
    	System.out.println (s2); 
    	System.out.println (s.equals (s2)); 
    	return; 
    }
}

// EOF