package com.weaselworks.jms;

import javax.jms.*;

/**
 * 
 * @author crawford
 *
 */

public class JMSUtil
{
	private
	JMSUtil ()
	{
		return; 
	}
		
	/**
	 * 
	 * @param conn
	 */
	
	public static
	void safeClose (final Connection conn)
	{
		if (conn != null) {
			try { 
				conn.close (); 
			}
			catch (final JMSException jms_e) { 
				// IGNORED
			}
		}
		return; 
	}
	
	/**
	 * 
	 * @param session
	 */
	
	public static
	void safeClose (final Session session)
	{
		if (session != null) {
			try { 
				session.close (); 
			}
			catch (final JMSException jms_e) { 
				// IGNORED
			}
		}
		return; 
	}
}

// EOF