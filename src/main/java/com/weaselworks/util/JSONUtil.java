package com.weaselworks.util;

import java.beans.*;
import java.lang.reflect.*;
import java.util.*;

/*
 * 
 */

public class JSONUtil
{
	private 
	JSONUtil ()
	{
		return; 
	}
	
	public static final String MIME_TYPE = "application/json";

	public static
	String toJson (final Object obj)
	{
		final StringBuilder sb = new StringBuilder (); 
		toJson (obj, sb); 
		return sb.toString (); 
	}
		
	public static 
	void toJson (final Iterator<Object> iter, final StringBuilder sb)
	{
		sb.append ('[');
		while (iter.hasNext ()) { 
			final Object obj = iter.next (); 
			toJson (obj, sb);
			if (iter.hasNext ()) { 
				sb.append (", ");
			}
		}
		sb.append (']'); 		
		return; 
	}
	
	@SuppressWarnings("unchecked")
	public static
	void toJson (final Object obj, final StringBuilder sb)
	{
		if (obj == null) { 
			sb.append ("null");  
		} else if (obj instanceof Map) { 
			final Map<String, Object> map = (Map<String, Object>) obj; 
			sb.append ("{ "); 
			final int len = map.size (); 
			int cnt = 0; 
			for (final String key : map.keySet ()) { 
				sb.append ("\"" + key + "\": "); 
				toJson (map.get (key), sb);
				if (++cnt < len) { 
					sb.append (", "); 
				}
			}
			sb.append (" }"); 
		} else if (obj instanceof Iterator) {
			final Iterator<Object> iter = (Iterator<Object>) obj; 
			toJson (iter, sb); 
		} else if (obj instanceof Enumeration) {
			final Enumeration<Object> e = (Enumeration<Object>) obj; 
			final EnumerationIterator<Object> iter = new EnumerationIterator<Object> (e); 
			toJson (iter, sb); 
		} else if (obj instanceof Iterable) { 			
			final Iterable<Object> iterable = (Iterable<Object>) obj; 
			final Iterator<Object> iter = iterable.iterator (); 
			toJson (iter, sb); 
		} else if (obj.getClass ().isArray ()) {  			
			final Object [] array = (Object []) obj;
			final List<Object> list = Arrays.asList (array); 
			toJson (list, sb); 
		} else if (obj instanceof String) { 
			sb.append ('\"'); 
			sb.append (obj); 
			sb.append ('\"'); 
		} else if (obj instanceof Boolean) { 
			sb.append (obj); 
		} else if (obj instanceof Integer) { 
			sb.append (obj); 
		} else if (obj instanceof Long) { 
			sb.append (obj); 
		} else if (obj instanceof Float) { 
			sb.append (obj); 
		} else if (obj instanceof Double) {
			sb.append (obj); 
		} else if (obj.getClass ().isEnum ()) { 
			final Enum<?> e = (Enum<?>) obj; 
			sb.append ('\"'); 
			sb.append (e.name ()); 
			sb.append ("\"");
		} else {
			sb.append ("{ "); 
	        try {
	            final BeanInfo beanInfo = Introspector.getBeanInfo (obj.getClass ());
	            final PropertyDescriptor [] props = beanInfo.getPropertyDescriptors ();
	            boolean first = true; 
	            
	            for (int i = 0; i < props.length; i ++) {
	                final PropertyDescriptor prop = props [i];
                    final String name = prop.getName (); 
	                final Method read = prop.getReadMethod ();
	                if (read != null) {
	                	if (first) { 
	                		first = false; 
	                	} else { 
		                    sb.append (", "); 
	                	}
                    	sb.append ("\"" + name + "\": "); 
	                    if (name.equals ("class")) {
	                    	sb.append ("\"" + obj.getClass ().getName () + "\""); 
	                    } else { 
	                        final Object val = read.invoke (obj, new Object[0]);
	                        toJson (val, sb); 
	                    }
	                }
	            }
	        }
	        catch (final Exception e) {
	        	// IGNORED
	        }
	        sb.append (" }"); 
		}
		return;
	}
}

// EOF