package com.weaselworks.codec;

/**
 * 
 * @author crawford
 *
 */

public class SimpleBean
{
	public SimpleBean () { return; }
	
	public SimpleBean (final String color, final int age)
	{
		this.color = color; 
		this.age = age; 
		return; 
	}
	
	protected String color; 
	public String getColor () { return this.color; } 
	public void setColor (final String color) { this.color = color; return; } 
	
	protected int age; 
	public int getAge () { return this.age; } 
	public void setAge (final int age) { this.age = age; return; }
}
