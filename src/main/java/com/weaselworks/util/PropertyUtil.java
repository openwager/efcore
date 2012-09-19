package com.weaselworks.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

public class PropertyUtil {
	public static final String SERVLET_CONTEXT_KEY = "property-reader";

	public static final String SIXFLAGS_IPHONE_FILE = "sixflagsIPhone.properties";
	public static final String SIXFLAGS_IPHONE_REMINDER_SERVICE_ACTIVE = "reminderServiceActive";	
	public static final String SIXFLAGS_IPHONE_USE_PRODUCTION_PUSH_SERVER = "useProductionServer";
	public static final String SIXFLAGS_IPHONE_CERT_PASSWORD = "pushCertificatePassword";
	
	private Map<String,Properties> loadedProperties = new HashMap<String,Properties>();
	
	private PropertyUtil()
	{
	}
	
	public static PropertyUtil getInstance(ServletContext ctx)
	{
		PropertyUtil instance = (PropertyUtil) ctx.getAttribute(SERVLET_CONTEXT_KEY);
		if (instance != null)
			return instance;
		return new PropertyUtil();
	}
	
	public Properties getProperties (String filename) throws IOException
	{

		if (loadedProperties.containsKey(filename))
		{
			return loadedProperties.get(filename);
		}
		else
		{
			InputStream inStream = Thread.currentThread().
				getContextClassLoader().getResourceAsStream(filename);
			Properties properties = new Properties();
			properties.load(inStream);
			loadedProperties.put(filename, properties);
			return properties;
		}
	}
	
	public Object getProperty (String filename, String key) throws IOException
	{
		return getProperties(filename).get(key);
	}
}
