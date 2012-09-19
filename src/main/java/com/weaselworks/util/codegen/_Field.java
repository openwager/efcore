package com.weaselworks.util.codegen;

import java.io.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class _Field
	implements Encodeable 
{
	public 
	_Field ()
	{
		return; 
	}

	protected String type;
	public String getType () { return this.type; }
	public void setType (final String type) { this.type = type; return; }

	protected String name;
	public String getName () { return this.name; }
	public void setName (final String name) { this.name = name; return; }

	protected Visibility visibility;
	public Visibility getVisibility () { return this.visibility; }
	public void setVisibility (final Visibility visibility) { this.visibility = visibility; return; }

	protected boolean isFinal;
	public boolean getIsFinal () { return this.isFinal; }
	public void setIsFinal (final boolean isFinal) { this.isFinal = isFinal; return; }

	protected boolean isStatic;
	public boolean getIsStatic () { return this.isStatic; }
	public void setIsStatic (final boolean isStatic) { this.isStatic = isStatic; return; }

	protected String value;
	public String getValue () { return this.value; }
	public void setValue (final String value) { this.value = value; return; }
	
	protected
	void validate ()
	{
		if (StringUtil.isEmpty (name)) { 
			throw new IllegalStateException ("Name is required."); 
		}
		if (StringUtil.isEmpty (type)) { 
			throw new IllegalStateException ("Type is required.");
		}
		return; 
	}
	
	public void encode (EncodingContext ec)
		throws IOException 
	{
		validate (); 
		if (visibility != Visibility.PACKAGE){ 
			ec.write (visibility.getValue () + " "); 
		}
		if (getIsStatic ()) { 
			ec.write ("static "); 
		}
		if (getIsFinal ()) { 
			ec.write ("final "); 
		}
		ec.write (getType () + " "); 
		ec.write (getName ()); 
		if (getValue () != null) {
			ec.write (" = " + getValue ()); 
		}
		ec.writeln (";");
		return; 
		
	}

}

// EOf