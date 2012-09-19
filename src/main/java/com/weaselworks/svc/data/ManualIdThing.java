package com.weaselworks.svc.data;

import javax.persistence.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@MappedSuperclass
abstract public class ManualIdThing
	extends Thing
		implements Identifiable<Long>
{
	public
	ManualIdThing ()
	{
		return; 
	}
	
	public
	ManualIdThing (final Long id)
	{
		setId (id); 
		return; 
	}
	
	@Id
	@Column (name="ID")
	protected Long id; 
	
	public Long getId () { return this.id; } 
	public void setId (final Long id) { this.id = id; return; } 
	
	@Override
	protected
	void paramString (final StringBuffer buf)
	{
		buf.append ("id="); 
		buf.append (id);
		return; 
	}

	@Override
    protected 
    void buildHash (HashBuilder hash)
    {
		hash.add (id); 
		return; 
    }
}

// EOF
