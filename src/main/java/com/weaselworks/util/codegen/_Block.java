package com.weaselworks.util.codegen;

import java.io.*;
import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class _Block
	implements Encodeable
{
	public
	_Block ()
	{
		return; 
	}
	
	public 
	String getPrefix ()
	{
		return ""; 
	}
	
	public 
	String getPostfix ()
	{
		return ""; 
	}
	
	protected List<Encodeable> encodeables = new ArrayList<Encodeable> ();
	public List<Encodeable> getEncodeables () { return this.encodeables; }
	public void setEncodeables (final List<Encodeable> encodeables) { this.encodeables = encodeables; return; }
	public void addEncodeable (final Encodeable encodeable) { this.encodeables.add (encodeable); return; } 

	public
	void addCode (final String code)
	{
		addEncodeable (new _Code (code)); 
	}
	
	public void encode (EncodingContext ec)
		throws IOException 
	{
		ec.write (getPrefix ()); 
		ec.writeln ("{"); 
		ec.push (); 
		
		for (final Encodeable encodeable : encodeables) {
			encodeable.encode (ec);
		}

		ec.pop (); 
		ec.write ("}"); 
		ec.write (getPostfix ());
		ec.writeln (); 
		return; 
	}
}

// EOF