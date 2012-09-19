package com.weaselworks.codec.json;

import org.json.*;

/**
 * 
 * @author crawford
 *
 */

public interface JsonCodecFactory
{
	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @return
	 */
	
	public <Type>
	JsonCodec<Type> getCodec (final Class<Type> type); 	

	/**
	 * 
	 * @param obj
	 * @return
	 */
	
	public
	JSONObject encode (final Object obj)
		throws Exception; 
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	
	public <Type>
	Type decode (final JSONObject obj)
		throws Exception; 
}

// EOF