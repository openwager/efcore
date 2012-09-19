package com.weaselworks.codec.xml;

import java.io.*;

import org.w3c.dom.*;

import com.weaselworks.codec.*;

/**
 * 
 * @author crawford
 *
 */

public interface XMLCodecFactory
{
	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @return
	 * @throws CodecNotFoundException
	 */
	
	public <Type>
	XMLCodec<Type> getCodec (final Class<?> type)
		throws CodecNotFoundException; 

	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @param is
	 * @return
	 * @throws Exception
	 */
	
	public <Type>
	Type decode (final Class<Type> type, final InputStream is)
		throws Exception;	

	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @param e
	 * @return
	 * @throws Exception
	 */
	
	public <Type>
	Type decode (final Class<Type> type, final Element e)
		throws Exception;	

	/**
	 * 
	 * @param <Type>
	 * @param type
	 * @param e
	 * @return
	 * @throws Exception
	 */

	public <Type>
	Type decode (final Element e)
		throws Exception;	

	
	/**
	 * 
	 * @param <Type>
	 * @param obj
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	
	public <Type>
	Element encode (final Type obj, final Document doc)
		throws Exception;
}

// EOF