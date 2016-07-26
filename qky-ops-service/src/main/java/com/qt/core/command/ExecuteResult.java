package com.qt.core.command;

/**
 * @author: Tim
 * @since: Mar 9, 2015
 */
public class ExecuteResult {
	@Override
	public String toString() {
		return "ExecuteResult [exitCode=" + exitCode + ", executeOut="
				+ executeOut + "]";
	}

	private int exitCode;
	private String executeOut;

	public ExecuteResult(int exitCode, String executeOut) {
		super();
		this.exitCode = exitCode;
		this.executeOut = executeOut;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

	public String getExecuteOut() {
		return executeOut;
	}

	public void setExecuteOut(String executeOut) {
		this.executeOut = executeOut;
	}

}