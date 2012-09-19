package com.weaselworks.util;

public class Foo2 {
	public Foo2 () 
	{ 
		return; 
	} 
	
	public Foo2 (final int weight, final String color, final Foo2 subfoo) 
	{
		setSubfoo (subfoo); 
		setWeight (weight); 
		setColor (color); 
		return; 
	}
	
	protected Foo2 subfoo; 
	public Foo2 getSubfoo () { return this.subfoo; } 
	public void setSubfoo (final Foo2 subfoo) { this.subfoo = subfoo; return; } 
	
	protected int weight; 
	public int getWeight () { return this.weight; } 
	public void setWeight (final int weight) { this.weight = weight; return; }
	
	protected String color; 
	public String getColor () { return this.color; } 
	public void setColor (final String color) { this.color = color; return; } 
}
