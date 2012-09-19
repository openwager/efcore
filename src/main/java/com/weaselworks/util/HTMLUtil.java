package com.weaselworks.util;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */
public class HTMLUtil
{
    private
    HTMLUtil ()
    {
        return;
    }

    /**
     *
     * @param c
     * @return
     */

    public static
    String toEntityRef (final char c)
    {
        switch (c)
        {
            case '&': return "&amp;";
             case '\'': return "&apos;";
             case '"': return "&quot;";
             case '>': return "&gt;";
             case '<': return "&lt;";
        }
        return null;
    }

    /**
     *
     * @param c
     * @return
     */

    public static
    String escape (final char c)
    {
        final String eref = toEntityRef (c);

        if (eref != null) {
            return eref;
        } else if ((c >= ' ' && c <= 0x7e) || c == '\n' || c == '\r' || c== '\t') {
            return "" + c;
        } else {
            return "&#" + Integer.toString (c) + ";";
        }

        // NOT REACHED
    }

    /**
     *
     * @param s
     * @return
     */

    public static
    String escape (final String s)
    {
        final char [] c = s.toCharArray ();
        final StringBuffer buf = new StringBuffer ();
        for (int i = 0; i < c.length; i ++) {
            buf.append (escape (c [i])); 
        }
        return buf.toString ();
    }

}

// EOF