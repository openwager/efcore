package com.weaselworks.svc;

import javax.persistence.EntityManager;

/**
 * 
 * @author crawford
 *
 */

public interface EntityManagerManager 
{
	/**
	 * 
	 * @param em
	 */
	
	public
	void setEntityManager (final EntityManager em); 
	
	/**
	 * 
	 * @return
	 */
	
	public
	EntityManager getEntityManager (); 
}

// EOF