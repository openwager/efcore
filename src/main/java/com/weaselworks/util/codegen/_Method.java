package com.weaselworks.util.codegen;

import java.io.*;
import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class _Method
	implements Encodeable
{
	public
	_Method ()
	{
		return; 
	}

	protected Visibility visibility = Visibility.PACKAGE;
	public Visibility getVisibility () { return this.visibility; }
	public void setVisibility (final Visibility visibility) { this.visibility = visibility; return; }

	protected String type;
	public String getType () { return this.type; }
	public void setType (final String type) { this.type = type; return; }

	protected String name;
	public String getName () { return this.name; }
	public void setName (final String name) { this.name = name; return; }

	protected boolean isConstructor;
	public boolean getIsConstructor () { return this.isConstructor; }
	public void setIsConstructor (final boolean isConstructor) { this.isConstructor = isConstructor; return; }

	protected boolean isAbstract;
	public boolean getIsAbstract () { return this.isAbstract; }
	public void setIsAbstract (final boolean isAbstract) { this.isAbstract = isAbstract; return; }
	
	protected boolean isFinal;
	public boolean getIsFinal () { return this.isFinal; }
	public void setIsFinal (final boolean isFinal) { this.isFinal = isFinal; return; }

	protected boolean isStatic;
	public boolean getIsStatic () { return this.isStatic; }
	public void setIsStatic (final boolean isStatic) { this.isStatic = isStatic; return; }

	protected _Block block = new _Block ();
	public _Block getBlock () { return this.block; }
	public void setBlock (final _Block block) { this.block = block; return; }

	protected List<_Parameter> parameters = new ArrayList<_Parameter> ();
	public List<_Parameter> getParameters () { return this.parameters; }
	public void setParameters (final List<_Parameter> parameters) { this.parameters = parameters; return; }
	public void addParameter (final _Parameter p) { this.parameters.add (p); return; } 
	
	protected
	void validate ()
	{
		// UNIMPLEMENTED
		return; 
	}
	
	public void encode (EncodingContext ec)
		throws IOException 
	{
		validate (); 
		ec.write (getVisibility ().getValue () + " "); 
		if (getIsAbstract ()) { 
			ec.write ("abstract "); 
		}
		if (getIsFinal ()) { 
			ec.write ("final "); 
		}
		if (getIsStatic ()) { 
			ec.write ("static "); 
		}
		ec.write (getType () + " "); 
		if (! getIsConstructor ()) { 
			ec.write (getName ()); 
		}
		ec.write ("("); 
		boolean first = true; 
		for (final _Parameter p : getParameters ()) { 
			if (first) { 
				first = false; 
			} else { 
				ec.write (", "); 
			}
			p.encode (ec); 
		}
		
		if (getIsAbstract ()) { 
			ec.writeln (");"); 					
		} else { 
			ec.writeln (")"); 					
			getBlock ().encode (ec); 
		}
		return; 
	}
}

// EOF