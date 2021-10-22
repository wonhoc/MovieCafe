package domain.message;

import java.util.ArrayList;

public class AddressVo {

	private int sendMsgno;
	private ArrayList<String> receieveId;
	
	
	public AddressVo() {
		super();
	}


	public AddressVo(int sendMsgno, ArrayList<String> receieveid) {
		super();
		this.sendMsgno = sendMsgno;
		this.receieveId = receieveid;
	}


	public int getSendMsgno() {
		return sendMsgno;
	}


	public void setSendMsgno(int sendMsgno) {
		this.sendMsgno = sendMsgno;
	}


	public ArrayList<String> getReceieveId() {
		return receieveId;
	}


	public void setReceieveId(ArrayList<String> receieveid) {
		this.receieveId = receieveid;
	}
	
	
	

	
	
}// class end
