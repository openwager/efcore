package main.java.com.weaselworks.crypt;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by leecrawford on 3/18/15.
 */
public interface CryptoBoxFactory
{
    /**
     *
     * @param salt
     * @return
     */

    public
    CryptoBox getInstance (final String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException;
}

// EOF