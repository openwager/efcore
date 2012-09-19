package com.weaselworks.codec.xio;

import java.io.*;

import com.weaselworks.io.*;

/**
 * 
 * @author crawford
 *
 */

public interface XIOCodec<Type>
{
	public
	int getObjectId (); 
	
	public 
	Class<Type> getType (); 

	public
	void write (Type type, XIOOutputStream xos)
		throws IOException; 
	
	public
	void writeBody (Type type, XIOOutputStream xos)
		throws IOException; 
	
	public
	Type read (XIOInputStream xis)
		throws IOException; 
	
	public
	Type readBody (XIOInputStream xis)
		throws IOException; 
}

// EOF