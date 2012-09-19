package com.weaselworks.util;

import java.io.*;
import java.util.*;

import com.weaselworks.svc.*;

/**
 * 
 * @author crawford
 *
 */

public class PaginationSequenceTest
{
	public static
	void main (final String [] args)
		throws IOException
	{
		final BufferedReader br = new BufferedReader (new InputStreamReader (System.in)); 		
		
		while (true) {
			System.out.println ("(page, total, blocksize), (sides, middle): ");
			final String str = br.readLine (); 
			if (StringUtil.isEmpty (str)) { 
				break; 				
			}
			final StringTokenizer toker = new StringTokenizer (str); 
			if (toker.countTokens () != 5) { 
				System.err.println ("ERROR: 5 values required.");
				continue; 
			}
			final int page = Integer.parseInt (toker.nextToken ()); 
			final int total = Integer.parseInt (toker.nextToken ()); 
			final int blocksize = Integer.parseInt (toker.nextToken ());
			final FetchRequest freq = new FetchRequest (page * blocksize, blocksize);
			final FetchResults<Object> fres = new FetchResults<Object> (freq, null, total);
			
			final int sides = Integer.parseInt (toker.nextToken ());		
			final int middle= Integer.parseInt (toker.nextToken ()); 
			final PaginationSequence pages = new PaginationSequence (fres, sides, middle); 
			
			PaginationSequence.dump (pages); 			
		}
		
		System.out.println ("DONE."); 
		return; 
	}
}

// EOF
