package com.weaselworks.svc.data;

import java.io.*;
import java.util.*;

import org.apache.log4j.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings ("serial")
abstract public class Thing
	implements Serializable
{	
	private static final Logger logger = Logger.getLogger (Thing.class); 
	
	public
	Thing ()
	{
		return; 
	}
	
	@Override
	public
	String toString ()
	{
		final StringBuffer buf = new StringBuffer ();
		buf.append (getClass ().getName ()); 
		buf.append ('['); 
		paramString (buf);
		buf.append (']'); 
		return buf.toString (); 
	}
	
	abstract protected
	void paramString (StringBuffer buf); 

	@Override
	public
	int hashCode ()
	{
		final HashBuilder hash =  new HashBuilder (); 
		buildHash (hash);  
		return hash.getHashCode (); 
	}
	
	abstract protected
	void buildHash (HashBuilder hash); 

    protected static final Map<Class<?>, Object> warned = new HashMap<Class<?>, Object> (); 
    
    public 
    boolean equals (final Object obj)
    {
    	if (! warned.containsKey (obj.getClass ())) { 
    		logger.info ("Missing equals() for " + obj.getClass ().getName ()); 
    		warned.put (obj.getClass (), ""); 
    	}
    	try {
	        return Util.fieldEquals (this, obj);
        } catch (final Exception e) {
        	logger.info (e.getMessage (), e); 
        	return false; 
        } 
        
        // NOT REACHED
    }
}

// EOF
