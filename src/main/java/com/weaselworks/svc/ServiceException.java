package com.weaselworks.svc;

import javax.ejb.ApplicationException;

/**
 * 
 * @author crawford
 *
 */

@SuppressWarnings ("serial")
@ApplicationException (rollback=true)
public class ServiceException 
	extends Exception
{
	public
	ServiceException (final String msg)
	{
		super (msg); 
		return;
	}
	
	public
	ServiceException (final String msg, final Throwable cause)
	{
		super (msg, cause); 
		return; 
	}

	interface DEFAULT
	{
		public int SYSTEM = 0x00; 
		public int SUBSYSTEM = 0x00; 
		public int ERROR = 0x00; 
		public int ERROR_CODE = SYSTEM | SUBSYSTEM | ERROR; 
	}
	
	protected int errorCode = DEFAULT.ERROR_CODE; 
	public int getErrorCode () { return this.errorCode; } 
	public void setErrorCode (final int errorCode) { this.errorCode = errorCode; return; } 
	
	public int getSystem () { return errorCode >> 24; } 
	public int getSubsystem () { return (errorCode >> 16) & 0xff; } 
	public int getError () { return errorCode & 0xffff;  }
}

// EOF