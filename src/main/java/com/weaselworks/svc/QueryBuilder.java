package com.weaselworks.svc;

import java.util.*;

import javax.persistence.*;

import org.apache.log4j.*;

import com.weaselworks.util.*;

/**
 * 
 * @author crawford
 *
 */

public class QueryBuilder 
{
	protected static final Logger logger = Logger.getLogger (QueryBuilder.class); 
	
	public
	QueryBuilder ()
	{
		return; 
	}
	
	public
	QueryBuilder (final FetchRequest freq)
	{
		setFetchRequest (freq); 
		return; 
	}
	
	public
	QueryBuilder (final String type, final String symbol)
	{
		this (type, symbol, null);  
		return; 
	}
	
	public
	QueryBuilder (final String type, final String symbol, final FetchRequest freq) 
	{
		addTable (new Pair<String, String> (type, symbol));
		addResult (symbol); 
		this.freq = freq; 
		return; 
	}
	
	protected int meta = 0; 
	
	protected List<Pair<String, String>> tables = new ArrayList<Pair<String, String>> ();  
	public List<Pair<String, String>> getTables () { return this.tables; } 
	public void addTable (final Pair<String, String> table) { tables.add (table); return; }
	public void addTable (final String table, final String symbol) { tables.add (new Pair<String, String> (table, symbol)); return; } 
	
	protected List<String> results = new ArrayList<String> ();  
	public List<String> getResults () { return this.results; } 
	public void addResult (final String result) { results.add (result); return; } 
	
	public String getSymbol () { return tables.get (0).getRight (); } 
	
	protected String count = "*"; 
	public String getCount () { return this.count; } 
	public void setCount (final String count) { this.count = count; return; } 
	
	protected FetchRequest freq = new FetchRequest (0, -1, true); 
	public FetchRequest getFetchRequest () { return this.freq; }
	public void setFetchRequest (final FetchRequest freq) { this.freq = freq; return; } 
	
	protected List<Clause> clauses = new ArrayList<Clause> ();
	public void addClause (final Clause clause) { clause.setMeta ("x" + meta++); clauses.add (clause); return; } 
	
	protected List<String> order = new ArrayList<String> (); 
	public void addOrderBy (final String col) { order.add (col); return; } 
	public void addOrderBy (final String col, final String dir) { order.add (col + ' ' + dir); return; }
	
	protected List<String> groupBys = new ArrayList<String> (); 
	public void addGroupBy (final String groupBy) { groupBys.add (groupBy); return; }
	public List<String> getGroupBys () { return this.groupBys; } 
	
	public 
	void addEqualsIfNotNull(final String col, final Object val)
	{
		if(null!=val)
		{
			addEquals(col, val);
		}		
	}
	
	public 
	void addLikeIfNotNull(final String col, final String pattern)
	{
		if(null!=pattern)
		{
			addLike(col, pattern);
		}
		return;
	}
	
	public
	void addEquals (final String col, final Object val)
	{
		addClause (new EqualsClause (col, val));
		return; 
	}
	
	public
	void addNotEquals (final String col, final Object val)
	{
		addClause (new NotEqualsClause (col, val)); 
		return; 
	}	
	
	public
	void addLike (final String col, final String pattern)
	{
		addClause (new LikeClause (col, pattern)); 
		return; 
	}
	
	public
	void addLessThan (final String col, final Object val)
	{
		addClause (new LessThanClause (col, val)); 
		return; 
	}
	
	public
	void addGreaterThan (final String col, final Object val)
	{
		addClause (new GreaterThanClause (col, val)); 
		return; 
	}

	public
	void addLessThanOrEquals (final String col, final Object val)
	{
		addClause (new LessThanOrEqualsClause (col, val)); 
		return; 
	}
	
	public
	void addGreaterThanOrEquals (final String col, final Object val)
	{
		addClause (new GreaterThanOrEqualsClause (col, val)); 
		return; 
	}

	public
	void addIsNull (final String col)
	{
		addClause (new IsNullClause (col)); 
		return; 
	}
	
	public
	void addIsNotNull (final String col)
	{
		addClause (new NotNullClause (col)); 
		return; 
	}
	
	public
	void addJoinEquals (final String left, final String right)
	{
		addClause (new JoinEqualsClause (left, right));
		return; 
	}
	
	/**
	 * 
	 * @see #getSelectSQL()
	 * @return
	 */
	
	public
	String getCountSQL ()
	{
		final StringBuffer buf = new StringBuffer (); 
		buf.append ("select Count("); 
		buf.append (count); 
		buf.append (") "); 
		getSQL (buf); 
		return buf.toString (); 
	}
	
