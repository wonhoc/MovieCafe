package domain.message;

import java.util.ArrayList;

public class SendMessageVo {
	private String sendMsgContent;
	private String msgWdate;
	private String writerId;
	private ArrayList<String> Address;
	
	
	public SendMessageVo() {
		super();
	}

	
	
	public SendMessageVo(String sendMsgContent, String msgWdate, String writerId, ArrayList<String> address) {
		super();
		this.sendMsgContent = sendMsgContent;
		this.msgWdate = msgWdate;
		this.writerId = writerId;
		Address = address;
	}



	//get set
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



	public ArrayList<String> getAddress() {
		return Address;
	}



	public void setAddress(ArrayList<String> address) {
		Address = address;
	}
	
	
	
	
	
	
	
}// class end
