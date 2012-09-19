package com.weaselworks.util;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class TestBean
{
	protected String name = "Freddy"; 
	public String getName () { return this.name; } 
	public void setName (final String name) { this.name = name; return; } 
	
	protected int weight = 123;  
	public int getWeight () { return this.weight; } 
	public void setWeight (final int weight) { this.weight = weight; return; } 

	protected Map<String, Object> properties; 
	public Map<String, Object> getProperties () { return this.properties; } 
	public void setProperties (final Map<String, Object> properties) { this.properties = properties; return; } 

	protected boolean happy = true; 
	public boolean getHappy () { return this.happy; } 
	public void setHappy (final boolean happy) { this.happy = happy; return; } 
	
	protected Color color = Color.RED; 
	public Color getColor () { return this.color; }
	public void setColor (final Color color) { this.color = color; return; } 
	
	protected long id = 123456L; 
	public long getId () { return this.id; } 
	public void setId (final long id) { this.id = id; return; } 

	public
	String toString ()
	{
		return StringUtil.toString (this); 
	}
}

// EOF