	/**
	 * 
	 * @see #getCountSQL()
	 * @return
	 */
	
	public
	String getSelectSQL ()
	{
		final StringBuffer buf = new StringBuffer ();  
		buf.append ("select ");
		final int cnt = results.size (); 
		for (int i = 0; i < cnt; i ++) { 
			final String result = results.get (i); 
			buf.append (result); 
			if (i < (cnt - 1)) { 
				buf.append (',');
			} else { 
				buf.append (' ');
			}
		}
		getSQL (buf); 
		return buf.toString (); 
	}
	
	/**
	 * 
	 * @param buf
	 */
	
	protected
	void getSQL (StringBuffer buf)
	{
		if (buf == null) { 
			buf = new StringBuffer (); 
		}
		buf.append ("from "); 
		final int cnt = tables.size (); 
		for (int i = 0; i < cnt; i ++) { 
			final Pair<String, String> table = tables.get (i);
			buf.append (table.getLeft ()); 
			buf.append (' '); 
			buf.append (table.getRight ()); 
			if (i < (cnt - 1)) { 
				buf.append (',');
			} else { 
				buf.append (' ');
			}			
		}
		for (int i = 0; i < clauses.size (); i ++) {		
			if (i == 0) { 
				buf.append (" where "); 
			} else { 
				buf.append (" and "); 
			}
			final Clause clause = clauses.get (i); 
			buf.append (clause.emit ()); 
		}
		if (groupBys.size () > 0) { 
			final int len = groupBys.size (); 
			buf.append (" group by "); 
			for (int i = 0; i < len; i ++) { 
				buf.append (groupBys.get (i)); 
				if (i != (len - 1)) { 
					buf.append (','); 
				}
			}
		}
		if (order.size () > 0) { 
			final int len = order.size ();
			buf.append (" order by "); 
			for (int i = 0; i < len; i ++) { 
				buf.append (order.get (i)); 
				if (i != (len - 1)) { 
					buf.append (','); 
				}
			}
		}
		return; 
	}
	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	
	protected
	Query createCountQuery (final EntityManager manager)
	{
		final String sql = getCountSQL (); 
		if (logger.isDebugEnabled()) {
			logger.debug("SQL Count: " + sql);
		}
		final Query query = manager.createQuery (sql); 
		
		for (final Clause clause : clauses) { 
			clause.equip (query); 
		}
		
		return query; 
	}
	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	
	protected
	Query createQuery (final EntityManager manager)
	{
		final String sql = getSelectSQL ();
		if (logger.isDebugEnabled()) {
			logger.debug("SQL Select: " + sql);
		}
		final Query query = manager.createQuery (sql); 

		for (final Clause clause : clauses) { 
			clause.equip (query); 
		}
	
		if (getFetchRequest() != null) {
			QueryUtil.limitQuery (query, getFetchRequest ());
		}
		return query; 
	}


	public interface Clause
	{
		public String emit ();
		public void equip (Query query); 
		public void setMeta (final String meta); 
	}
	
	abstract class ClauseSupport
		implements Clause
	{
		protected ClauseSupport () { return; }
		protected ClauseSupport (final Object val) { setValue (val); return; } 
		
		protected Object val; 
		public Object getValue () { return val; } 
		public void setValue (final Object val) { this.val = val; return; }
	
		protected String meta; 
		public String getMeta () { return meta; } 
		public void setMeta (final String meta) { this.meta = meta; return; } 
	}
	
	abstract class SimpleClauseSupport
		extends ClauseSupport
	{
		public SimpleClauseSupport (final String col, final Object val, final String op)
		{
			super (val); 
			if (col.indexOf ('.') < 0) {
				this.col = getSymbol () + '.' + col; 
			} else {
				this.col = col; 
			}
			this.op = op; 
			return; 
		}
		
		protected String col; 
		public String getColumn () { return col; }
		
		protected String op; 
		public String getOp () { return op; } 
		
		public String emit ()
		{			
			return col + " " + op + " :" + getMeta ();  
		}
		
		public void equip (final Query query)
		{
			query.setParameter (getMeta (), getValue ()); 
			return; 
		}
	}

	/**
	 * 
	 * @author crawford
	 *
	 */
	
	public class OrClause
		implements Clause 
	{
		public
		OrClause (final Clause c1, final Clause c2)
		{
			this.c1 = c1; 
			this.c2 = c2;
			return; 
		}
		
		protected Clause c1, c2; 
		
        public String emit ()
        {
        	return '(' + c1.emit () + " OR " + c2.emit () + ')'; 
        }

		public void equip (Query query)
        {
			c1.equip (query); 
			c2.equip (query); 
			return; 
        }

        public void setMeta (String meta)
        {
        	c1.setMeta (meta + "a"); 
        	c2.setMeta (meta + "b"); 
        	return; 	        
        }
	}
	
