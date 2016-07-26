package com.qt.core.util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import qtone.xxt.cmpp.imp.IRmiMessageHandler;
import qtone.xxt.smsmq.message.groupsend.XxtMessage;

public class SmsUtil {
	private static SmsUtil smsUtil = new SmsUtil();
	private static Log logger = LogFactory.getLog(SmsUtil.class);
	
	IRmiMessageHandler iRmi;
	String lookupURL;
	
	private SmsUtil(){
		lookupURL = "rmi://"+ PropHandler.get("rmi.ip")+":"+PropHandler.get("rmi.port")+"/SendMsg";
	}
	
	public static SmsUtil getInstance(){
		return smsUtil;
	}
	
	public void sendMonitorMessage(List<XxtMessage> list){
		try {
			iRmi = (IRmiMessageHandler) Naming.lookup(lookupURL);
		} catch (MalformedURLException e) {
			logger.error(null,e);
		} catch (RemoteException e) {
			logger.error(null,e);
		} catch (NotBoundException e) {
			logger.error(null,e);
		}
		iRmi.pushMessage(list);
	}
	
	public void sendMonitorMessage(XxtMessage message){
		try {
			iRmi = (IRmiMessageHandler) Naming.lookup(lookupURL);
		} catch (MalformedURLException e) {
			logger.error(null,e);
		} catch (RemoteException e) {
			logger.error(null,e);
		} catch (NotBoundException e) {
			logger.error(null,e);
		}
		List<XxtMessage> list = new ArrayList<XxtMessage>();
		list.add(message);
		iRmi.pushMessage(list);;
	}
}
