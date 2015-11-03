package com.weaselworks.codec.json;

import java.util.*;

import org.json.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class JsonCodecSupport<Type >
	implements JsonCodec<Type>
{
	protected
	JsonCodecSupport (final String type, final Class<? extends Type> classType)
	{
		setType (type); 
		setClassType (classType); 
		return; 
	}
	
	protected String type; 
	public String getType () { return this.type; } 
	protected void setType (final String type) { this.type = type; return; } 

	protected Class<? extends Type> classType; 
	public Class<? extends Type> getClassType () { return this.classType; } 
	protected void setClassType (final Class<? extends Type> classType) { this.classType = classType; return; } 

	public
	JSONObject newObject ()
		throws JSONException
	{
		final JSONObject json = new JSONObject (); 
		json.put (JsonConstant.TYPE, getType ()); 
		return json; 
	}
	
	public
	Type newInstance ()
		throws Exception
	{
		return getClassType ().newInstance ();
	}

	protected JsonCodecFactory factory; 
	
	public 
	void setFactory (final JsonCodecFactory factory)
	{
		this.factory = factory; 
		return; 
	}

	protected
	JsonCodecFactory getFactory ()
	{
		return this.factory; 
	}

	@SuppressWarnings ("unchecked")
	protected
	Map<String, String> decodeProperties (final JSONObject obj)
		throws JSONException
	{
		final Map<String, String> props= new HashMap<String, String> (); 
		final Iterator<String> keys = obj.keys (); 
		while (keys.hasNext ()) { 
			final String key = keys.next (); 
			props.put (key, (String) obj.get (key)); 
		}
		return props; 
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	
	@SuppressWarnings ("unchecked")
	public <T>
	List<T> decodeList (final JSONArray array)
		throws Exception
	{
		final int len = array.length (); 
		final List<T>list = new ArrayList<T>(len);
		for (int i = 0; i < len; i ++) { 
			final JSONObject elem = array.getJSONObject (i);
			final T obj = (T) getFactory ().decode (elem); 
			list.add (obj); 
		}
		return list; 
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	
	public
	JSONArray encodeList (final List<?>list)
		throws Exception
	{
		final JSONArray array = new JSONArray (); 
		final JsonCodecFactory factory = getFactory (); 
		for (final Object obj : list) {
			final JSONObject json = factory.encode (obj); 
			array.put (json); 
		} 
		return array; 
	}
	
	/**
	 * 
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	
	public
	JSONArray encodeArray (final Object [] objs) 
		throws Exception
	{
		final JSONArray array = new JSONArray (); 
		final JsonCodecFactory factory = getFactory (); 
		for (final Object obj : objs) {
			final JSONObject json = factory.encode (obj); 
			array.put (json); 
		} 
		return array; 
	}

	/**
	 * 
	 * @param things
	 * @return
	 */
	
//	public static
//	long [] toLongIdArray (final Collection<? extends LongIdThing> things)
//	{
//		final long [] ids = new long [things.size ()];
//		int cnt = 0;
//		for (final LongIdThing thing : things) {
//			ids [cnt++] = thing.getId ();
//		}
//		return ids;
//	}
	
	/**
	 * 
	 * @param things
	 * @return
	 */
	
//	public static
//	int [] toIntegerIdArray (final Collection <? extends IntegerIdThing> things)
//	{
//		final int [] ids = new int [things.size ()];
//		int cnt = 0;
//		for (final IntegerIdThing thing : things) {
//			ids [cnt++] = thing.getId ();
//		}
//		return ids;
//	}

	/**
	 * 
	 * @param vals
	 * @return
	 */
	
	protected
	JSONArray encodeIntegers (final List<Integer> vals)
		throws JSONException
	{
		final JSONArray json = new JSONArray (); 
		for (final Integer val : vals) { 
			json.put (val); 
		}
		return json; 		
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	
	protected
	List<Integer> decodeIntegers (final JSONArray array)
		throws JSONException
	{
		final List<Integer> list = new ArrayList<Integer> (); 
		final int len = array.length (); 
		for (int i = 0; i < len; i ++) { 
			final int val = array.getInt (i); 
			list.add (val); 
		}
		return list; 
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	
	protected
	List<Long> decodeLongs (final JSONArray array)
		throws JSONException
	{
		final List<Long> list = new ArrayList<Long> (); 
		final int len = array.length (); 
		for (int i = 0; i < len; i ++) { 
			final long val = array.getLong (i); 
			list.add (val); 
		}
		return list; 
	}
	
	/**
	 * 
	 * @param vals
	 * @return
	 */
	
	protected 
	JSONArray encodeLongs (final List<Long> vals)
	{
		final JSONArray json = new JSONArray (); 
		for (final Long val : vals) { 
			json.put (val); 
		}
		return json; 
	}
	
	protected
	JSONArray encodeStringList (final List<String> strings)
	{
		final JSONArray json = new JSONArray (); 
		for (final String string : strings) { 
			json.put (string); 
		}
		return json; 	
	}
	
	protected
	List<String> decodeStringList (final JSONArray array)
		throws JSONException
	{
		final List<String> strings = new ArrayList<String> ();
		final int len = array.length (); 
		for (int i = 0; i < len; i ++) { 
			final String val = array.getString (i); 
			strings.add (val); 
		}
		return strings;
	}
}

// EOF