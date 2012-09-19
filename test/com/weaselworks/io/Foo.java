package com.weaselworks.io;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class Foo
{
	public
	Foo ()
	{
		return; 
	}
	
	public
	Foo (final boolean a, final short b, final int c, final long d, final float e, final double f, final char g,  
		final String h, final byte i, final Boolean j, final Short k, final Integer l, final Long m, final Float n, final Double o,
		final Character p, final Byte q)
	{
		this.myBoolean  = a; 
		this.myShort = b; 
		this.myInt = c; 
		this.myLong = d; 
		this.myFloat = e; 
		this.myDouble = f; 
		this.myChar = g; 
		this.myString = h; 
		this.myByte = i; 
		this.myBoolean2 = j; 
		this.myShort2 = k; 
		this.myInteger2 = l; 
		this.myLong2 = m; 
		this.myFloat2 = n; 
		this.myDouble2 = o;
		this.myCharacter = p; 
		this.myByte2 = q; 
		return; 
	}	
	
	protected boolean myBoolean; 
	public boolean getMyBoolean () { return this.myBoolean; } 
	public void setMyBoolean (final boolean myBoolean) { this.myBoolean = myBoolean; return; } 
	
	protected short myShort; 
	public short getMyShort () { return this.myShort; } 
	public void setMyShort (final short myShort) { this.myShort = myShort; return; } 
	
	protected int myInt; 
	public int getMyInt () { return this.myInt; } 
	public void setMyInt (final int myInt) { this.myInt = myInt; return; }
	
	protected long myLong; 
	public long getMyLong () { return this.myLong; } 
	public void setMyLong (final long myLong) { this.myLong = myLong; return; } 
	
	protected float myFloat; 
	public float getMyFloat () { return this.myFloat; } 
	public void setMyFloat (final float myFloat) { this.myFloat = myFloat; return; } 
	
	protected double myDouble;
	public double getMyDouble () { return this.myDouble; } 
	public void setMyDouble (final double myDouble) { this.myDouble = myDouble; return; } 
	
	protected char myChar; 
	public char getMyChar () { return this.myChar; } 
	public void setMyChar (final char myChar) { this.myChar = myChar; return; } 
	
	protected String myString; 
	public String getMyString () { return this.myString; } 
	public void setMyString (final String myString) { this.myString = myString; return; } 
	
	protected byte myByte; 
	public byte getMyByte () { return this.myByte; } 
	public void setMyByte (final byte myByte) { this.myByte = myByte; return; } 
	
	protected Boolean myBoolean2;
	public Boolean getMyBoolean2 () { return this.myBoolean2; } 
	public void setMyBoolean2 (final Boolean myBoolean2) { this.myBoolean2 = myBoolean2; return; } 
	
	protected Short myShort2; 
	public Short getMyShort2 () { return this.myShort2; } 
	public void setMyShort2 (final Short myShort2) { this.myShort2 = myShort2; return; } 
	
	protected Integer myInteger2; 
	public Integer getMyInteger2 () { return this.myInteger2; } 
	public void setMyInteger2 (final Integer myInteger2) { this.myInteger2 = myInteger2; return; } 
	
	protected Long myLong2; 
	public Long getMyLong2 () { return this.myLong2; } 
	public void setMyLong2 (final Long myLong2) { this.myLong2 = myLong2; return; } 
	
	protected Float myFloat2; 
	public Float getMyFloat2 () { return this.myFloat2; } 
	public void setMyFloat2 (final Float myFloat2) { this.myFloat2 = myFloat2; return; } 
	
	protected Double myDouble2; 
	public Double getMyDouble2 () { return this.myDouble2; } 
	public void setMyDouble2 (final Double myDouble2) { this.myDouble2 = myDouble2; return; } 
	
	protected Character myCharacter; 
	public Character getMyCharacter () { return this.myCharacter; } 
	public void setMyCharacter (final Character myCharacter) { this.myCharacter = myCharacter; return; } 

	protected Byte myByte2;
	public Byte getMyByte2 () { return this.myByte2; } 
	public void setMyByte2 (final Byte myByte2) { this.myByte2 = myByte2; return; } 
	
	public
	String toString ()
	{
		return StringUtil.toString (this); 
	}
	
	public
	boolean equals (final Object obj)
	{
		try { 
			return Util.fieldEquals (this, obj);
		}
		catch (final Exception e) { 
			return false; 
		}
	}
}

// EOF