	/**
	 * 
	 * @author crawford
	 *
	 */
	
	public class AndClause
		implements Clause
	{
		public
		AndClause (final Clause c1, final Clause c2)
		{
			this.c1 = c2; 
			this.c2 = c2;
			return; 
		}
		
		protected Clause c1, c2; 
		
        public String emit ()
        {
        	return '(' + c1.emit () + " AND " + c2.emit () + ')'; 
        }

		public void equip (Query query)
        {
			c1.equip (query); 
			c2.equip (query); 
			return; 
        }

        public void setMeta (String meta)
        {
        	c1.setMeta (meta + "a"); 
        	c2.setMeta (meta + "b"); 
        	return; 	        
        }
	}

	public class JoinEqualsClause 
		extends SimpleClauseSupport
	{
		public JoinEqualsClause (final String col, final Object val)
		{
			super (col, val, "="); 
			return; 
		}		
		
		public String emit () { return col + " " + op + " " + val; } 
		public void equip (final Query query) { return; } 
	}

	/**
	 * 
	 *
	 */
	
	public class EqualsClause 
		extends SimpleClauseSupport
	{
		public EqualsClause (final String col, final Object val)
		{
			super (col, val, "="); 
			return; 
		}		
	}

	/**
	 * 
	 *
	 */
	
	public class NotEqualsClause
		extends SimpleClauseSupport
	{
		public NotEqualsClause (final String col, final Object val)
		{
			super (col, val, "!="); 
			return; 
		}
	}
	
	/**
	 * 
	 *
	 */
	
	public class LikeClause 
		extends SimpleClauseSupport
	{
		public LikeClause (final String col, final String pattern)
		{
			super (col, pattern, "like"); 
			return; 
		}
	}

	/**
	 * 
	 *
	 */
	
	public class LessThanClause
		extends SimpleClauseSupport
	{
		public
		LessThanClause (final String col, final Object val)
		{
			super (col, val, "<"); 
			return; 
		}
	}
	
	/**
	 * 
	 *
	 */
	
	public class GreaterThanClause
		extends SimpleClauseSupport
	{
		public 
		GreaterThanClause (final String col, final Object val)
		{
			super (col, val, ">"); 
			return; 
		}
	}
	
	/**
	 * 
	 *
	 */
	
	public class LessThanOrEqualsClause
		extends SimpleClauseSupport
	{
		public
		LessThanOrEqualsClause (final String col, final Object val)
		{
			super (col, val, "<="); 
			return; 
		}
	}
	
	/**
	 * 
	 *
	 */
	
	public class GreaterThanOrEqualsClause
		extends SimpleClauseSupport
	{
		public 
		GreaterThanOrEqualsClause (final String col, final Object val)
		{
			super (col, val, ">="); 
			return; 
		}
	}

	/**
	 * 
	 */
	
	public abstract class NullClause
		implements Clause
	{
		protected
		NullClause (final String col, final String op)
		{
			this.emit = col + ' ' + op;
			return; 
		}

		protected String emit;  
		
		public
		String emit ()
		{
			return emit;  
		}

		public 
		void equip (Query query)
		{
			// EMPTY
			return; 
		}

		public 
		void setMeta (String meta)
		{
			// EMPTY
			return; 
		}
	}
	
	/**
	 * 
	 */
	
	public class IsNullClause
		extends NullClause
	{
		public
		IsNullClause (final String col)
		{
			super (col, "is null"); 
			return; 
		}		
	}
	
	/**
	 * 
	 */
	
	public class NotNullClause 
		extends NullClause
	{
		public
		NotNullClause (final String col)
		{
			super (col, "is not null"); 
			return; 
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param manager
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
    public <T>
	FetchResults<T> execute (final EntityManager manager)
	{
		Query q; 
		long total = -1L; 
		
		// Calculate the total number of results if they've requested it
		
		if (freq != null) { 
			if (! freq.getSkipCount ()) { 
				q = createCountQuery (manager); 
				total = (Long) q.getResultList ().get (0); 
			}
		}
		
		// Execute the data retrieval query
		
		q = createQuery (manager);		
		QueryUtil.limitQuery (q, freq); 
		
		final List<T> res = q.getResultList (); 
		if (freq == null) {
			total = res.size();
		}
		return new FetchResults <T> (freq, res, total); 
	}		
}

// EOF