package com.weaselworks.util;

import org.testng.annotations.Test;

/**
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class HexUtilTest
{
    @Test
    public
    void test1 ()
    {
        byte[] ba = HexUtil.decodeString("3AE2922BB4404028D125657FE749C30E");
        String s = HexUtil.encodeBytes(ba);
        if ("3AE2922BB4404028D125657FE749C30E".equals(s)) {
            System.out.println("same");
        } else {
            System.out.println("not same");
        }
        return;         
    }
}

// EOF