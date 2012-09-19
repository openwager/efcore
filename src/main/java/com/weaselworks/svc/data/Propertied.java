package com.weaselworks.svc.data;

import java.util.*;

/**
 * 
 * @author crawford
 *
 */

public interface Propertied
{
    public
    Map<String, String> getProperties ();

    public
    String getProperty (String key);

    public
    void setProperty (String key, String value);

    public
    void removeProperty (String key); 

    public
    boolean hasProperty (String key);
}

// EOF