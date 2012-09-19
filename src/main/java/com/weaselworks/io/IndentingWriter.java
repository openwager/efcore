package com.weaselworks.io;

import java.io.*;

/**
 * 
 * @author crawford
 *
 */

public class IndentingWriter
	extends Writer
{
	public
	IndentingWriter (final Writer writer)
	{
		this (writer, new StringIndentationPolicy ());  
		return; 
	}
	
	public 
	IndentingWriter (final Writer writer, final IndentationPolicy policy)
	{
		setWriter (writer); 
		setPolicy (policy);  
		return; 
		
	}

	protected Writer writer;
	public Writer getWriter () { return this.writer; }
	public void setWriter (final Writer writer) { this.writer = writer; return; }

	protected IndentationPolicy policy;
	public IndentationPolicy getPolicy () { return this.policy; }
	public void setPolicy (final IndentationPolicy policy) { this.policy = policy; return; }
	
	protected int indent;
	public int getIndent () { return this.indent; }
	public void setIndent (final int indent) { this.indent = indent; return; }
	
	public void pop () { indent --; return; } 
	public void push () { indent ++; return; } 
	
	public 
	void close ()
		throws IOException 
	{
		writer.close (); 
		return; 
	}

	@Override
	public void flush ()
		throws IOException 
	{
		writer.flush ();
		return; 		
	}

	protected boolean atStart = true; 
	
	public 
	void write (char [] cbuf, int off, int len)
		throws IOException 
	{
		if (len > 0) { 
			for (int i = 0; i < len; i ++) { 
				final char c = cbuf [off + i]; 				
				if (atStart) { 
					writer.write (policy.getIndent (indent));
					atStart = false; 
				}
				writer.write (c);
				if (c == '\n') {
					atStart = true;
				}				
			}
		}
		
		return; 
	}
}

// EOF