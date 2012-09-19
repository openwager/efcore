package com.weaselworks.util;

/**
 *
 * @author Lee Crawford (lee.crawford@gmail.com)
 * @copyright Copyright (c) 2009, All Rights Reserved.
 */

public class Pair <Left, Right>
{
    public
    Pair ()
    {
        return;
    }

    public
    Pair (final Left left, final Right right)
    {
        setLeft (left);
        setRight (right);
        return;
    }

    protected Left left;
    public Left getLeft () { return this.left; }
    public void setLeft (final Left left) { this.left = left; return; }

    protected Right right;
    public Right getRight () { return this.right; }
    public void setRight (final Right right) { this.right = right; return; }

    @Override
    public
    String toString ()
    {
        String str = getClass ().getName () + "[";
        str += "left=" + left;
        str += ",right=" + right; 
        return str + "]";
    }
}

// EOF