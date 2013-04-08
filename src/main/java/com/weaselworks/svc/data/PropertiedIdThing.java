package com.weaselworks.svc.data;

import java.util.*;

import javax.persistence.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
@MappedSuperclass
public class PropertiedIdThing
	extends LongIdThing
		implements Propertied
{
	public
	PropertiedIdThing ()
	{
		return; 
	}
	
	@Embedded
	protected EmbeddableProperties props; 
	
	protected 
	void ensurePropertiesNotNull ()
	{
		if (props == null) { 
			props = new EmbeddableProperties (); 
		}
		return; 
	}
	
    public Map<String, String> getProperties ()
    {
    	ensurePropertiesNotNull ();
    	return props.getProperties (); 
    }

    public String getProperty (String key)
    {
    	ensurePropertiesNotNull ();
	    return props.getProperty (key); 
    }

    public boolean hasProperty (String key)
    {
    	ensurePropertiesNotNull ();
	    return props.hasProperty (key);  
    }

    public void removeProperty (String key)
    {
    	ensurePropertiesNotNull ();
		props.removeProperty (key);
		return;  
    }

    public void setProperty (String key, String value)
    {
    	ensurePropertiesNotNull ();
	    props.setProperty (key, value); 
	    return; 
    }

    public void setProperties (final Map<String, String> properties) 
    {
    	ensurePropertiesNotNull ();
    	props.setProperties (properties); 
    	return; 
    }
    
    public 
    void mergeProperties (final Map<String, String> properties)
    {
       	ensurePropertiesNotNull ();
       	for (final String key : properties.keySet ()) { 
       		props.setProperty (key, properties.get (key));  
       	}
    	return;  
    }
    
	/**
	 * Convenience method for setting a number of properties
	 * 
	 * @param props
	 */
	
	public 
	void setProperties (final String [][] props)
	{
		for (final String [] prop : props) { 
			setProperty (prop [0], prop [1]); 
		}
		return;
	}

	@Override
	protected
	void paramString (final StringBuffer buf)
	{
		super.paramString (buf); 
    	ensurePropertiesNotNull ();
    	buf.append (",props="); 
		buf.append (props); 
		return; 
	}
	
	@Override
    protected 
    void buildHash (HashBuilder hash)
    {
		super.buildHash (hash);
    	ensurePropertiesNotNull ();
		hash.add (props); 
		return; 
	}
		
    @Override
    public boolean equals (final Object obj)
    {
	    if (this == obj) {
		    return true;
	    }
	    if (!(obj instanceof PropertiedIdThing)) {
		    return false;
	    }
	    final PropertiedIdThing other = (PropertiedIdThing) obj;
	    return
	    	super.equals (obj)
	        && Util.isEqual (props, other.props)
	    ;
    }
}

// EOF

