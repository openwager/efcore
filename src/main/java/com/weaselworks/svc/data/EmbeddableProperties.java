package com.weaselworks.svc.data;

import java.io.*;
import java.util.*;

import javax.persistence.*;

import org.apache.log4j.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings("serial")
@Embeddable
public class EmbeddableProperties
    implements Propertied, Serializable
{
	private static final Logger logger = Logger.getLogger (EmbeddableProperties.class); 
	
	public
	EmbeddableProperties ()
	{
		return; 
	}
	
    public
    EmbeddableProperties (final EmbeddableProperties other)
    {
        setProperties(other.getProperties ());
        return; 
    }

    @Lob
    @Column (name="PROPS")
    protected byte[] props;

    @Transient
    private Map<String, String> properties = new HashMap<String, String>();

    @Transient
    private boolean requiresDehydrate = true;

    /**
     * Caller should not call this method to modify the properties.
     */

    public
    Map<String, String> getProperties()
    {
        dehydrate();
        return properties;
    }

    public
    void setProperties (Map<String, String> properties)
	{
		this.properties = properties;
		hydrate ();
		return;
	}

	public
	void putAll (Map<String, String> properties)
	{
		dehydrate ();
		this.properties.putAll (properties);
		hydrate ();
		return;
	}

	public
	String getProperty (String key)
	{
		dehydrate ();
		return properties.get (key);
	}

	public 
	boolean hasProperty (String key)
	{
		return getProperty (key) != null;
	}

    public
    void setProperty (String key, String value)
	    throws NullPointerException
	{
		if (null == key) {
			throw new NullPointerException ();
		}
		dehydrate ();
		properties.put (key, value);
		hydrate ();
		return;
	}

	public 
	void removeProperty (String key)
	{
		dehydrate ();
		properties.remove (key);
		hydrate ();
		return; 
	}

	public Set<String> getPropertyKeys ()
	{
		dehydrate ();
		return properties.keySet ();
	}

	enum EncodeMethod
	{
		JavaMapSerialize, URLEncodedMap
	};

	protected final static EncodeMethod encodingMethod = EncodeMethod.JavaMapSerialize; // URLEncodedMap;

	/**
	 * 
	 */
	
	private 
	void hydrate ()
	{
		if (properties != null) {
			if (encodingMethod == EncodeMethod.URLEncodedMap) {
//				byte[] urlEncodedMap = MapEncoder.encode (properties);
//				ByteArrayOutputStream outs = new ByteArrayOutputStream ();
//				try {
//					outs.write (0xAD);
//					outs.write (0xEC);
//					outs.write (urlEncodedMap);
//					props = outs.toByteArray ();
//					requiresDehydrate = false;
//				} catch (IOException ioe) {
//					throw new RuntimeException ("failed to encode properties", ioe);
//				}
			} else if (encodingMethod == EncodeMethod.JavaMapSerialize) {
				try { 				
					props = SerializationUtils.serialize ((Serializable) properties); 
				} catch (final Exception e) { 
					logger.error (e.getMessage (), e); 
				}
				requiresDehydrate = false;
			}
		}
		return;
	}

	@SuppressWarnings ("unchecked")
	private 
	void dehydrate ()
	{
		if (requiresDehydrate == true && props != null) {
			// Version 1 properties: serialized Map
			// If first 2 bytes match this magic
			// java.io.ObjectStreamConstants.STREAM_MAGIC
			if (props.length >= 2 && props[0] == (byte) 0xAC && props[1] == (byte) 0xED) {
				try { 
					properties = (Map<String, String>) SerializationUtils.deserialize (props);
				}
				catch (final Exception e) { 
					logger.error (e.getMessage (), e); 
				}
				requiresDehydrate = false;
				return;
			} else 
			// Version 2 properties: URL encoded
			if (props.length >= 2 && props[0] == (byte) 0xAD && props[1] == (byte) 0xEC) {
//				byte[] encoded = ArrayUtils.subarray (props, 2, props.length);
//				properties = MapEncoder.decode (encoded);
//				requiresDehydrate = false;
//				return;
			}
		}
	}

	@Override
	public int hashCode ()
	{
		return getProperties ().hashCode ();
	}

	@Override
	public boolean equals (final Object obj)
	{
		if (!(obj instanceof EmbeddableProperties)) {
			return false;
		}
		final EmbeddableProperties o = (EmbeddableProperties) obj;
		return Util.arrayEquals (this.props, o.props);
	}

	@Override
	public String toString ()
	{
		final StringBuilder s = new StringBuilder ();
		s.append (this.getClass ().getName ());
		s.append ('[');
		s.append ("properties=");
		s.append ('{');
		s.append (getProperties ());
		s.append ('}');
		return s.toString ();
	}
}

// EOF