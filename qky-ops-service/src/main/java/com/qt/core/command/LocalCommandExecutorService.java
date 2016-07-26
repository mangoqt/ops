package com.qt.core.command;

/**
 * @author: Tim
 * @since: Mar 9, 2015
 */
public interface LocalCommandExecutorService {
	ExecuteResult executeCommand(String[] command, long timeout);
}