package com.weaselworks.util.codegen;

import java.io.*;
import java.util.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class _Container
	implements Encodeable
{
	protected
	_Container ()
	{
		return; 
	}
	
	protected String pkg;
	public String getPackage () { return this.pkg; }
	public void setPackage (final String pkg) { this.pkg = pkg; return; }

	protected String name;
	public String getName () { return this.name; }
	public void setName (final String name) { this.name = name; return; }

	protected boolean isAbstract;
	public boolean getIsAbstract () { return this.isAbstract; }
	public void setIsAbstract (final boolean isAbstract) { this.isAbstract = isAbstract; return; }

	protected boolean isStatic;
	public boolean getIsStatic () { return this.isStatic; }
	public void setIsStatic (final boolean isStatic) { this.isStatic = isStatic; return; }

	protected boolean isFinal;
	public boolean getIsFinal () { return this.isFinal; }
	public void setIsFinal (final boolean isFinal) { this.isFinal = isFinal; return; }

	protected boolean isInner;
	public boolean getIsInner () { return this.isInner; }
	public void setIsInner (final boolean isInner) { this.isInner = isInner; return; }

	protected Visibility visibility = Visibility.PACKAGE;
	public Visibility getVisibility () { return this.visibility; }
	public void setVisibility (final Visibility visibility) { this.visibility = visibility; return; }

	protected Set<String> imports = new HashSet<String> ();
	public Set<String> getImports () { return this.imports; }
	public void setImports (final Set<String> imports) { this.imports = imports; return; }
	public void addImport (final String i) { this.imports.add (i); return; } 
	
	protected List<Encodeable> encodeables = new ArrayList<Encodeable> ();
	public List<Encodeable> getEncodeables () { return this.encodeables; }
	public void setEncodeables (final List<Encodeable> encodeables) { this.encodeables = encodeables; return; }
	public void addEncodeable (final Encodeable encodeable) { this.encodeables.add (encodeable); return; } 
	
	abstract protected
	String getType (); 
	
	abstract protected
	void validate ();
	
	public
	void encode (EncodingContext ec)
		throws IOException
	{
		validate (); 
		if (! getIsInner ()) { 
			ec.writeln ("package " + getPackage () + ";"); 
			ec.writeln (); 

			if (imports.size () > 0) { 
				for (final String i : imports) { 
					ec.writeln ("import " + i + ";"); 
				}
				ec.writeln (); 
			}
		}
		
		ec.write (getVisibility ().getValue () + " "); 
		if (getIsAbstract ()) { 
			ec.writeln ("abstract "); 
		}
		if (getIsFinal ()) { 
			ec.writeln ("final "); 
		}
		if (getIsStatic ()) { 
			ec.writeln ("static "); 
		}
		ec.write (getType () + " "); 
		ec.write (getName ()); 
		ec.writeln (" {"); 
		ec.push (); 
		
		for (final Encodeable encodeable : encodeables) {
			encodeable.encode (ec);
			ec.writeln (); 
		}
		
		ec.pop (); 
		ec.writeln ("}"); 
		return; 
	}	
}

// EOF