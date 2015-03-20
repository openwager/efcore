package com.weaselworks.crypt;

import com.weaselworks.util.Base64;
import com.weaselworks.util.HexUtil;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by leecrawford on 3/19/15.
 */
public class DES3CryptoBox
    implements CryptoBox
{
    protected IvParameterSpec ivspec;
    protected SecretKeySpec keyspec;

    public
    DES3CryptoBox (final byte [] salt, final String keyString)
    {
        ivspec = new IvParameterSpec (salt);
        keyspec = new SecretKeySpec(keyString.getBytes (), "DESede");
        return;
    }


    @Override
    public byte[] encrypt(byte[] bs)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException
    {
        throw new java.lang.RuntimeException ("Unimplemented");
    }

    @Override
    public String encrypt(String in)
            throws IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, UnsupportedEncodingException
    {
        final byte [] out = encrypt (in.getBytes ("UTF-8"));
        return new String (out);
    }

    @Override
    public byte[] decrypt (final byte[] in)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException
    {
        final Cipher cipher = Cipher.getInstance ("DESede/CBC/PKCS5Padding");
        cipher.init (Cipher.DECRYPT_MODE, keyspec, ivspec);
        final byte [] out = cipher.doFinal (in);
        return out;
    }

    @Override
    public String decrypt(String in)
        throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
    {
        final byte [] bytes = decrypt (in.getBytes ("UTF-8"));
        return new String (bytes);
    }
}

// EOF