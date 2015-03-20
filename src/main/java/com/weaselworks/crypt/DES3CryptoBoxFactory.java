package com.weaselworks.crypt;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by leecrawford on 3/19/15.
 */

public class DES3CryptoBoxFactory
   implements CryptoBoxFactory
{
    public DES3CryptoBoxFactory (final String key)
    {
        assert key != null;

        this.key = key;
        return;
    }

    protected String key;

    protected
    byte [] fromUUID (final UUID uuid)
    {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    protected
    byte [] fromUUID (final String cid)
    {
        final UUID uuid = UUID.fromString (cid);
        return fromUUID (uuid);
    }

    @Override
    public
    CryptoBox getInstance (final String cid)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException
    {
        assert cid != null;


        // Generate a new crypto box for encrypting and decrypting

        byte [] salt = fromUUID (cid);
        salt = Arrays.copyOfRange(salt, 0, 8);
        final CryptoBox crypto = new DES3CryptoBox (salt, key);
        return crypto;
    }
}

// EOF
