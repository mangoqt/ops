package com.qt.xxtmonitor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class TestRedis {
	public static void main(String [] args){
		Jedis jedis = null;
		try {
			JedisShardInfo shardInfo = new JedisShardInfo("192.168.1.121", 6379);
//		shardInfo.setPassword("");
			shardInfo.setTimeout(1000);
			jedis = new Jedis(shardInfo);
			String value = jedis.get("test");
			System.out.println(value);
		} catch (JedisConnectionException e) {
			System.out.println(e.getMessage());
		}finally{
			if(jedis != null && jedis.isConnected()){
				jedis.disconnect();
			}
		}
	}
}
