package com.weaselworks.util.codegen;

import java.io.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class 	_Property
	implements Encodeable
{
	protected String type;
	public String getType () { return this.type; }
	public void setType (final String type) { this.type = type; return; }

	protected String name;
	public String getName () { return this.name; }
	public void setName (final String name) { this.name = name; return; }

	protected String value;
	public String getValue () { return this.value; }
	public void setValue (final String value) { this.value = value; return; }

	protected boolean isStatic;
	public boolean getIsStatic () { return this.isStatic; }
	public void setIsStatic (final boolean isStatic) { this.isStatic = isStatic; return; }

	@Override
	public void encode (EncodingContext ec)
		throws IOException 
	{
		final _Encodeables e = new _Encodeables (); 
		final String lower, upper; 
		
		if (StringUtil.isEmpty (getName ())) {
			lower = Character.toLowerCase (type.charAt (0)) + type.substring (1);
			upper = getType (); 
		} else { 
			lower = getName ();
			upper = Character.toUpperCase (lower.charAt (0)) + lower.substring (1); 
		}
		
		// add the field
		
		final _Field f = new _Field (); 
		f.setType (type); 
		f.setName (lower); 
		f.setVisibility (Visibility.PROTECTED); 
		f.setIsStatic (isStatic); 
		e.addEncodeable (f); 
		
		// add the getter
		
		final _Code g = new _Code (getGetter (isStatic, type, lower, upper)); 
//		final _Method g = new _Method (); 
//		g.setVisibility (Visibility.PUBLIC); 
//		g.setIsStatic (isStatic);
//		g.setType (type); 
//		g.setName ("get" + upper); 
		e.addEncodeable (g); 
		
		// add the setter

		final _Code s = new _Code (getSetter (isStatic, type, lower, upper)); 
//		final _Method s = new _Method (); 
//		s.setVisibility (Visibility.PUBLIC); 
//		s.setIsStatic (isStatic);
//		s.setType ("void");
//		s.setName("set" + upper); 
//		s.addParameter (new _Parameter (true, type, lower)); 
		e.addEncodeable (s); 
		
		e.encode (ec); 
		return; 
	}	
	
	public static
	String getGetter (final boolean isStatic, final String type, final String lower, final String upper)
	{
		return "protected " + (isStatic ? "static " : "") + type + " get" + upper + " () { return this." + lower + "; }"; 
	}

	public static
	String getSetter (final boolean isStatic, final String type, final String lower, final String upper)
	{
		return "protected " + (isStatic ? "static " : "") + "void " + "set" + upper  + " ( final " + type + " " + lower + ") { this." + lower + " = " + lower + "; return; }"; 
	}
}

// EOF