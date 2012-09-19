package com.weaselworks.svc.data;

/**
 * 
 * @author crawford
 *
 */

public interface Identifiable<KeyType>
{
	/**
	 * 
	 * @return
	 */
	
	public
	KeyType getId ();
	
	/**
	 * 
	 * @param id
	 */
	
	public
	void setId (final KeyType id); 
}

// EOF