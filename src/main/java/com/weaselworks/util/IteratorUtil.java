package com.weaselworks.util;

import java.util.*;
import java.io.PrintStream;
import java.io.OutputStream;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class IteratorUtil
{
    /**
     *  Private constructor to defeat instantiation .
     */

    private
    IteratorUtil ()
    {
        return;
    }

    /**
     *
     * @param iter
     * @return
     */

    public static
    int countElements (final Iterator<?> iter)
    {
        int cnt = 0;
        while (iter.hasNext ()) {
            cnt ++;
        }
        return cnt;
    }

    public static
    Iterator<?> toIterator (final Object obj)
    {
        if (obj instanceof Object []) {
            return toIterator (obj);
        } else if (obj instanceof boolean []) {
            return toIterator ((boolean []) obj);
        } else if (obj instanceof byte []) {
            return toIterator ((byte []) obj);
        } else if (obj instanceof char []) {
            return toIterator ((char []) obj);
        } else if (obj instanceof short []) {
            return toIterator ((short []) obj);
        } else if (obj instanceof int []) {
            return toIterator ((int []) obj);
        } else if (obj instanceof long []) {
            return toIterator ((long []) obj);
        } else if (obj instanceof float []) {
            return toIterator ((float []) obj);
        } else if (obj instanceof double []) {
            return toIterator ((double []) obj);
        } else if (obj instanceof Collection<?>) {
            return toIterator ((Collection<?>) obj);
        } else if (obj instanceof Iterator<?>) {
            return toIterator ((Iterator<?>) obj);
        } else if (obj instanceof Enumeration<?>) {
            return toIterator ((Enumeration<?>) obj);
        } else if (obj instanceof Map<?, ?>) {
            return toIterator ((Map<?,?>) obj);
        } else if (obj instanceof String) {
            return toIterator ((String) obj);
        } else {
            throw new IllegalArgumentException ();
        }

        // NOT REACHED
    }

    public static <Type>
    Iterator<Type> toIterator (final Type [] val)
    {
        return Arrays.asList (val).iterator ();
    }

    public static
    Iterator<Boolean> toIterator (final boolean [] val)
    {
        final Boolean [] wrapper = new Boolean [val.length];
        for (int i = 0; i < val.length; i ++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Short> toIterator (final short [] val)
    {
        final Short [] wrapper = new Short [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Byte> toIterator (final byte[] val)
    {
        final Byte [] wrapper = new Byte [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Character> toIterator (final char [] val)
    {
        final Character [] wrapper = new Character [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Integer> toIterator (final int [] val)
    {
        final Integer [] wrapper = new Integer [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Long> toIterator (final long [] val)
    {
        final Long [] wrapper = new Long [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper[i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Float> toIterator (final float [] val)
    {
        final Float [] wrapper = new Float [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator <Double> toIterator (final double [] val)
    {
        final Double [] wrapper = new Double [val.length];
        for (int i = 0; i < val.length; i++) {
            wrapper [i] = val [i];
        }
        return Arrays.asList (wrapper).iterator ();
    }

    public static
    Iterator<?> toIterator (final Collection<?> coll)
    {
        return coll.iterator ();
    }

    public static
    Iterator<?> toIterator (final Iterator<?> iter)
    {
        return iter;
    }

    public static <Type>
    Iterator <Type> toIterator (final Enumeration<Type> en)
    {
        return new EnumerationIterator<Type> (en);
    }

    public static
    Iterator <String> toIterator (final String val)
    {
        return toIterator (val, ",");
    }

    public static
    Iterator <String> toIterator (final String str, final String delim)
    {
        final StringTokenizer toker = new StringTokenizer(str, delim);
        return new Iterator<String>() {
            public boolean hasNext ()  {
                return toker.hasMoreTokens () ;
            }
           public String next () {
               return toker.nextToken ();
            }
            public void remove () {
                throw new UnsupportedOperationException ();
            }
        };
    }


    /**
     * A convenience method used to convert an {@linkplain Iterator}
     * into the equivalent {@linkplain List} structure to permit long-term,
     * random access to the iterated elements.
     *
     * @param iter
     * @return
     */

    public static <Type>
    List <Type> toList (final Iterator <Type> iter)
    {
        final List <Type> list = new ArrayList <Type> ();
        while (iter.hasNext ()) {
            list.add (iter.next ());
        }
        return list;
    }

    /**
     * A truly bizarre method used to convert an {@linkplain Iterator} into the
     *  equivalent {@linkplain Iterable} interface so that it can be used in the
     * new for loop. Why this is required is totally and completely beyond me. <
/p>
     *
     * This allows you to do: </p>
     *
     * <pre>
     * final List <Integer> list = new ArrayList <Integer> ();
     * for (int i = 0; i < 10; i ++) {
     *     list.add (i);
     * }
     * final Iterator <Integer> iter = list.iterator ();
     * for (int i : <b>IteratorUtil.toIterable (iter)</b>) {
     *     System.out.println (i);
     * }
     * </pre>
     *
     * @param iter
     * @return
     */

    public static <Type>
    Iterable <Type> toIterable (final Iterator <Type> iter)
    {
        return new Iterable <Type> () {
            public Iterator<Type> iterator () {
                return iter;
            }
        };
    }

    /**
     *
     * @param iter
     */

    public static
    void dump (final Iterator<?> iter)
    {
        dump (iter, System.out);
        return;
    }

    /**
     *
     * @param iter
     * @param os
     */

    public static
    void dump (final Iterator<?> iter, final OutputStream os)
    {
        final PrintStream ps = new PrintStream(os);
        int cnt = 0;

        while (iter.hasNext ()) {
            final Object elem = iter.next ();
            ps.println ("" + (++ cnt) + ": " + elem);
        }

        return;
    }
}
                
// EOF