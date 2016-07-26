package com.qt.command;

import com.qt.core.command.ExecuteResult;
import com.qt.core.command.LocalCommandExecutorService;
import com.qt.core.command.LocalCommandExecutorServiceImpl;

/**
 * @author: Tim
 * @since: Mar 9, 2015
 */
public class LocalCommandExecutorTest {
	public static void main(String[] args) {
		LocalCommandExecutorService service = new LocalCommandExecutorServiceImpl();
		String[] command = new String[] { "ping", "127.0.0.1" };
		ExecuteResult result = service.executeCommand(command, 5000);
		System.out.println("退出码：" + result.getExitCode());
		System.out.println("输出内容：" + result.getExecuteOut());
	}
}
