package com.weaselworks.util;

import java.util.*;
import java.util.regex.*;
import java.beans.PropertyDescriptor;
import java.beans.Introspector;
import java.beans.BeanInfo;
import java.lang.reflect.Method;

//import org.apache.log4j.*;


/**
 * Comment goes here.
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class StringUtil
{
    private
    StringUtil ()
    {
        return;
    }

    /**
     *
     * @param vals
     * @return
     */

    public static
    String toString (final Object [] vals)
    {
        final StringBuffer buf = new StringBuffer ("{");
        for (int i = 0; i < vals.length; i ++) {
            buf.append (vals [i]);
            if (i != (vals.length - 1)) {
                buf.append (", ");
            }
        }
        buf.append ("}");
        return buf.toString ();
    }

    public static
    String toString (final List<?> vals)
    {
    	StringBuffer buf = null; 
    	for (final Object val : vals) { 
    		if (buf == null) { 
    			buf = new StringBuffer ();
    			buf.append (val); 
    		} else { 
    			buf.append (','); 
    			buf.append (val); 
    		}
    	}
    	return buf.toString (); 
    }
    
    /**
     *
     * @param str
     * @return
     */

    public static
    boolean isEmpty (final String str)
    {
        return str == null || str.length () == 0; 
    }

    /**
     *
     * @param str
     * @return
     */

    public static
    boolean isDigits (final String str)
    {
        final int len = str.length ();
        for (int i = 0; i < len; i ++) {
            if (! Character.isDigit (str.charAt (i))) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param base
     * @param set
     * @return
     */

    public static
    int span (final String base, final String set)
    {
        final int len = base.length ();
        final int slen = set.length ();
        int i;

        outer:
        for (i = 0; i < len; i ++) {
            final char c = base.charAt (i); 
            for (int j = 0; j < slen; j ++) {
                if (c == set.charAt (j)) {
                    continue outer;
                }
            }
            break;
        }

        return i;
    }

    /**
     *
     * @param base
     * @param set
     * @return
     */

    public static
    boolean fullSpan (final String base, final String set)
    {
        return span (base, set) == base.length ();
    }

    /**
     *
     * @param start
     * @param end
     * @param base
     * @param repl
     * @return
     */

    public static
    String replace (final int start, final int end, final String base, final String repl)
    {
        String left = "", right = "";

        if (start > 0) {
            left = base.substring (0, start);
        }
        if (end < base.length ()) {
            right = base.substring (end);
        }

        return left + repl + right;
    }

    /**
     *
     * @param str
     * @param pos
     * @param add
     * @return
     */

    public static
    String insert (final String str, final int pos, final String add)
    {
        return replace (pos, pos, str, add);
    }

    /**
     *
     * @param str
     * @param pos
     * @param add
     * @return
     */

    public static
    String insert (final String str, final int pos, final char add)
    {
        return replace (pos, pos, str, "" + add);
    }

    /**
     *
     * @param str
     * @return
     */

    public static
    String nullToEmpty (final String str)
    {
            return str == null ? "" : str;
    }

    /**
     *
     * @param str
     * @param delim
     * @return
     */

    public static
    String [] splitString (final String str, final String delim)
    {
        final StringTokenizer toker = new StringTokenizer(str, delim);
        final int cnt = toker.countTokens ();
        final String [] strs = new String [cnt];

        for (int i = 0; i < cnt; i++) {
                strs[i] = toker.nextToken ();
        }

        return strs;
    }

    /**
     *
     * @param str
     * @param delim
     * @return
     */

    public static
    List<String> splitStringToList (final String str, final String delim)
    {
        return Arrays.asList(splitString (str, delim));
    }

    /**
     *
     * @param c
     * @param cnt
     * @return
     */

    public static
    String repeat (final char c, int cnt)
    {
        final StringBuffer sb = new StringBuffer (cnt);
        while (cnt-- > 0) {
            sb.append (c);
        }
        return sb.toString ();
    }

    /**
     *
     * @param s
     * @param cnt
     * @return
     */

    public static
    String repeat (final String s, int cnt)
    {
        final StringBuffer sb = new StringBuffer (s.length () * cnt);
        while (cnt-- > 0) {
            sb.append (s);
        }
        return sb.toString ();
    }

    /**
     *
      * @param base
     * @param orig
     * @param repl
     * @return
     */

    public static
    String replaceAll (String base, final String orig, final String repl)
    {
        int pos = 0, i;

        while ((i = base.indexOf (orig, pos)) >= 0) {
            base = StringUtil.replace (i, i + orig.length (), base, repl);
            pos += repl.length ();
        }

        return base;
    }

    /**
     *
     * @param orig
     * @param map
     * @return
     */

    public static
    String replaceAll (final String orig, final Map<String, String> map)
    {
        String str = orig;
        for (final String key : map.keySet ()) {
        	final String repl = Matcher.quoteReplacement (map.get (key)); 
//        	System.err.println ("str.replaceAll ('" + ("\\$\\{" + key + "\\}") + "', '" + map.get (key) + "')");  
            str = str.replaceAll ("\\$\\{" + key + "\\}", repl);
        }
        return str;
    }

    /**
     *
     * @param val
     * @return
     */

    public static
    String commify (final int val)
    {
        StringBuffer buf = new StringBuffer ("" + val);
        int pos = buf.length () - 3;
        while (pos > 0) {
            buf.insert (pos, ',');
            pos -= 3;
        }
        return buf.toString ();
    }

    /**
     *
      * @param source
     * @param separators
     * @return
     */

    public static List<String> split (final String source, final String separators)
    {
        boolean checkQuotes = (separators.indexOf ('"') < 0);
        boolean inQuotes = false;

        final List<String> ret = new ArrayList<String> ();
        final StringBuffer b = new StringBuffer ();
        char [] src = source.toCharArray ();

        // buffer to build the segment in

        for (final char c : src) {
            if (c == '\"' && checkQuotes) {
                inQuotes = !inQuotes;
                continue;
            }

            if (inQuotes) {
                b.append (c);
            } else if (separators.indexOf (c) < 0) {
                b.append (c);
            }
            else {
                if (b.length () > 0) {
                    ret.add (b.toString ());
                    b.delete (0, b.length ());
                }
            }
        }

        if (b.length () > 0) {
            ret.add (b.toString ());
        }

        return ret;
    }

    /**
     *
     * @param in
     * @return
     */

    public static
    String toUrlString (final String in)
    {
        return in.toLowerCase ().replaceAll ("\\s+", "-").replaceAll ("[^\\w\\d]+", "-");
    }

    /**
     *
     * @param values
     * @return
     */

    public static
    String buildString (final int [] values)
    {
        StringBuilder sb = new StringBuilder ();
        sb.append ("[");
        for (int i = 0; values != null && i < values.length; i++) {
            if (i > 0) {
                sb.append (", ");
            }
            sb.append (values[i]);
        }
        sb.append ("]");
        return sb.toString ();
    }

    /**
     *
     * @param values
     * @return
     */

    public static
    String buildString (final long [] values)
    {
        StringBuilder sb = new StringBuilder ();
        sb.append ("[");
        for (int i = 0; values != null && i < values.length; i++) {
            if (i > 0) {
                sb.append (", ");
            }
            sb.append (values[i]);
        }
        sb.append ("]");
        return sb.toString ();
    }

    /**
     *
     * @param values
     * @return
     */

    public static
    String buildString (final boolean [] values)
    {
        StringBuilder sb = new StringBuilder ();
        sb.append ("[");
        for (int i = 0; values != null && i < values.length; i++) {
            if (i > 0) {
                sb.append (", ");
            }
            sb.append (values[i]);
        }
        sb.append ("]");
        return sb.toString ();
    }

    /**
     *
     * @param values
     * @return
     */

    public static
    String buildString (final String [] values)
    {
        final StringBuilder sb = new StringBuilder ();
        sb.append ("[");
        for (int i = 0; values != null && i < values.length; i++) {
            if (i > 0) {
                sb.append (", ");
            }
            sb.append (values[i]);
        }
        sb.append ("]");
        return sb.toString ();
    }

    /**
     *
     * @param array
     * @return
     */

    public static
    String buildString (final byte [] array)
    {
        final StringBuffer sb = new StringBuffer ();
        sb.append ("[");
        for (int i = 0; array != null && i < array.length; i++) {
            if (i > 0) {
                sb.append (", ");
            }
            sb.append (array[i]);
        }
        sb.append ("]");
        return sb.toString ();
    }

    /**
     *
     * @param array
     * @return
     */

    public static
    String buildString (final float [] array)
    {
        final StringBuffer sb = new StringBuffer ();
        sb.append ("[");
        for (int i = 0; array != null && i < array.length; i++) {
            if (i > 0) {
                sb.append (", ");
            }
            sb.append (array[i]);
        }
        sb.append ("]");
        return sb.toString ();
    }

    /**
     *
     * @param values
     * @param delimiter
     * @return
     */

    public static
    String implode (final String [] values, final String delimiter)
    {
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; values != null && i < values.length; i++) {
            if (i > 0) {
                sb.append (delimiter);
            }
            sb.append (values[i]);
        }
        return sb.toString ();
    }

    /**
     *
     * @param values
     * @param delimiter
     * @return
     */

    public static
    String join (final String [] values, final String delimiter)
    {
            return implode (values, delimiter);
    }

    /**
     *
     * @param values
     * @param delimiter
     * @return
     */

    public static
    String implode (final Collection<String> values, final String delimiter)
    {
            return implode (values.toArray (new String [values.size ()]), delimiter);
    }

    /**
     *
     * @param input
     * @param delimiter
     * @return
     */

    public static
    String [] explode (final String input, final String delimiter)
    {
        String [] values = input.split (delimiter);
        for (String val : values) {
            val.trim ();
        }
        return values;
    }

    /**
     *
     * @param str
     * @param searchKey
     * @return
     */

    public static
    int lastIndexOf (final String str, final String searchKey)
    {
        int index = -1;
        int start = 0;
        final int length = str.length ();
        int i = str.indexOf (searchKey, start);

        while (i != -1 && (i + 1) < length) {
            index = i;
            start = i + 1;
            i = str.indexOf (searchKey, start);
        }

        return index;
    }

    /**
     *
     * @param obj
     * @return
     */

    public static
    String toString (final Object obj)
    {
        final StringBuffer buf = new StringBuffer ();
        buf.append (obj.getClass().getName ());
        buf.append ('[');

        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo (obj.getClass ());
            final PropertyDescriptor [] props = beanInfo.getPropertyDescriptors ();
            boolean first = true;

            for (int i = 0; i < props.length; i ++) {
                final PropertyDescriptor prop = props [i];
                if (prop.getName ().equals ("class")) {
                    continue;
                }
                if (first) {
                    first = false;
                } else {
                    buf.append (',');
                }
                final Method read = prop.getReadMethod ();
                if (read != null) {
                    buf.append (prop.getName ());
                    buf.append ('=');
                    final Object val = read.invoke (obj, new Object[0]);
                    buf.append (val);
                }
            }
        }
        catch (final Exception e) {
            buf.append (e.toString ());
        }

        buf.append (']');
        return buf.toString ();
    }

    /**
     * Encodes an integer using the provided alphabet
     * @param input
     * @param alphabet
     * @return
     */

    public static
    String encode (final int input, final String alphabet)
    {
        StringBuilder s = new StringBuilder();

        if(input < alphabet.length ()) {
            s.append (alphabet.charAt ((int)input));
        } else {
            s.append (encode(input/alphabet.length (), alphabet));
            int temp = (int)(input % alphabet.length ());
            s.append(alphabet.charAt(temp));
        }

        return s.toString ();
    }

	/**
	 * 
	 * @param obj
	 * @return
	 */
	
    public static
    String curlyToString (final Object obj)
    {
    	final StringBuffer buf = new StringBuffer (); 
    	curlyToString (obj, buf, 0, new HashSet<Object> ()); 
    	return buf.toString (); 
    }

    /**
     * 
     * @param obj
     * @param buf
     * @param indent
     */
    
