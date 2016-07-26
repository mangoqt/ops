package com.qt.xxtmonitor.constants;


public class ControlConfig {
	public static int corecountThread=6; //消息接收核心线程数
	public static int maxcountThread=12; //消息接收端最大线程数
	public static int  timeout=3000;    //单线程等待超时时间
    public static int  blockingQueueLength=12;    //线程池阻塞线程长度
    
    public static String redisHost="192.168.1.220";
    public static String  redisPWD="";
    public static int redisPort=6388;
    
    public static int maxActive=100;
    public static int maxIdle=20;
    public static int maxWait=100;
    
    public static  int minConnectionPoolSize = 1;//MQ连接池最小连接数
    public static  int connectionPoolSize= 50;//MQ连接池最大数
    public static  int statementPoolSize= 50;//连接池statement上限
    public static  int idleTimeout= 55;      //
    public static  int idleConnTimeout= 43200;//连接超时间
    public static  int shrinkInterval= 50;
    
    public static String  smsMqserver="192.168.1.163";    //MQ服务器
    public static String   queueNamePrefix="gd-sms-queue";
    public static String  smsMqPort="";    //MQ服务器
    public static String  smsMqACCOUNT="";    //MQ服务器
    public static String  smsMqPassword="";    //MQ服务器
	
	public static String CLUSTER_NAME="Test Cluster"; //列簇数据库
	public static String SERVER_IP="192.168.1.163:9160,192.168.1.163:9160";//列簇数据库ip
	public static String KEY_SPACE="gdxxtsms"; 
	
	public static int mainThreadSleeptime=5000;//主线程休眠时间
	
	public static String WEB_IP = "";
	
	public static int listenerPort=5678;
		   
}