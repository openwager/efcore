package com.weaselworks.io;

/**
 * Contains the constants used by the XIO framework for encoding/decoding. 
 * 
 * @author crawford
 *
 */

public interface XIOConstant
{
	public byte NONE = 0x00; 
	public byte NULL = 0x01; 
	public interface BOOLEAN { 
		public byte FALSE = 0x02; 
		public byte TRUE = 0x03; 
	}
	public byte BYTE = 0x04; 
	public byte SHORT = 0x05; 
	public byte INTEGER = 0x06; 
	public byte LONG = 0x07; 
	public byte FLOAT = 0x08; 
	public byte DOUBLE = 0x09; 
	public byte CHAR = 0x0a; 
	public byte MARKER = 0x0b;
	public byte ARRAY = 0x0c; 

	public interface OBJECT
	{
		public byte INTERNAL = 0x20; 
		public int RESERVED = 0x00; 
		public int STRING = 0x01; 
		public int MAP = 0x02; 
		public int DATE = 0x03 ;
		public int LIST = 0x04;
		public int SET = 0x05;
		public int BOOLEAN = 0x06; 
		public int BYTE = 0x07; 
		public int SHORT = 0x08; 
		public int INTEGER = 0x09; 
		public int LONG = 0x0a; 
		public int FLOAT = 0x0b; 
		public int DOUBLE = 0x0c;
		public int CHARACTER = 0x0d; 
	}	
}

// EOF