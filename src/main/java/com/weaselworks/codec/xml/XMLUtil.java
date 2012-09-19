package com.weaselworks.codec.xml;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * 
 * @author crawford
 *
 */

public class XMLUtil
{
    public static
    DocumentBuilder getBuilder ()
        throws ParserConfigurationException
    {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
        factory.setNamespaceAware (false);
        factory.setValidating (false);
        return factory.newDocumentBuilder ();
    }

    /**
     * Utility method for dumping a human-readable version of the specified element
     * to the output stream. 
     * 
     * @param el
     * @param os
     * @throws Exception
     */
	
	public static 
	void dump (final Element el, final OutputStream os)
		throws Exception
	{
        // Now dump it to the specified output stream

        final TransformerFactory xfactory = TransformerFactory.newInstance ();
        final Transformer transformer = xfactory.newTransformer ();
        transformer.setOutputProperty("omit-xml-declaration","yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty (OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        final Source source = new DOMSource (el);
        final Result result = new StreamResult (os);
        transformer.transform (source, result);
        return; 
	}
	
	/**
	 * 
	 * @param el
	 * @return
	 * @throws Exception
	 */
	
	public static
	String dump (final Element el)
		throws Exception
	{
		final ByteArrayOutputStream baos = new ByteArrayOutputStream (); 
		dump (el, baos); 
		baos.flush (); 
		baos.close (); 
		final String xml = baos.toString ("UTF-8"); 
		return xml;
	}
	
	public static 
	void main (final String [] args)
		throws Exception
	{
		final String a = "<a><b><c>sdfasdf</c><d>sdfsdf 砜濏磦</d></b><foo>• Monito</foo></a>";
		final Document d= parse (a); 
		System.out.println (dump (d.getDocumentElement ())); 
		return; 
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	
	public static
	Document parse (final String str)
		throws Exception
	{
//		System.err.println ("XMLUtil.parse 1============"); 
//		System.err.println (str); 
//		System.err.println ("XMLUtil.parse2 ============"); 
		
        final DocumentBuilder builder = getBuilder ();
        final StringReader reader = new StringReader (str);
        final InputSource is = new InputSource (reader);
        is.setEncoding ("UTF-8");
        final Document doc = builder.parse (is); 

//		System.err.println ("XMLUtil.parse3 ============"); 
//		System.err.println (dump (doc.getDocumentElement ())); 
//		System.err.println ("XMLUtil.parse4 ============"); 
        
        return doc; 
	}
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	
	public static
	Document parse (final InputStream is)
		throws Exception
	{
        final DocumentBuilder builder = getBuilder ();
        final Document  doc = builder.parse (is);
        return doc; 
	}

	/**
	 * 
	 * @author crawford
	 *
	 */
	
	public class NodeListIterator 
		implements Iterator<Node>, Iterable<Node> 
	{
	    private NodeList nodeList_;
	    private int index_ = 0;

	    public NodeListIterator(NodeList nodeList) 
	    {
	        nodeList_ = nodeList;
	    }

	    public Node next() throws NoSuchElementException 
	    {
	        Node node = nodeList_.item(index_++);
	        if (node == null) {
	            throw new NoSuchElementException();
	        }
	        return node;
	    }

	    public boolean hasNext() {
	        return index_ < nodeList_.getLength();
	    }

	    public NodeListIterator iterator() 
	    {
	        return this;
	    }

	    public
	    void remove() throws UnsupportedOperationException
	    {
	        throw new UnsupportedOperationException();
	        // NOT REACHED
	    }
	}
}


// EOF