package com.weaselworks.util;

/**
 * 
 * @author crawford
 *
 */

public class MathUtil
{
	private
	MathUtil ()
	{
		return; 
	}

	public static
	int sign (final int val)
	{
		if (val < 0) { 
			return -1; 
		} else if (val == 0) { 
			return 0; 
		} else { 
			return 1; 
		}
	}
	
	public static
	int sign (final long val)
	{
		if (val < 0) { 
			return -1; 
		} else if (val == 0) { 
			return 0; 
		} else { 
			return 1; 
		}
	}
	
	public static 
	int clip (final int val, final int low, final int high)
	{
		if (val < low) { 
			return low; 
		} else if (val > high) { 
			return high; 
		} else { 
			return val;
		}
	}
	
	public static
	long clip (final long val, final long low, final long high) 
	{
		if (val < low) { 
			return low; 
		} else if (val > high) { 
			return high; 
		} else { 
			return val; 
		}
	}
}

// EOF