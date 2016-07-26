package raulstudy;

import java.io.*;

import com.rabbitmq.client.*;

public class Receiver {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		
		subTask s1= new subTask();
		subTask s2= new subTask();
		subTask s5= new subTask();
		subTask s3= new subTask();
		subTask s4= new subTask();
		
		s1.start();
		s2.start();
		s3.start();
		s4.start();
		s5.start();
		
	}

}

	class subTask extends Thread {
		subTask() {
		}// 构造函数

		public void run() {

			try {
				String threadid=this.getName();
				
				int k=1;
				 String QUEUE_NAME = "hello2";  
				ConnectionFactory factory = new ConnectionFactory();
				factory.setHost("183.60.136.253");
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();

				channel.queueDeclare(QUEUE_NAME, true, false, false, null);
				System.out
						.println(" Thread="+threadid+"  Waiting for messages.");
				QueueingConsumer consumer = new QueueingConsumer(channel);
				channel.basicQos(5);
				channel.basicConsume(QUEUE_NAME, false, consumer);
				while (k<5) {
					QueueingConsumer.Delivery delivery = consumer
							.nextDelivery();
			
					
				
					String message = new String(delivery.getBody());
					System.out.println("Thread="+threadid+"  Received '" + message + "' ,当前线程接收了:"+k);
					channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
					sleep(1000);
					k++;
				}
				

			} catch (Exception e) {
				return;
			}
			// 如果该run()方法顺序执行完了,线程将自动结束,而不会被主线程杀掉
			// 但如果休眠时间过长,则线程还存活,可能被stop()杀掉
		}
	}


