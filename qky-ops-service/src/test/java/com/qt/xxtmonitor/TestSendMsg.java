package com.qt.xxtmonitor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import qtone.xxt.cmpp.imp.IRmiMessageHandler;
import qtone.xxt.smsmq.message.groupsend.XxtMessage;

public class TestSendMsg {

	public static void main(String[] args) {
		String lookupURL = "rmi://"+ "192.168.210.74"+":10990/SendMsg";
//		String lookupURL = "rmi://"+ "localhost"+":10990/SendMsg";
		IRmiMessageHandler iRmi;
		List<XxtMessage> list = new ArrayList<XxtMessage>();
		try {
			iRmi = (IRmiMessageHandler) Naming.lookup(lookupURL);
			for(int i=0;i<1;i++){
				XxtMessage xxtMessage = new XxtMessage();
				xxtMessage.setContent("短信测试内容1"+i);
				xxtMessage.setMobile("13512719456");
				xxtMessage.setMessagecode("22");
				xxtMessage.setFromAppType(0);
				xxtMessage.setOutMQFromCMPPDateTime(new Date());
				list.add(xxtMessage);
//				if(i%1000 == 0){
					iRmi.pushMessage(list);
					System.out.println("发送："+xxtMessage.getMobile());
//					list.clear();
//				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
