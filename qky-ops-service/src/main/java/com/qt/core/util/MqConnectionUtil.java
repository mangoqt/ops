/**
 * Copyright(c)2005-2013 GuangDong QTONE Education Co.,Ltd<br>
 * All rights reserved. Use is subject to license terms.
 */
package com.qt.core.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.qt.xxtmonitor.model.MonitorObj;
import com.qt.xxtmonitor.model.MqConn;
import com.qt.xxtmonitor.model.MqMonitor;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消息队列客户端管理
 * 
 * @author Tim
 */
public class MqConnectionUtil {
	public static Logger logger = Logger.getLogger(MqConnectionUtil.class
			.getName());

	private static MqConnectionUtil util = new MqConnectionUtil();

	private Map<String, Connection> connectionMap = new HashMap<String, Connection>();
	
	private Map<String, Channel> channelMap = new HashMap<String, Channel>();

	private MqConnectionUtil() {
	}

	public static MqConnectionUtil getInstance() {
		return util;
	}

	public void initConnection(List<MonitorObj> mqMonitors) throws IOException {
		for (MonitorObj mqMonitor : mqMonitors) {
			MqConn conn = ((MqMonitor) mqMonitor).getMqConn();
			String key = conn.getHost() + conn.getPort();

			if (connectionMap.containsKey(key)) {
				Connection connection = connectionMap.get(key);
				if (connection == null || !connection.isOpen()) {
					connection = createPooledConnection(conn);
				}
			}else{
				Connection connection = createPooledConnection(conn);
				connection = createPooledConnection(conn);
				connectionMap.put(key, connection);
			}
		}
	}
	
	public synchronized void initConnection(MqConn conn) throws IOException {
		String key = conn.getHost() + conn.getPort();

		if (connectionMap.containsKey(key)) {
			Connection connection = connectionMap.get(key);
			if (connection == null || !connection.isOpen()) {
				connection = createPooledConnection(conn);
			}
		}else{
			Connection connection = createPooledConnection(conn);
			connectionMap.put(key, connection);
		}
	}
	

	public Channel getChannel(String key, String queueName, boolean durable)
			throws IOException {
		Connection connection = connectionMap.get(key);
		Channel channel = channelMap.get(key+queueName);
		if(channel==null || !channel.isOpen()){
			channel = connection.createChannel();
			channel.queueDeclare(queueName, durable, false, false, null); // 声明消息队列，且为可持久化的
			channelMap.put(key+queueName, channel);
		}
		return channel;
	}

	private Connection createPooledConnection(MqConn conn) throws IOException {
		ConnectionFactory connectionFactory = new ConnectionFactory(); // 创建连接工厂
		connectionFactory.setHost(conn.getHost());
		connectionFactory.setPort(conn.getPort());
		connectionFactory.setUsername(conn.getAccount());
		connectionFactory.setPassword(conn.getPassword());
		Connection connection = connectionFactory.newConnection();
		return connection;
	}

	public QueueingConsumer newConsumer(String key, int rowCount,String queueName) throws IOException {
		Channel channel = getChannel(key,queueName,true);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, false, consumer);

		return consumer;
	}

	public Connection getConnection(String key) {
		return connectionMap.get(key);
	}

	public static void main(String[] args) {
		MqConnectionUtil util = MqConnectionUtil.getInstance();
	}

	public synchronized void closeConn(String key) {
		Connection conn = getConnection(key);
		if(conn != null && conn.isOpen()){
			try {
				connectionMap.remove(key);
				conn.close();
				conn = null;
			} catch (IOException e) {
				logger.error(null,e);
			}
		}
		
	}
}
