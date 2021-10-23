package domain.message;

import java.util.ArrayList;

public class SendMessageVo {
	private int sendMsgNo;
	private String sendMsgContent;
	private String msgWdate;
	public ArrayList<String> getAddress() {
		return address;
	}


	public void setAddress(ArrayList<String> address) {
		this.address = address;
	}


	private String writerId;
	private ArrayList<String> address;
	
	
	public SendMessageVo() {
		super();
	}


	public SendMessageVo(int sendMsgNo, String sendMsgContent, String msgWdate, String writerId,
			ArrayList<String> address) {
		super();
		this.sendMsgNo = sendMsgNo;
		this.sendMsgContent = sendMsgContent;
		this.msgWdate = msgWdate;
		this.writerId = writerId;
		this.address = address;
	}
	

	public int getSendMsgNo() {
		return sendMsgNo;
	}


	public void setSendMsgNo(int sendMsgNo) {
		this.sendMsgNo = sendMsgNo;
	}


	public String getSendMsgContent() {
		return sendMsgContent;
	}


	public void setSendMsgContent(String sendMsgContent) {
		this.sendMsgContent = sendMsgContent;
	}


	public String getMsgWdate() {
		return msgWdate;
	}


	public void setMsgWdate(String msgWdate) {
		this.msgWdate = msgWdate;
	}


	public String getWriterId() {
		return writerId;
	}


	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}



	
	
	
	
	
	
	
	
}// class end
