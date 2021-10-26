package domain.message;

public class ReceiveMsgVo {
	private int receiveMsgNo;
	private String writerId;
	private String receiveMsgContent;
	private String msgWdate;
	private int isRead;
	
	
	
	




	public int getIsRead() {
		return isRead;
	}




	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}




	public ReceiveMsgVo() {
		super();
	}




	public int getReceiveMsgNo() {
		return receiveMsgNo;
	}




	public void setReceiveMsgNo(int receiveMsgNo) {
		this.receiveMsgNo = receiveMsgNo;
	}




	public String getWriterId() {
		return writerId;
	}




	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}




	public String getReceiveMsgContent() {
		return receiveMsgContent;
	}




	public void setReceiveMsgContent(String receiveMsgContent) {
		this.receiveMsgContent = receiveMsgContent;
	}




	public String getMsgWdate() {
		return msgWdate;
	}




	public void setMsgWdate(String msgWdate) {
		this.msgWdate = msgWdate;
	}






	
	
	
	
	
}// class end
