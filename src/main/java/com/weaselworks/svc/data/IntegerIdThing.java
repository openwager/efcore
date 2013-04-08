package com.weaselworks.svc.data;

import javax.persistence.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
@MappedSuperclass
abstract public class IntegerIdThing
	extends Thing
		implements Identifiable<Integer>
{
	public
	IntegerIdThing ()
	{
		return; 
	}
	
	public
	IntegerIdThing (final Integer id)
	{
		setId (id); 
		return; 
	}
	
	@Id
	@GeneratedValue
	@Column (name="ID")
	protected Integer id; 
	public Integer getId () { return this.id; } 
	public void setId (final Integer id) { this.id = id; return; } 
	
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
		if (! (obj instanceof IntegerIdThing)) { 
			return false; 
		}
		final IntegerIdThing other = (IntegerIdThing) obj; 
		return Util.isEqual (this.id, other.id); 
	}
}

// EOF
