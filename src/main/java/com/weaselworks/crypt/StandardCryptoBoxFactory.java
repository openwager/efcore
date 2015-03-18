package main.java.com.weaselworks.crypt;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by leecrawford on 3/18/15.
 */

public class StandardCryptoBoxFactory
        implements CryptoBoxFactory
{
    public
    StandardCryptoBoxFactory (final String key)
    {
        assert key != null;

        this.key = key;
        return;
    }

    protected String key;

    @Override
    public
    CryptoBox getInstance (String salt)
        throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException
    {
        assert salt != null;

        // Make sure we only have 8 bytes of salt to work with here

        while (salt.length() < 8) {
            salt += salt;
        }
        salt = salt.substring (salt.length () - 8);

        // Generate a new crypto box for encrypting and decrypting

        final CryptoBox crypto = new CryptoBoxImpl (salt, key);
        return crypto;
    }
}

// EOF