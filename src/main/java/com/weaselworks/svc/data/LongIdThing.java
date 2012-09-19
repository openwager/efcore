package com.weaselworks.svc.data;

import javax.persistence.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@MappedSuperclass
abstract public class LongIdThing
	extends Thing
		implements Identifiable<Long>
{
	public
	LongIdThing ()
	{
		return; 
	}
	
	public
	LongIdThing (final Long id)
	{
		setId (id); 
		return; 
	}
	
	@Id
	@GeneratedValue
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
	
	@Override
	public
	boolean equals (final Object obj)
	{
		if (! (obj instanceof LongIdThing)) { 
			return false; 
		}
		final LongIdThing other = (LongIdThing) obj; 
		return Util.isEqual (this.id, other.id); 
	}
}

// EOF
