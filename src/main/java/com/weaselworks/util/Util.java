package com.weaselworks.util;

import java.beans.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 *
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All rights reserved.
 */

public class Util
{
    private
    Util ()
    {
        return; 
    }

    public static
    void dump (final Object o)
    {
    	if (o == null) {
    		System.err.println ("null"); 
    	} else { 
    		System.err.println ("(" + o.getClass().getName () + ") " + o); 
    	}
    	return; 
    }
    
    public static
    String getStackTrace (final Throwable aThrowable) 
    {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
    
    /**
     *
     * @param duration
     */

    public static
    void delay (final long duration)
    {
        try {
            Thread.sleep (duration);
        }
         catch (final InterruptedException i_e) {
             // IGNORED
         }
        
        return;
    }

    /**
     *
     * @param duration
     */

    public static
    void delay (final Duration duration)
    {
        delay (duration.getDuration ()); 
        return;
    }

    /**
     *
     * @param msg
     */

    public static
    void awaitEnter (final String msg)
    {
        try {
            System.out.println (msg);
            System.in.read ();
        }
        catch (final IOException io_e) {
            // IGNORED
        }
        return;
    }

    /**
     *
     */

    public static
    void awaitEnter ()
    {
        awaitEnter ("Press <Enter> to continue...");
        return; 
    }

    public static
    boolean isEqual (final Object o1, final Object o2)
    {
        if (o1 == null) {
            if (o2 == null) {
                return true;
            } else {
                return o2.equals (o1);
            }
        } else {
            return o1.equals (o2);
        }
        // NOT REACHED
    }

    public static 
    boolean isEqual (final byte b1, final byte b2)
    {
    	return b1 == b2; 
    }
    
    public static
    boolean isEqual (final byte [] b1, final byte [] b2) 
    {
    	if (b1 == null) { 
    		return b2 == null; 
    	} else if (b2 == null) {
    		return false; 
    	} else if (b1.length != b2.length) {
    		return false;
    	} else { 
    		for (int i = 0; i < b1.length; i ++) { 
    			if (b1 [i] != b2 [i]) { 
    				return false; 
    			}
    		}
    		return true; 
    	}
    	// NOT REACHED
    }
    
    public static
    boolean isEqual (final int i1, final int i2)
    {
        return i1 == i2;
    }

    public static
    boolean isEqual (final long l1, final long l2)
    {
        return l1 == l2;
    }

    public static
    boolean isEqual (final boolean b1, final boolean b2)
    {
        return b1 == b2;
    }

    public static
    boolean isEqual (final char c1, final char c2)
    {
        return c1 == c2;
    }

    public static
    boolean isEqual (final short s1, final short s2)
    {
        return s1 == s2;
    }

    /**
     * 
     * @param o1
     * @param o2
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    
	public static 
	boolean fieldEquals (final Object o1, final Object o2)
		throws IntrospectionException, InvocationTargetException, IllegalAccessException
	{
		// Get the list of fields in the first object
		
		final BeanInfo bi1 = Introspector.getBeanInfo (o1.getClass ());
		final Map<String, PropertyDescriptor> map1 = new HashMap<String, PropertyDescriptor> (); 
		for (final PropertyDescriptor pd : bi1.getPropertyDescriptors ()) { 
			map1.put (pd.getName (), pd); 
		}
		
		// Make sure the second object doesn't have a different set of fields
		// TODO: This probably doesn't work, need better field-level checking (crawford)
		
		final Map<String, PropertyDescriptor> map2; 
		
		if (o1.getClass () != o2.getClass ()) { 
			final BeanInfo bi2 = Introspector.getBeanInfo (o2.getClass ()); 
			map2 = new HashMap<String, PropertyDescriptor> (); 
			for (final PropertyDescriptor pd : bi2.getPropertyDescriptors ()) { 
				map2.put (pd.getName (), pd); 
			}
			if (map1.size () != map2.size ()) { 
				return false; 
			}
		} else { 
			map2 = map1;
		}

		// Now compare the two objects field by field
		
		for (final String field : map1.keySet ()) {
			if (field.equals ("class")) { 
				continue; 
			}
			final PropertyDescriptor pd1 = map1.get (field); 
			final PropertyDescriptor pd2 = map2.get (field);
			if (pd2 == null) { 
				return false; 
			}
			final Method m1 = pd1.getReadMethod ();
			final Method m2 = pd2.getReadMethod (); 
			final Object v1 = m1.invoke (o1, new Object [0]); 
			final Object v2 = m2.invoke (o2, new Object [0]);
			if (! Util.isEqual (v1, v2)) { 
				return false; 
			}
		}
		
		return true; 
	}
	
	/**
	 * Tests two boolean arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */

	public static
	boolean arrayEquals (final boolean [] a, final boolean [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}
	
	/**
	 * Tests two byte arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */

	public static
	boolean arrayEquals (final byte [] a, final byte [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}

	/**
	 * Tests two short arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	
	public static
	boolean arrayEquals (final short [] a, final short [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}
	
	/**
	 * Tests two int arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	
	public static
	boolean arrayEquals (final int [] a, final int [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}
	
	/**
	 * Tests two long arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */

	public static
	boolean arrayEquals (final long [] a, final long [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}

	/**
	 * Tests two float arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	
	public static
	boolean arrayEquals (final float [] a, final float [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}

	/**
	 * Tests two double arrays for equivalence.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	
	public static
	boolean arrayEquals (final double [] a, final double [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}
	
	/**
	 * Tests two char arrays for equivalence. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */

	public static
	boolean arrayEquals (final char [] a, final char [] b) 
	{
		if (a == null) { 
			if (b == null) { 
				return true; 
			} else { 
				return false; 
			}
		}
		if (a.length != b.length) { 
			return false; 
		}
		for (int i = 0; i < a.length; i ++) { 
			if (a [i] != b [i]) { 
				return false; 
			}
		}
		return true; 
	}

	/**
	 * 
	 * @param map1
	 * @param map2
	 * @return
	 */
	
    public static 
    boolean mapEquals (final Map<?, ?> map1, final Map<?, ?> map2)
    {
    	if (map1 == null) { 
    		if (map2 != null) {
    			return false; 
    		}
    	}
    	if (map1.size () != map2.size ()) { 
    		return false; 
    	}
    	for (final Object key : map1.keySet ()) { 
    		if (! Util.isEqual (map1.get (key), map2.get (key))) { 
    			return false; 
    		}
    	}
    	return true; 
    }
}

// EOF