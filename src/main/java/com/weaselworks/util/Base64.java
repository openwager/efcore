package com.weaselworks.util;

/**
 * 
 * @author crawford
 *
 */

public class Base64
{
	private
	Base64 ()
	{
		return; 
	}
	
    static private byte [] codes = new byte [256];

    static {
        for (int i=0; i<256; i++) {
            codes[i] = -1;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            codes[i] = (byte) (i - 'A');
        }
        for (int i = 'a'; i <= 'z'; i++) {
            codes[i] = (byte) (26 + i - 'a');
        }
        for (int i = '0'; i <= '9'; i++) {
            codes[i] = (byte)(52 + i - '0');
        }
        codes['+'] = 62;
        codes['/'] = 63;
    }

    static private byte [] alphabet = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/',
        '='
    };

    /**
     *
     * @param str
     * @return
     */

    static public
    byte [] encode (final String str)
    {
        return encode (str.getBytes ());
    }

    /**
     *
     *
     *  @param data
     *  @return
     */

    static public
    byte [] encode (final byte [] data)
    {
        final byte [] out = new byte [((data.length + 2) / 3) * 4];

        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;

            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data [i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data [i + 2]);
                quad = true;
            }

            out [index + 3] = alphabet [(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out [index + 2] = alphabet [(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out [index + 1] = alphabet [val & 0x3F];
            val >>= 6;
            out [index + 0] = alphabet [val & 0x3F];
        }

        return out;
    }

    /**
     *
     * @param str
     * @return
     */

    static public
    byte [] decode (final String str)
    {
         return decode (str.getBytes ());
    }

    /**
     *
     *
     *  @param data
     *  @return
     */

    static public
    byte [] decode (final byte [] data)
    {
        int len = ((data.length + 3) / 4) * 3;
        if (data.length > 0 && data [data.length - 1] == '=') --len;
        if (data.length > 1 && data [data.length - 2] == '=') --len;
        final byte [] out = new byte [len];
        int shift = 0, accum = 0, index = 0;

        for (int ix = 0; ix < data.length; ix ++) {
            int value = codes [data[ix] & 0xFF];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out [index++] = (byte) ((accum >> shift) & 0xff);
                }
            }
        }

        if (index != out.length) {
            throw new IllegalStateException ("Miscalculated data length!");
        }

        return out;
    }
}

// EOF