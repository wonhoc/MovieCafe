package domain.message;

import java.util.ArrayList;

public class SendMessageVo {
	private int sendMsgNo;
	private String sendMsgContent;
	private String msgWdate;
	private String writerId;
	private ArrayList<String> address;
	private ArrayList<Integer>isRead;
	
	
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
	
	public ArrayList<Integer> getIsRead() {
		return isRead;
	}


	public void setIsRead(ArrayList<Integer> isRead) {
		this.isRead = isRead;
	}
	
	public ArrayList<String> getAddress() {
		return address;
	}


	public void setAddress(ArrayList<String> address) {
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


	@Override
	public String toString() {
		return "SendMessageVo [sendMsgNo=" + sendMsgNo + ", sendMsgContent=" + sendMsgContent + ", msgWdate=" + msgWdate
				+ ", writerId=" + writerId + ", address=" + address + "]";
	}



	
	
	
	
	
	
	
	
}// class end
