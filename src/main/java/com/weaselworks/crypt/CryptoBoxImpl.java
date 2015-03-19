package com.weaselworks.crypt;

import com.weaselworks.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by leecrawford on 3/17/15.
 */

public class CryptoBoxImpl
    implements CryptoBox
{
    protected PBEKeySpec pbeKeySpec;
    protected PBEParameterSpec pbeParamSpec;
    protected SecretKeyFactory keyFac;
    protected Cipher pbeCipher;
    protected SecretKey pbeKey;

    public
    CryptoBoxImpl (final String saltString, final String keyString)
            throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException
    {
        // Create PBE parameter set
        final byte [] salt = saltString.getBytes ("UTF-8");
        pbeParamSpec = new PBEParameterSpec (salt, 20);

        pbeKeySpec = new PBEKeySpec (keyString.toCharArray ());
        keyFac = SecretKeyFactory.getInstance ("PBEWithMD5AndDES");
        pbeKey = keyFac.generateSecret(pbeKeySpec);

        // Create PBE Cipher
        pbeCipher = Cipher.getInstance ("PBEWithMD5AndDES");
        return;
    }

    /**
     *
     * @param str
     * @return
     * @throws java.io.UnsupportedEncodingException
     */

    public
    String encrypt (final String str)
            throws IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, UnsupportedEncodingException
    {
        final byte [] bytes = str.getBytes ("UTF-8");
        final byte [] cipherText = encrypt (bytes);
        return new String (Base64.encode (cipherText), "UTF-8");
    }

    /**
     *
     * @param bytes
     * @return
     */

    public synchronized
    byte [] encrypt (final byte [] bytes)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException
    {
        // Initialize PBE Cipher with key and parameters
        pbeCipher.init (Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

        // Encrypt the cleartext
        final byte[] ciphertext = pbeCipher.doFinal (bytes);
        return ciphertext;
    }

    /**
     *
     * @param str
     * @return
     * @throws java.io.UnsupportedEncodingException
     */

    public
    String decrypt (final String str)
            throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException
    {
        final byte [] cipherText = Base64.decode (str);
        final byte [] plainText = decrypt (cipherText);
        return new String (plainText, "UTF-8");
    }

    /**
     *
     * @param ciphertext
     * @return
     */

    public synchronized
    byte [] decrypt (final byte [] ciphertext)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException
    {
        // Now we want to decrypt it
        pbeCipher.init (Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

        // Decrpypt the ciphertext
        final byte[] cleartext = pbeCipher.doFinal (ciphertext);
        return  cleartext;
    }
}

// EOF