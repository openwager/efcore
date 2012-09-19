package com.weaselworks.util;

import java.lang.reflect.*;

/**
 * General purpose utility methods for working with enumerated types. 
 * 
 * @author crawford
 *
 */

public class EnumUtil
{
	private
	EnumUtil ()
	{
		return; 
	}
	
    /**
     * A utility method used to return all of the values for an enumeration type identifed
     * by the specified class name. 
     *  
     * @param name
     * @return
     * @throws Exception
     */
    
	@SuppressWarnings("unchecked")
    public static <T extends Enum<?>>
	T [] getEnumValues (final String name)
	    throws Exception
	{
		final Class<?> clazz = Thread.currentThread ().getContextClassLoader ().loadClass (name);
		final Method method = clazz.getMethod ("values");
		return (T []) method.invoke (clazz);
	}
}

// EOF