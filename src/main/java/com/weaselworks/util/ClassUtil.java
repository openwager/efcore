package com.weaselworks.util;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class ClassUtil
{
    private
    ClassUtil ()
    {
        return;
    }

    /**
     * 
     * @param <T>
     * @param name
     * @param type
     * @return
     * @throws Exception
     */
    
    public static <T>
    T newInstance (final String name, final Class<T> type) 
    	throws Exception
    {
    	final Object obj = newInstance (name); 
    	return type.cast (obj); 
    }
    
    /**
     *
     *
     * @param name
     * @return
     * @throws Exception
     */

    public static
    Object newInstance (final String name)
        throws Exception
    {
		final ClassLoader cl = Thread.currentThread ().getContextClassLoader ();
		final Class<?> c = cl.loadClass (name);
		final Object obj = c.newInstance ();
		return obj; 
    }

    /**
     *
     * @param name
     * @return
     */

    public static
    Object newInstanceSilent (final String name)
    {
        try {
            return newInstance (name);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param c
     * @param ifc
     * @return
     */

	public static
    boolean implementsInterface (final Class<?> c, final Class<?> ifc)
    {
        for (Class<?> i : c.getInterfaces ()) {
            if (ifc.isAssignableFrom (i)) {
                return true;
            }
            if (implementsInterface (i, ifc)) { 
            	return true; 
            }
        }
        if (c.getSuperclass () != null) { 
        	return implementsInterface (c.getSuperclass (), ifc); 
        }
        return false;
    }

    /**
     *
      * @param c
     * @return
     */

    public static
    String getPackage (final Class<?> c)
    {
        final String cname = c.getName ();
        final int i = cname.lastIndexOf ('.');

        if (i < 0) {
            return "";
        } else {
            return cname.substring (0, i);
        }
        // NOT REACHED
    }

    /**
     * 
     * @param name
     * @return
     * @throws Exception
     */
    
    public static
    Object [] getEnumValues (final String name)
        throws Exception
    {
        final Class<?> clazz = Thread.currentThread ().getContextClassLoader ().loadClass (name);
        final Method method = clazz.getMethod ("values");
        return (Object []) method.invoke (clazz);
    }

	/**
	 * Gets a list of interfaces implemented by a given class or interface type. 
	 * 
	 * @param type
	 * @return
	 */
	
	public static
	List<Class<?>> getInterfaces (final Class<?> type)
	{
		final List<Class<?>> list = new ArrayList<Class<?>> ();
		addInterfaces (list, type); 
		return list; 
	}
	
	/**
	 * Utility method used by the {@linkplain #getInterfaces} method to recursively build
	 * up the interface list. 
	 * 
	 * @param list
	 * @param type
	 */
	
	protected static
	void addInterfaces (final List<Class<?>> list, final Class<?> type) 
	{
		if (type.isInterface ()) { 
			list.add (type); 
		}
		if (type.getSuperclass () != null) { 
			addInterfaces (list, type.getSuperclass ()); 
		}
		for (Class<?> si : type.getInterfaces ()) { 
			addInterfaces (list, si); 
		}
		return; 
	}
}

// EOF