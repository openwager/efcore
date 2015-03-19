package com.weaselworks.crypt;

import com.weaselworks.util.Base64;
import com.weaselworks.util.HexUtil;

/**
 * Created by leecrawford on 3/19/15.
 */

public class DES3CryptoBoxTest
{
    public static
    void main (final String [] args)
            throws Exception
    {
        final String salt = "squidly!";
        final String key = "hi there lee; how areyou";
        final CryptoBox crypto = new DES3CryptoBox (salt, key);

        final String encrypted = "5qkO+wVfZbgGsr35VXj0UQ==";
        final byte [] ciphertext = Base64.decode(encrypted);
        System.out.println ("cipher=" + HexUtil.encodeBytes(ciphertext));
        System.out.println ("bytes=" + ciphertext.length);

        final byte [] decrypted = crypto.decrypt (ciphertext);
        System.out.println ("plaintext=" + new String (decrypted));
        return;
    }
}

// EOF