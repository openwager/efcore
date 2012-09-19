package com.weaselworks.codec.xml;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

abstract public class XMLCodecSupport<Type>
	implements XMLCodec<Type>
{
	public
	XMLCodecSupport (final String nodeName, final Class<? extends Type> type)
	{
		setNodeName (nodeName); 
		setType (type); 
		return; 
	}

	public
	XMLCodecSupport (final Class<? extends Type> type)
	{
		setType (type); 
		return; 
	}

	protected String nodeName; 
	public String getNodeName () { return this.nodeName; } 
	public void setNodeName (final String nodeName) { this.nodeName = nodeName; return; } 
	
	protected Class<? extends Type> type; 
	public Class<? extends Type> getType () { return this.type; } 
	public void setType (final Class<? extends Type> type) { this.type = type; return; } 
	
	protected
	Element newElement (final Document doc)
	{
		final Element e = doc.createElement (getNodeName ()); 
		return e; 
	}
	
	protected
	Type newInstance ()
		throws Exception
	{
		return getType ().newInstance ();
	}
	
	protected XMLCodecFactory factory; 
	public void setFactory (final XMLCodecFactory factory) { this.factory = factory; return; } 
	public XMLCodecFactory getFactory () { return this.factory; } 

	public
	Type decode (String str)
		throws Exception 
	{
		final ByteArrayInputStream bais = new ByteArrayInputStream (str.getBytes ()); 
		return decode (bais); 
	}
	
	public
	Type decode (InputStream is)
		throws Exception 
	{
		final Document doc = XMLUtil.parse (is);
		final Element elem = doc.getDocumentElement (); 
		return decode (elem); 
	}
		
	public
	void encode (Type type, OutputStream os)
		throws Exception 
	{
		final DocumentBuilder builder = XMLUtil.getBuilder (); 
		final Document doc = builder.newDocument (); 
		encode (type, doc); 
		XMLUtil.dump (doc.getDocumentElement (), os); 
		return; 
	}
	
 	public static
	Element getLoneChild (final Element e, final String name, final boolean required)
 		throws IOException
	{
 		final List<Element> elems = getChildNodes (e, name); 
		if (elems.size () == 0) { 
			if (required) { 
				throw new IOException ("Element '" + e.getNodeName () + "' has no child '" + name + "'.");
			} else { 
				return null; 
			}
		} else if (elems.size () > 1) { 
			throw new IOException ("Element '" + e.getNodeName () + "' has multilple child '" + name + "' elements ("+ elems.size () + ".");  
		} else { 
			return elems.get (0); 
		}
		
		// NOT REACHED
	}
 	
 	protected static
 	List<Element> getChildNodes (final Element e)
 		throws IOException
 	{	
 		return getChildNodes (e, null); 
 	}
 	
 	protected static
 	List<Element> getChildNodes (final Element e, final String name)
 		throws IOException
 	{
 		final NodeList nl = e.getChildNodes ();
 		final int cnt = nl.getLength ();
 		final List<Element> elems = new ArrayList<Element> (); 
 		
 		for (int i = 0; i < cnt; i ++) { 
 			final Node n = nl.item (i); 
 			if (n.getNodeType () == Node.ELEMENT_NODE) {
// 				if (name == null || name.equals (n.getNodeName ())) {
 				if (name == null || matchNodeNames (n, name)) {
 					final Element e2 = (Element) n; 
 					elems.add (e2);
 				}
 			}
 		}
 		
 		return elems;
 	}
 	
	protected static
	void expect (final Element e, final String name)
		throws IOException
	{
//		String nodeName = e.getNodeName (); 
//		if (nodeName.contains (":")) { 
//			if (! name.contains (":")) { 
//				nodeName = nodeName.substring (nodeName.indexOf (':') + 1); 
//			}
//		}
//		if (! nodeName.equals (name)) {
		if (! matchNodeNames (e, name)) { 
			throw new IOException ("Expected '" + name + "'; found '" + e.getNodeName () + "'");
		}
		return; 
	}
	
	/**
	 * 
	 * @param e
	 * @param name
	 * @return
	 */
	
	protected static
	boolean matchNodeNames (final Node e, final String name)
	{
		final String nodeName = e.getNodeName ();
		final int index = nodeName.indexOf (':'); 
		
		if (index == -1) {
			return nodeName.equals (name);
		} else { 
			return nodeName.substring (index + 1).equals (name); 
		}
		// NOT REACHED
	}

	/**
	 * 
	 * @param <T>
	 * @param parent
	 * @param type
	 * @param name
	 * @param required
	 * @return
	 * @throws Exception
	 */
	
	protected <T>
	T decodeChild (final Element parent, final Class<T> type, final String name, final boolean required)
		throws Exception
	{
		final Element child = getLoneChild (parent, name, required);
		T obj = null; 
		
		if (child != null) { 
			final XMLCodec<T> codec = getFactory ().getCodec (type); 
			obj = codec.decode (child); 
		}

		return obj; 
	}

	/**
	 * 
	 * @param e
	 * @param name
	 * @param required
	 * @return
	 * @throws Exception
	 */
	
	protected
	String getTextChild (Element e, final String name, final boolean required)
		throws Exception
	{
		final Element child = getLoneChild (e, name, required); 
		if (child == null) { 
			return null; 
		} else {
			final String text = child.getTextContent ();
			return text; 
		}
	}
	
	protected
	String getAttribute (final Element e, final String attr, final boolean required)
		throws Exception
	{
		if (required) { 
			if (! e.hasAttribute (attr)) { 
				throw new Exception ("Missing attribute '" + attr  +"'."); 
			}
		}
		final String val = e.getAttribute (attr); 
		return val; 
	}
	
//	protected
//	Boolean getBooleanAttribute (final Element e, final String attr, final boolean required)
//		throws Exception
//	{
//		final String val = getAttribute (e, attr, required); 
//		if (val == null) { 
//			return null; 
//		} else {  
//			return Boolean.parseBoolean (val); 
//		}
//		// NOT REACHED	
//	}
	
	protected
	Integer getIntegerAttribute (final Element e, final String attr, final boolean required)
		throws Exception
	{
		final String val = getAttribute (e, attr, required); 
		if (val == null) { 
			return null; 
		} else {  
			return Integer.parseInt (val); 
		}
		// NOT REACHED
	}
	
	protected 
	String getAttributeOrChild (final Element e, final String name, final boolean required)
		throws Exception
	{
		String val = null; 
		if (e.hasAttribute (name)) { 
			val = e.getAttribute (name); 
		} else { 
			val = getTextChild (e, name, required); 
		}
		return val;
	}
	
	protected
	Element addTextChild (final Element parent, final String name, final String value, final Document doc)
	{
		final Element e = doc.createElement (name); 
		e.setTextContent (value);
		parent.appendChild (e); 
		return e; 
	}

	protected
	Boolean getBooleanAttribute (final Element e, final String attr, final boolean required)
		throws Exception
	{
		final String val = getAttribute (e, attr, required); 
		if (val == null) { 
			return null; 
		} else {  
			return Boolean.parseBoolean (val); 
		}
		// NOT REACHED	
	}

	protected <T>  
	List<T> decodeList (final Element e1, final String name)
		throws Exception
	{
		final List<T> list = new ArrayList<T> (); 
		final List<Element> elems = getChildNodes (e1, name); 
		for (final Element elem : elems) { 
			final T t = factory.decode (elem); 
			list.add (t); 
		}
		return list; 
	}
}

// EOF