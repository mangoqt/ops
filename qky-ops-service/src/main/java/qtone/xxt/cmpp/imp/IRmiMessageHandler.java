package qtone.xxt.cmpp.imp;

import java.rmi.Remote;
import java.util.List;

import qtone.xxt.smsmq.message.groupsend.XxtMessage;

public interface IRmiMessageHandler extends Remote{

	/**
	 * 远程推送信息
	 * @param list
	 */
	public void pushMessage(List<XxtMessage> list);
	
}
