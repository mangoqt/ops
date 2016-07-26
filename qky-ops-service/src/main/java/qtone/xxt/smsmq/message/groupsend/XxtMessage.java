package qtone.xxt.smsmq.message.groupsend;

import java.io.Serializable;
import java.util.Date;

public class XxtMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6265693795066481764L;
	// 短信下行发送的目录手机
	private String mobile;
	// 短信内容
	private String content;
	// 发送端口后缀
	private String messagecode;
	// 短信请求提交时间
	private Date commitTime;
	// 消息请求出mq时的时间
	private Date outMqDateTime;
	// 进入短信明细消息队列时间
	private Date sendTime;
	//短信明细出消息队列时间
	private Date outMQFromCMPPDateTime;
	//输出短信明细的应用程序标识  0：消息处理程序 1：groupsend程序 2：CMPPServer程序 3：考勤分析程序 
	private int fromAppType;
	// 业务代码
	private String tranCode;
	// 企业代码
	private String spId;
	// 地区名称
	private String areaName;
	// sn
	private String sn;
	// 短信的类型 1：家校互动短信 2：教育OA短信 3：应急短信
	private int messageType;

	public String getMessagecode() {
		return messagecode;
	}

	public void setMessagecode(String messagecode) {
		this.messagecode = messagecode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public Date getOutMqDateTime() {
		return outMqDateTime;
	}

	public void setOutMqDateTime(Date outMqDateTime) {
		this.outMqDateTime = outMqDateTime;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public Date getOutMQFromCMPPDateTime() {
		return outMQFromCMPPDateTime;
	}

	public void setOutMQFromCMPPDateTime(Date outMQFromCMPPDateTime) {
		this.outMQFromCMPPDateTime = outMQFromCMPPDateTime;
	}

	public int getFromAppType() {
		return fromAppType;
	}

	public void setFromAppType(int fromAppType) {
		this.fromAppType = fromAppType;
	}

}
