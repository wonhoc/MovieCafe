package domain.message;

import java.util.ArrayList;

public class SendMessageVo {
	private String sendMsgContent;
	private String msgWdate;
	private String writerId;
	private ArrayList<AddressVo> Address;
	
	
	public SendMessageVo() {
		super();
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
	
	
	
	
	
	
}// class end
