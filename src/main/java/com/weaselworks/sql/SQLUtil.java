package com.weaselworks.sql;

import java.sql.*;

/**
 * 
 * @author crawford
 *
 */

public class SQLUtil
{
	private
	SQLUtil ()
	{
		return; 
	}
	
	public static
	void safeClose (final PreparedStatement ps)
	{
		if (ps != null) { 
			try { 
				ps.close (); 
			}
			catch (final SQLException sql_e) { 
				// IGNORED
			}
		}
		return; 
	}

	public static
	void safeClose (final Statement ps)
	{
		if (ps != null) { 
			try { 
				ps.close (); 
			}
			catch (final SQLException sql_e) { 
				// IGNORED
			}
		}
		return; 
	}

	public static
	void safeClose (final Connection conn)
	{
		if (conn != null) { 
			try { 
				conn.close (); 
			}
			catch (final SQLException e) { 
				// IGNORED
			}
		}
		return; 
	}
	
	public static
	void safeClose (final ResultSet rs) 
	{
		if (rs != null) { 
			try { 
				rs.close (); 
			}
			catch (final SQLException e) { 
				// IGNORED
			}
		}
		return; 
	}

}

// EOF