package com.weaselworks.io;

import java.io.*;

import org.apache.log4j.*;

import com.weaselworks.codec.xio.*;

/**
 * 
 * @author crawford
 *
 */

public class FooCodec
	extends XIOCodecSupport<Foo>
		implements XIOCodec<Foo>
{
	private static final Logger logger = Logger.getLogger (FooCodec.class); 
	
	public
	FooCodec ()
	{
		super (0xbeef, Foo.class); 
		return; 
	}

    public
    Foo readBody (final XIOInputStream xis)
        throws IOException
    {
    	final Foo foo = new Foo ();
    	
    	final boolean b = xis.readBoolean ();
    	logRead ("boolean", b); 
    	foo.setMyBoolean (b);
    	
    	final short s = xis.rawShort ();
    	logRead ("short", s); 
    	foo.setMyShort (s);
    	
    	final int i = xis.rawInteger (); 
    	logRead ("int", i); 
    	foo.setMyInt (i);
    	
    	final long l = xis.rawLong ();
    	logRead ("long", l); 
    	foo.setMyLong (l);
    	
    	final float f = xis.rawFloat (); 
    	logRead ("float", f); 
    	foo.setMyFloat (f);    	
    	
    	final double d = xis.rawDouble ();  
    	logRead ("double", d); 
    	foo.setMyDouble (d);
    	
    	final char c = xis.rawChar (); 
    	logRead ("char", c); 
    	foo.setMyChar (c);
    	
    	final byte by = xis.rawByte (); 
    	logRead ("byte", by); 
    	foo.setMyByte (by);
    	
    	final String st = xis.readObject (String.class);  
    	logRead ("String", st); 
    	foo.setMyString (st);
    	
    	final Boolean b2 = xis.readObject (Boolean.class);
    	logRead ("Boolean", b2); 
    	foo.setMyBoolean2 (b2); 
    	
    	final Short s2 = xis.readObject (Short.class); 
    	logRead ("Short", s2); 
    	foo.setMyShort2 (s2); 
    	
    	final Integer i2 = xis.readObject (Integer.class); 
    	logRead ("Integer", i2); 
    	foo.setMyInteger2 (i2); 
    	
    	final Long l2 = xis.readObject (Long.class); 
    	logRead ("Long", l2); 
    	foo.setMyLong2 (l2); 
    	
    	final Float f2 = xis.readObject (Float.class);  
    	logRead ("Float", f2); 
    	foo.setMyFloat2 (f2); 
    	
    	final Double d2 = xis.readObject (Double.class); 
    	logRead ("Double", d2); 
    	foo.setMyDouble2 (d2); 
    	
    	final Character c2 = xis.readObject (Character.class);
    	logRead ("Character", c2); 
    	foo.setMyCharacter (c2); 
    	
    	final Byte by2 = xis.readObject (Byte.class); 
    	logRead ("Byte", by2); 
    	foo.setMyByte2 (by2); 
    	return foo; 
    }

    public
    void writeBody (final Foo foo, final XIOOutputStream xos)
        throws IOException
    {
    	logWrite ("boolean", foo.getMyBoolean ()); 
    	xos.writeBoolean (foo.getMyBoolean ()); 

    	logWrite ("short", foo.getMyShort ()); 
		xos.rawShort (foo.getMyShort ()); 
		
		logWrite ("int", foo.getMyInt ()); 
		xos.rawInteger (foo.getMyInt ()); 
		
		logWrite ("long", foo.getMyLong ()); 
		xos.rawLong (foo.getMyLong ()); 
		
		logWrite ("float", foo.getMyFloat ()); 
		xos.rawFloat (foo.getMyFloat ()); 
		
		logWrite ("double", foo.getMyDouble ()); 
		xos.rawDouble (foo.getMyDouble ()); 
		
		logWrite ("char", foo.getMyChar ()); 
		xos.rawChar (foo.getMyChar ()); 
		
		logWrite ("byte", foo.getMyByte ()); 
		xos.rawByte (foo.getMyByte ()); 
		
		logWrite ("String", foo.getMyString ()); 
		xos.writeObject (foo.getMyString (), String.class); 
		
		logWrite ("Boolean", foo.getMyBoolean2 ()); 
		xos.writeObject (foo.getMyBoolean2 (), Boolean.class); 
		
		logWrite ("Short", foo.getMyShort2 ()); 
		xos.writeObject (foo.getMyShort2 (), Short.class); 
		
		logWrite ("Integer", foo.getMyInteger2 ()); 
		xos.writeObject (foo.getMyInteger2 (), Integer.class); 
		
		logWrite ("Long", foo.getMyLong2 ()); 
		xos.writeObject (foo.getMyLong2 (), Long.class); 
		
		logWrite ("Float", foo.getMyFloat2 ()); 
		xos.writeObject (foo.getMyFloat2 (), Float.class);
		
		logWrite ("Double", foo.getMyDouble2 ()); 
		xos.writeObject (foo.getMyDouble2 (), Double.class); 
		
		logWrite ("Character", foo.getMyCharacter ()); 
		xos.writeObject (foo.getMyCharacter (), Character.class); 
		
		logWrite ("Byte", foo.getMyByte2 ()); 
		xos.writeObject (foo.getMyByte2 (), Byte.class);     	
    	return; 
    }

    protected 
    void logWrite (final String type, final Object val)
    {
    	log ("WRITE", type, val); 
    	return;     	
    }
    
    protected static
    void logRead (final String type, final Object val)
    {
    	log ("READ", type, val); 
    	return; 
    }
    
    protected static
    void log (String op, final String type, final Object val)
    {
    	logger.info (op + " " + type + "(" + val + ")"); 
    	return; 
    }    
}

// EOF