package com.weaselworks.util.codegen;

import java.io.*;
import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public class _Encodeables
	implements Encodeable
{
	public
	_Encodeables ()
	{
		return; 
	}
	
	protected List<Encodeable> encodeables = new ArrayList<Encodeable> ();
	public List<Encodeable> getEncodeables () { return this.encodeables; }
	public void setEncodeables (final List<Encodeable> encodeables) { this.encodeables = encodeables; return; }
	public void addEncodeable (final Encodeable encodeable) { this.encodeables.add (encodeable); return; }
	
	@Override
	public void encode (EncodingContext ec)
		throws IOException 
	{
		for (final Encodeable encodeable : encodeables) { 
			encodeable.encode (ec);  
		}
		return; 
	} 
}

// EOF