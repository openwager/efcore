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
        final String key = "ItsAL0vel33DayinTh3NEigh";
        final CryptoBoxFactory factory = new DES3CryptoBoxFactory (key);

        final String salt = "7433ba5d-c942-45a8-a81e-51e72b46f52e";
        final CryptoBox crypto = factory.getInstance (salt);

        final String encrypted = "vjFdee74/37Ll6GPLVntJQ==";
        final byte [] ciphertext = Base64.decode(encrypted);
        System.out.println ("cipher=" + HexUtil.encodeBytes(ciphertext));
        System.out.println ("bytes=" + ciphertext.length);

        final byte [] decrypted = crypto.decrypt (ciphertext);
        System.out.println ("plaintext=" + new String (decrypted));
        return;
    }
}

// EOF