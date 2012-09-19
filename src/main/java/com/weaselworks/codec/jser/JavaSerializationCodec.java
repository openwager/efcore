package com.weaselworks.codec.jser;

import java.io.*;

import com.weaselworks.codec.*;

/**
 * 
 * @author crawford
 *
 */

public class JavaSerializationCodec
	implements Codec <Object>
{
	public
	JavaSerializationCodec ()
	{
		return; 
	}
	
    public
    Object decode (final InputStream is)
        throws IOException
    {
		try { 
			final ObjectInputStream ois = new ObjectInputStream (is);
			return ois.readObject ();
		}
		catch (final ClassNotFoundException cnf_e) { 
			throw new IOException (cnf_e.getMessage (), cnf_e); 
		}
		
		// NOT REACHED
    }

    public void encode (final Object type, final OutputStream os)
        throws IOException
    {
		if (! (type instanceof Serializable)) { 
			throw new NotSerializableException ("Can't serialize: " + type.getClass().getName ());  
		}
//		final Serializable ser = (Serializable) type; 
		final ObjectOutputStream oos = new ObjectOutputStream (os); 
		oos.writeObject (type); 
		oos.flush (); 
		return; 
    }
}

// EOF
