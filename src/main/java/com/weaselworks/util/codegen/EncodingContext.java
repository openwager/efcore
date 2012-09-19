package com.weaselworks.util.codegen;

import java.io.*;

import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public class EncodingContext 
{
	public
	EncodingContext ()
	{
		this (new OutputStreamWriter (System.err)); 
		return; 
	}
	
	public
	EncodingContext (final Writer out)
	{
		final IndentationPolicy ip = new StringIndentationPolicy (); 
		final IndentingWriter iw = new IndentingWriter (out, ip);
		setOutput (iw); 
		return; 
	}
	
	protected IndentingWriter out; 
	public IndentingWriter getOutput () { return this.out; } 
	public void setOutput (final IndentingWriter out) { this.out = out; return; } 
	
	public 
	void write (final String s)
		throws IOException
	{
		getOutput ().write (s); 
		return; 
	}
	
	public 
	void writeln (final String s)
		throws IOException
	{
		write (s); 
		write ("\n");  
		return; 
	}
	
	public
	void writeln ()
		throws IOException
	{
		write ("\n"); 
		return; 
	}
	
	public
	void push ()
	{
		out.push (); 
		return; 
	}

	public
	void pop ()
	{
		out.pop ();  
		return; 
	}
}

// EOF