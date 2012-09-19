import java.io.*;
import java.net.*;
import java.util.*;

import org.json.*;

public class MFPClient
{
	public static final String USAGE = 
		"USAGE: % java MFPClient [-h] [-r] [-b <body>] [-i]\n" + 
		"\n" +
		"where:\n" +
		"  -h - help\n" +
		"  -r - raw message\n" +
		"  -m <message> - request\n" +
		"  -url <url> - endpoint URL\n" + 
		"  -i - message in stdin\n" + 
		"  -v <value> - value to extract"; 
	
	public static
	void main (final String [] args)
		throws Exception
	{
		// Package the command-line for easier parsing
		
		final List<String> list = Arrays.asList (args); 
		final Iterator<String> iter = list.iterator (); 
		
		// Figure out what we're sending to the server
		
		String api = null; 
		boolean raw = false; 
		Boolean stdin = null; 
		String request = null;
		String value = null; 
		boolean debug = false; 
		
		while (iter.hasNext ()) { 
			final String arg = iter.next (); 
			
			if (arg.equals ("-h")) { 
				System.err.println (USAGE); 
				System.exit (-2); 
			} else if (arg.equals ("-r")) { 
				raw = true; 
			} else if (arg.equals ("-d")) { 
				debug = true; 
			} else if (arg.equals ("-m")) {
				if (! iter.hasNext ()) { 
					System.err.println ("ERROR: Missing argument <message>"); 
					System.exit (-3);
					// NOT REACHED
				}
				request = iter.next (); 
			} else if (arg.equals ("-i")) { 
				stdin = true; 
			} else if (arg.equals ("-v")) { 
				if (! iter.hasNext ()) { 
					System.err.println ("ERROR: Missing argument <value>"); 
					System.exit (-8); 
					// NOT REACHED
				}
				value = iter.next (); 
			} else if (arg.equals ("-api")) { 
				if (! iter.hasNext ()) { 
					System.err.println ("ERROR: Missing argument <url>"); 
					System.exit (0-7); 
					// NOTE REACHED
				}
				api = iter.next (); 
			} else { 
				System.err.println ("ERROR: Unrecognized parameter: " + arg); 
				System.exit (-1); 
			}
		}
		
		if (request == null && stdin == null) { 
			System.err.println (USAGE);
			System.exit (-4);
			// NOT REACHED
		}
		
		if (api == null) { 
			System.err.println ("ERROR: Endpoint URL not specified."); 
			System.exit (-5); 
			// NOT REACHED
		}
		
		// Fetch the request from stdin if requested
		
		if (stdin != null) { 
			request = readFully (System.in); 
		}
		
		// Put together the message

		if (! raw) { 
			request = "{ \"_t\": \"mfmessage\", \"header\": { \"_t\": \"mfheader\" }, \"body\": " + request + " }";
		}

		// Debug
		
		if (debug) { 
			System.err.println ("DEBUG: " + debug); 
			System.err.println ("ENDPOINT: " + api); 
			System.err.println ("RAW: " + raw); 
			System.err.println ("REQUEST: " + request); 
			System.err.println ("VALUE: " + value); 
		}
		
		// Send the request
		
		final StringBuffer buf = new StringBuffer (); 
		buf.append (api); 
		buf.append ("?json=");         
    	buf.append (URLEncoder.encode (request, "UTF-8"));

       	final String urlstr = buf.toString (); 
    	final URL url = new URL (urlstr); 
    	final HttpURLConnection conn = (HttpURLConnection) url.openConnection (); 
    	conn.setDoOutput (true); 
    	
    	final InputStream is = conn.getInputStream ();
    	final String results = readFully (is); 
   
    	if (debug) { 
    		System.err.println ("RESPONSE CODE: " + conn.getResponseCode ()); 
    		System.err.println ("RESPONSE: /" + results + "/"); 
    	}
    	
    	// Extract the results
    	
    	if (value != null) { 
        	Object json = new JSONObject (results);
        	final String [] path = value.split ("\\.");
        	for (int i = 0; i < path.length; i ++) {
        		if (json == null) { 
        			break;
        		}
        		if (json instanceof JSONObject) {         			
            		json = ((JSONObject) json).get (path [i]);
        		}
        	}
    		System.out.println (json); 
    	} else { 
    		System.out.println (results); 
    	}
    	
		return; 
	}
	
    public static
    String readFully (final InputStream is)
    	throws IOException
    {
        final StringBuffer sb = new StringBuffer ();
        int c;

        while ((c = is.read ()) != -1) {
            sb.append ((char) c);
        }

        return sb.toString ();
    }
}

// EOF