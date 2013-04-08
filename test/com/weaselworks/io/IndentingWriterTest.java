package com.weaselworks.io;

import java.io.*;

import org.testng.annotations.*;

/**
 * 
 * @author crawford
 *
 */

public class IndentingWriterTest
{
	@Test
	public
	void test1 ()
	{
		final IndentationPolicy ip = new StringIndentationPolicy ("<-->"); 
		final OutputStreamWriter osw = new OutputStreamWriter (System.err); 
		final IndentingWriter iw = new IndentingWriter (osw, ip); 
		final PrintWriter pw = new PrintWriter (iw); 
		
		try { 
			for (int i = 0; i < 10; i ++) { 
				pw.println (" FDFDFSADSFADFS");
				if (i == 1 || i == 3 || i == 5) { 
					iw.push (); 
				} else if (i == 6 || i == 7 || i == 8) { 
					iw.pop (); 
				}
				
			}
			
			pw.flush ();
		}
		finally { 
			pw.close (); 
		}
		return; 
		
	}
}

// EOF