//    protected static final Logger logger = Logger.getLogger (StringUtil.class); 
    
    protected static
    void curlyToString (final Object obj, final StringBuffer buf, final int indent, final Set<Object> already)
    {    	
//    	System.err.println ("OBJ = " + obj); 
//    	System.err.println ("ALREADY = " + already); 
//    	System.err.println (already.contains (obj)); 
    	if (already.size () > 10) { buf.append ("ABORT"); return; } 
    	
    	if (obj == null) { 
    		buf.append (obj);
    		buf.append ("\n");
    	} else if (already.contains (obj)) { 
    		buf.append (obj.getClass ().getName () + " { ... ALREADY ... }");
    		return; 
    	} else {  
    		already.add (obj); 
    		buf.append (obj.getClass ().getName () + " {\n"); 
    	}
    	
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo (obj.getClass ());
            final PropertyDescriptor [] props = beanInfo.getPropertyDescriptors ();
            boolean first = true;
            int output = 0; 

            for (int i = 0; i < props.length; i ++) {
                final PropertyDescriptor prop = props [i];
                final String name = prop.getName (); 
                if (name.equals ("class") || name.equals ("codec")) { 
                    continue;
                }               
                final Method read = prop.getReadMethod ();
                if (read != null) {
                    if (first) {
                        first = false;
                    } else {
                        buf.append (",\n");
                    }
                    output ++; 
                	buf.append (StringUtil.repeat ("  ", indent + 1)); 
                    buf.append (prop.getName ());
                    buf.append ('=');
                    try { 
	                    final Object val = read.invoke (obj, new Object[0]);
	                    if (val == null) { 
	                    	buf.append ("null"); 
	                    } else { 
//		                    final Class<?> type = val.getClass ();
		                    if (val instanceof Boolean 
		                    	|| val instanceof Short 
		                    	|| val instanceof Integer
		                    	|| val instanceof Long
		                    	|| val instanceof Float
		                    	|| val instanceof Double
		                    	|| val instanceof Character
		                    	|| val instanceof Collection<?>
		                    	|| val instanceof Map<?, ?>
		                    	|| val instanceof String
		                    	|| val instanceof Void) {
		                    	buf.append (val.toString ());                     
		                    } else { 
		                    	curlyToString (val, buf, indent + 1, already);
		                    }
	                    }
                    }
                    catch (final Exception e) {
	                    	buf.append ("ERROR: " + e.getMessage ());  
                    }
                }
            }

        	if (output > 0) { 
        		buf.append ("\n"); 
        	}
        }
        catch (final Exception e) {
        	e.printStackTrace ();
            buf.append (e.toString ());
        }

    	buf.append (StringUtil.repeat ("  ", indent));
    	buf.append ("}"); 
    	return;     	
    }

	public static
	String getUrlName (final String input)
	{
		String output = input.toLowerCase (); 
		output = output.replace (' ', '-'); 
		output = output.replaceAll ("[^a-z0-9\\-]", ""); 
		return output; 
	}
	
	public static String strip (final String input)
	{
		return input.replaceAll("\\s", "");
	}
}

// EOF