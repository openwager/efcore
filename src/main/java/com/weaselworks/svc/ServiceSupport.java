package com.weaselworks.svc;

import javax.persistence.*;

import com.weaselworks.svc.data.*;

import java.util.*; 

/**
 * 
 * @author crawford
 *
 */

abstract public class ServiceSupport 
	implements Service
{
	protected
	ServiceSupport ()
	{
		return; 
	}

	protected
	ServiceSupport (final EntityManager em)
	{
		this.em = em; 
		return; 
	}
	
	public
	String getVersion ()
	{
		return getClass ().getPackage ().getImplementationVersion (); 
	}	

	protected EntityManager em; 
	public final EntityManager getEntityManager () { return this.em; } 
	
//	protected EntityManagerManager emm; 
//	protected void setEntityManagerManager (final EntityManagerManager emm) { this.emm = emm; } 
//	protected EntityManagerManager getEntityManagerManager () { return this.emm; } 
//	
//	public
//	EntityManager getEntityManager ()
//	{
//		return emm.getEntityManager (); 
//	}
	
    /**
     * A convenience method that's used to coerce a single result to the appropriate
     * type and also absorb any {@linkplain NoResultExceptions} and turn them into
     * null results. 
     * 
     * @param <Type>
     * @param type
     * @param query
     * @return
     */
    
    @SuppressWarnings("unchecked")
    public <Type>
    Type getSingleResult (final Class<Type> type, final Query query)
    {
    	try { 
    		return (Type) query.getSingleResult (); 
    	}
    	catch (final NoResultException nr_e) { 
    		return null; 
    	}
    	
    	// NOT REACHED
    }
    
    /**
     * 
     * @param <Type>
     * @param type
     * @param fres
     * @return
     */
    
    public <Type> 
    Type getSingleResult (final Class<Type> type, final FetchResults<Type> fres)
    {
    	final List<Type> results = fres.getResults ();
    	switch (results.size ()) { 
    		case 0: 
    			return null; 
    		case 1:
    			return results.get (0); 
    			
    		default:
    			throw new IllegalArgumentException ("FetchResults contains > 1 elements.");     				
    	}

    	// NOT REACHED 
    }
    
    /**
     * 
     * @param type
     * @param key
     * @return
     */
    
    public
   	boolean remove (final Class<?> type, final Object key)
    {
    	assert type != null; 
    	assert key != null; 
    	
    	final EntityManager em = getEntityManager (); 
    	final Object obj = em.find (type, key); 
    	if (obj != null) { 
    		em.remove (obj); 
    		em.flush ();
    		return true; 
    	}
    	return false; 
    }

    /**
     * 
     * @param key
     */
    
    public
    void update (final Identifiable<?> obj)
    {
    	assert obj != null; 
    	assert obj.getId () != null; 
    	
    	final EntityManager em = getEntityManager (); 
    	em.merge (obj);
    	return;
    }
}

// EOF