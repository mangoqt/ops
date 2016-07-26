package raulstudy;
import java.io.*;

import com.rabbitmq.client.*;

public class Send {  
       private final static String QUEUE_NAME = "hello2";  
 
 
     public static void main(String[] args) throws Exception {  
    	 boolean durable=true;
       ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("183.60.136.253");  
       Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        channel.exchangeDeclare(QUEUE_NAME, "fanout", durable);
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null); 
        for(int i=0;i<100;i++){
        //	channel.queueDeclare(QUEUE_NAME, durable, false, false, null);  
        	String message = "Hello World!-----"+i;  
 	
			
        	channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());  
        	 System.out.println(" [x] Sent '" + message + "'"); 
        	Thread.sleep (100);
        	
        }
 
       
        channel.close();  
        
       connection.close();  
     }
     

}  
