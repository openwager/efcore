package com.weaselworks.util.codegen;

import java.util.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class _Class
	extends _Container
		implements Encodeable
{
	
	public
	_Class ()
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

	protected boolean isFinal;
	public boolean getIsFinal () { return this.isFinal; }
	public void setIsFinal (final boolean isFinal) { this.isFinal = isFinal; return; }

	protected boolean isInner;
	public boolean getIsInner () { return this.isInner; }
	public void setIsInner (final boolean isInner) { this.isInner = isInner; return; }

	protected Visibility visibility;
	public Visibility getVisibility () { return this.visibility; }
	public void setVisibility (final Visibility visibility) { this.visibility = visibility; return; }

	protected Set<String> imports = new HashSet<String> ();
	public Set<String> getImports () { return this.imports; }
	public void setImports (final Set<String> imports) { this.imports = imports; return; }
	public void addImport (final String i) { this.imports.add (i); return; } 
		
	protected
	String getType () 
	{
		return "class"; 
	}
	
	protected
	void validate ()
	{
		if (getIsInner ()) { 
			if (imports.size () > 0) { 
				throw new IllegalStateException ("Inner classes can't have imports."); 
			}
		} else { 
			if (StringUtil.isEmpty (getPackage ())) { 
				throw new IllegalStateException ("Package is required."); 
			}
		}
		if (StringUtil.isEmpty (name)) { 
			throw new IllegalStateException ("Name is required."); 
		}
		return; 
	}
}

// EOF