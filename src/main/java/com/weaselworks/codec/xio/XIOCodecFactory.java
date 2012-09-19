package com.weaselworks.codec.xio;

/**
 * 
 * @author crawford
 *
 */

public interface XIOCodecFactory
{
	/**
	 * 
	 * @param codec
	 */
	
    public
    void addCodec (final XIOCodec<?> codec);
    
    /**
     * 
     * @param codecs
     */
    
    public
    void addCodecs (final XIOCodec<?> [] codecs);     

    /**
     * 
     * @param <Type>
     * @param type
     * @return
     */
    
    public <Type>
    XIOCodec<? super Type> getCodec (Class<Type> type);

    /**
     * 
     * @param objectId
     * @return
     */
    
    public
    XIOCodec<?> getCodec (int objectId);
}

// EOF