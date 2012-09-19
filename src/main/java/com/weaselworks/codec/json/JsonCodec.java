package com.weaselworks.codec.json;

import org.json.*;

/**
 * 
 * @author crawford
 *
 */

public interface JsonCodec<Type>
{
	/**
	 * 
	 * @return
	 */
	
	public 
	String getType (); 
	
	/**
	 * 
	 * @return
	 */
	
	public 
	Class<? extends Type> getClassType ();
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	
	public
	JSONObject encode (Type obj)
		throws Exception; 
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	
	public 
	Type decode (JSONObject obj)
		throws Exception; 
	
	/**
	 * 
	 * @param factory
	 */

	public
	void setFactory (JsonCodecFactory factory);	
}

// EOF