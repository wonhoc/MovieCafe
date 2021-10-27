package domain.message;

public class AddressVo {

	private int sendMsgno;
	private String receiveId;
	
	
	public AddressVo() {
		super();
	}


	public AddressVo(int sendMsgno, String receiveId) {
		super();
		this.sendMsgno = sendMsgno;
		this.receiveId = receiveId;
	}
	
	//get set

	public int getSendMsgno() {
		return sendMsgno;
	}


	public void setSendMsgno(int sendMsgno) {
		this.sendMsgno = sendMsgno;
	}


	public String getReceiveId() {
		return receiveId;
	}


	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}


	
	
	

	
	
}// class end
