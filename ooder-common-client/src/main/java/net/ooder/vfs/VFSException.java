/**
 * $RCSfile: VFSException.java,v $
 * $Revision: 1.1 $
 * $Date: 2025/07/08 00:26:08 $
 *
 * Copyright (C) 2025 ooder, Inc. All rights reserved.
 *
 * This software is the proprietary information of ooder, Inc.
 * Use is subject to license terms.
 */
/**
 * $RCSfile: VFSException.java,v $
 * $Revision: 1.0 $
 * $Date: 2025/08/25 $
 * <p>
 * Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 */
package net.ooder.vfs;

import net.ooder.common.JDSException;

import java.io.PrintStream;
import java.io.PrintWriter;

public class VFSException extends JDSException {

	// 虚拟文件服务器相关异常，包括启动，管理命令等
	public static final int SERVERNOTSTARTEDERROR = 10;

	public static final int SERVERSTARTERROR = 11;

	public static final int SERVERSTOPERROR = 12;

	public static final int SERVERRESTARTERROR = 13;

	public static final int SERVERSTATUSCOMMANDERROR = 14;

	public static final int SERVERSTARTCOMMANDERROR = 15;

	public static final int SERVERSTOPCOMMANDERROR = 16;

	public static final int SERVERRESTARTCOMMANDERROR = 17;

	// 虚拟文件服务器载入相关异常
	public static final int LOADWORKFLOWSERVERERROR = 20;

	public static final int LOADRIGHTENGINEERROR = 21;

	public static final int LOADAPPLICATIONCONFIGERROR = 22;

	public static final int LOADAPPLICATIONCONFIGMAPPINGERROR = 23;

	public static final int LOADADMINSERVICEERROR = 24;

	// 事件相关异常
	public static final int UNSUPPORTSERVEREVENTERROR = 30;

	public static final int UNSUPPORTCOREPROCESSEVENTERROR = 31;

	public static final int UNSUPPORTCOREACTIVITYEVENTERROR = 32;

	public static final int UNSUPPORTPROCESSEVENTERROR = 33;

	public static final int UNSUPPORTACTIVITYEVENTERROR = 34;

	public static final int DISPATCHCOREPROCESSEVENTERROR = 35;

	public static final int DISPATCHCOREACTIVITYEVENTERROR = 36;

	// 应用管理相关异常
	public static final int APPLICATIONNOTFOUNDERROR = 100;

	// 登陆验证相关异常
	public static final int NOTLOGINEDERROR = 200;

	public static final int ALREADYLOGINEDERROR = 201;

	// 定义相关异常
	public static final int PROCESSDEFINITIONERROR = 1000;

	public static final int ACTIVITYDEFINITIONERROR = 1001;

	public static final int ROUTEDEFINITIONERROR = 1002;

	public static final int GETPROCESSDEFLISTERROR = 1003;

	public static final int GETPROCESSINSTLISTERROR = 1004;

	public static final int GETACTIVITYINSTLISTERROR = 1005;

	// 实例相关异常
	public static final int CREATEPROCESSINSTANCEERROR = 2000;

	public static final int STARTPROCESSINSTANCEERROR = 2001;

	public static final int NEWPROCESSINSTANCEERROR = 2002;

	// 事务处理相关异常
	public static final int TRANSACTIONBEGINERROR = 100000;

	public static final int TRANSACTIONCOMMITERROR = 100001;

	public static final int TRANSACTIONROLLBACKERROR = 100002;
	
//	 表单相关异常
	public static final int FORMNOTFONUD = 	9000;

	/** Exception that might have caused this one. */
	private Throwable cause;

	/** Exception code that defined in VFS. */
	private int errorCode;

	/**
	 * Constructs a build exception with no descriptive information.
	 */
	public VFSException() {
		super();
	}

	/**
	 * Constructs an exception with the given descriptive message and error
	 * code.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code>.
	 * @param errorCode
	 *            Error code defined in VFS.
	 */
	public VFSException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs an exception with the given descriptive message and error
	 * code.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code>.
	 * @param cause
	 *            The exception that might have caused this one. May be
	 *            <code>null</code>.
	 * @param errorCode
	 *            Error code defined in VFS.
	 */
	public VFSException(String message, Throwable cause, int errorCode) {
		this(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs an exception with the given descriptive message.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code>.
	 */
	public VFSException(String message) {
		super(message);
	}

	/**
	 * Constructs an exception with the given message and exception as a root
	 * cause.
	 * 
	 * @param message
	 *            A description of or information about the exception. Should
	 *            not be <code>null</code> unless a cause is specified.
	 * @param cause
	 *            The exception that might have caused this one. May be
	 *            <code>null</code>.
	 */
	public VFSException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	/**
	 * Constructs an exception with the given exception as a root cause.
	 * 
	 * @param cause
	 *            The exception that might have caused this one. Should not be
	 *            <code>null</code>.
	 */
	public VFSException(Throwable cause) {
		super(cause.toString());
		this.cause = cause;
	}

	/**
	 * Retrieves the VFS exception code for this <code>VFSException</code>
	 * object.
	 * 
	 * @return the vendor's error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Returns the nested exception, if any.
	 * 
	 * @return the nested exception, or <code>null</code> if no exception is
	 *         associated with this one
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * Returns the nested exception, if any.
	 * 
	 * @return the nested exception, or <code>null</code> if no exception is
	 *         associated with this one
	 */
	public Throwable getException() {
		return cause;
	}

	/**
	 * Returns the location of the error and the error message.
	 * 
	 * @return the location of the error and the error message
	 */
	public String toString() {
		return getMessage();
	}

	/**
	 * Prints the stack trace for this exception and any nested exception to
	 * <code>System.err</code>.
	 */
	public void printStackTrace() {
		printStackTrace(System.err);
	}

	/**
	 * Prints the stack trace of this exception and any nested exception to the
	 * specified PrintStream.
	 * 
	 * @param ps
	 *            The PrintStream to print the stack trace to. Must not be
	 *            <code>null</code>.
	 */
	public void printStackTrace(PrintStream ps) {
		synchronized (ps) {
			if (errorCode != 0) {
				ps.println("Error Code: " + errorCode);
			}
			super.printStackTrace(ps);
			if (cause != null) {
				ps.println("--- Nested Exception ---");
				cause.printStackTrace(ps);
			}
		}
	}

	/**
	 * Prints the stack trace of this exception and any nested exception to the
	 * specified PrintWriter.
	 * 
	 * @param pw
	 *            The PrintWriter to print the stack trace to. Must not be
	 *            <code>null</code>.
	 */
	public void printStackTrace(PrintWriter pw) {
		synchronized (pw) {
			if (errorCode != 0) {
				pw.println("Error Code: " + errorCode);
			}
			super.printStackTrace(pw);
			if (cause != null) {
				pw.println("--- Nested Exception ---");
				cause.printStackTrace(pw);
			}
		}
	}

}
