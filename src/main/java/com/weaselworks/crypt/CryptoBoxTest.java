package com.weaselworks.crypt;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by leecrawford on 3/17/15.
 */
public class CryptoBoxTest
{
    public static final String KEY = "thisISaTest";
    public static final String SALT = "NaClNaCl"; // must be 8 bytes
    public static final String PHRASE = "here is a sample!sssssss";

    public static
    void main (final String [] args)
        throws Exception
    {
        System.out.println ("CryptoBoxText");

        while (true) {
            final String salt = readString ("salt");
            final String key = readString ("key");
            final String plaintext = readString ("plaintext");

            System.out.println ();
            System.out.println ("salt='" + salt + "'");
            System.out.println ("key='" + key + "'");
            System.out.println ("plaintext='" + plaintext + "'");

            final CryptoBoxFactory factory = new StandardCryptoBoxFactory (key);
            final CryptoBox crypto = factory.getInstance(salt);
            final String ciphertext = crypto.encrypt (plaintext);
            System.out.println ("ciphertext='" + ciphertext + "'");
            final String decoded = crypto.decrypt (ciphertext);
            System.out.println ("decoded='" + decoded + "'");
            System.out.println ("success=" + plaintext.equals (decoded));
            System.out.println ();
        }

        // NOT REACHED
    }

    /**
     * Used to read a value from the console.
     *
     * @param label
     * @return
     * @throws IOException
     */

    protected static
    String readString (final String label)
            throws IOException
    {
        while (true) {
            System.out.print (label + "> ");
            final DataInputStream dis = new DataInputStream (System.in);
            final String result = dis.readLine();
            if (! result.equals ("")) {
                return result;
            }
        }

        // NOT REACHED
    }
}

// EOF