package com.weaselworks.codec.xml;

import java.io.*;

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

public class JDOMUtil
{
    public static Element getElementByTag(Document document, String tag) {
        NodeList nodeList = document.getElementsByTagName(tag);
        if (nodeList.getLength () > 1) {
                throw new RuntimeException("XMLUtils.getElementByTag: Multiple tags found in document: " + tag);
        }
        return (Element)nodeList.item(0);
}

	/**
	 * Creates a String representation of an XML Document
	 * 
	 * @param document
	 * @param omitHeader
	 * @return
	 * @throws TransformerException
	 */
	public static synchronized String getXMLDocumentAsString (Document document, boolean omitHeader)
	    throws TransformerException
	{
		TransformerFactory tFactory = TransformerFactory.newInstance ();
		Transformer transformer = tFactory.newTransformer ();
		if (omitHeader) {
			transformer.setOutputProperty (OutputKeys.OMIT_XML_DECLARATION, "yes");
		}
		DOMSource source = new DOMSource (document);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ();
		StreamResult result = new StreamResult (byteArrayOutputStream);
		transformer.transform (source, result);
		return byteArrayOutputStream.toString ();
	}

	/**
	 * Creates an XML Document object from the string representation of the
	 * document
	 * 
	 * @param inputStream
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document createXMLDocumentFromStream (InputStream inputStream)
	    throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance ().newDocumentBuilder ();
		return builder.parse (inputStream);
	}

	/**
	 * Returns the String value for the specified tag in the XML document
	 * 
	 * @param document
	 * @param tag
	 * @return
	 * @throws PaymentProcessorException
	 */
	public static String getStringElementValue (Document document, String tag)
	{
		String value = getStringElementValueUnchecked (document, tag);
		if (null != value) {
			return value;
		} else {
			String errorMsg = "XMLUtils.getStringElementValue : Could not find expected tag: " + tag + " in XML response";
			throw new RuntimeException (errorMsg);
		}
	}

	/**
	 * Returns the String value for the specified tag in the XML document
	 * (unchecked)
	 * 
	 * @param document
	 * @param tag
	 * @return
	 */
	public static String getStringElementValueUnchecked (Document document, String tag)
	{
		Element element = getElementByTag (document, tag);
		/*
		 * TODO: upgrade HTMLUNIT so DOM Element class has newer methods
		 * 
		 * if (null != element && null != element.getTextContent ()) { return
		 * element.getTextContent (); } else { return null; }
		 */
		if (null != element && null != element.getFirstChild () && null != element.getFirstChild ().getNodeValue ()) {
			return element.getFirstChild ().getNodeValue ();
		} else {
			return null;
		}
	}
}

// EOF
