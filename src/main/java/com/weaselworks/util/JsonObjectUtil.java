package com.weaselworks.util;

import io.vertx.core.json.JsonObject;

/**
 * 
 * @author leecrawford
 *
 */

public class JsonObjectUtil
{
	private 
	JsonObjectUtil ()
	{
		return; 
	}
	
	public static<T>
	String dump (final T [] vals)
	{
		if (vals == null) { 
			return "null"; 
		} else { 
			final StringBuffer buf = new StringBuffer (); 
			buf.append ('['); 
			for (int i = 0; i < vals.length; i ++) {
				if (i != 0) { 
					buf.append (", "); 
				}
				final T val = vals [i]; 
				buf.append (val); 
			}
			buf.append (']'); 
			return buf.toString (); 
		}
	}
	
	public static<T>
	T get (final JsonObject json, final String path)
	{
		return get (json, path, null); 
	}

	public static<T>
	T get (final JsonObject json, final String path, final T def)
	{
		assert json != null; 
		assert path != null; 
		
		final String [] split = path.split ("\\."); 
//		System.out.println (dump (split)); 
		return get (json, split, def); 
	}
	
	public static<T>
	T get (final JsonObject json, final String [] path)
	{
		return get (json, path, null); 
	}

	public static<T>
	T get (final JsonObject json, final String [] path, final T def)
	{		
		assert json != null; 
		assert path != null; 
		assert path.length > 0; 
		
		final int len = path.length;
		JsonObject o = json; 
		
		for (int i = 0; i < len; i ++) {
			final String node = path [i]; 
			if (i != len - 1) { 
				o = o.getJsonObject (node);
			} else { 
				if (o.containsKey (node)) {
					return (T) o.getValue (node);
				} else {  
					System.out.println ("DEFAULT"); 
					return  def; 
				}
			}
		}
	
		// NOT REACHED
		throw new IllegalStateException ("Shouldn't fall-through to here."); 
	}
	
	public static
	JsonObject merge (final JsonObject a, final JsonObject b)
	{
		for (final String field : b.fieldNames ()) {
			final Object from = b.getValue (field);
			if (a.containsKey (field)) {
				final Object to = a.getValue (field);
				if (to instanceof JsonObject && from instanceof JsonObject) { 
					merge ((JsonObject) to, (JsonObject) from); 
				} else { 
					a.put (field, from);
				}
			} else { 
				a.put (field, from);
			}
		}
		return a; 
	}
	
	/**
	 * 
	 * @param json
	 * @param paths
	 * @param value
	 */
	
	public static <T>
	void put (JsonObject json, final String [] paths, final T value)
	{
		final int len = paths.length; 
		for (int i = 0; i < len - 1; i ++) { 
			final String path = paths [i]; 
			JsonObject child = json.getJsonObject (path);
			if (child == null) { 
				child = new JsonObject ();
				json.put (path, child);
			} 
			json = child; 
		}
		json.put (paths [len - 1], value);
		return; 
	}
}	

// EOF