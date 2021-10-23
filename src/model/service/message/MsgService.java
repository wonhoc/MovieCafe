package model.service.message;

import java.sql.Connection;
import java.util.ArrayList;

import domain.message.SendMessageVo;
import model.DBConn;
import model.dao.message.AddressDao;
import model.dao.message.ReceiveMSgDao;
import model.dao.message.SendMsgDao;

public class MsgService {
	//싱글톤패턴 
	private static MsgService msgservice;
	
	private MsgService() {
		
	}
	
	public static MsgService getInstance() {
		if(msgservice == null) {
			msgservice = new MsgService();
		}//if end
		return msgservice;
	}//getInstance() end
	
	//쪽지 정보 등록하기
	public void retrieveMsg(SendMessageVo msgVo) throws Exception {
		ArrayList<String> addrs = new ArrayList<String>();	//받을사람들을 저장할 ArrayList
		Connection conn = null;
		boolean isSucess = false;	//트랜잭션 처리를 위한 값
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false); //트랜잭션 시작
			
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao 객체생성
			//내가쓴 쪽지 정보 DB에 저장
			int sendMsgNo = sendmsgDao.insertMessage(conn, msgVo);
			
			
			//보낸 사람들을 저장하기
			addrs = msgVo.getAddress();
			AddressDao addrDao = AddressDao.getInstance(); //AddressDao 객체생성
			
			for(String addr : addrs) {
				addrDao.insertAddr(conn, sendMsgNo, addr);
			}//for end
			
			//받는사람 편지함에 쪽지 등록하기
			ReceiveMSgDao rmsdao = ReceiveMSgDao.getInstance(); //ReceiveMSgDao 객체생성
			for(String addr : addrs) {
				rmsdao.insertMessage(conn, msgVo.getSendMsgContent(), addr, sendMsgNo);
			}//for end
			
			isSucess = true;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) {
					if(isSucess) {
						conn.commit();
						System.out.println("됨");
					}else{
						conn.rollback();
						System.out.println("안됨");
					}//if end
					conn.close();
				}//if end
			} catch (Exception e2) {
				throw e2;
			}// end
		
		}// end
		
		
	}//retriveMsg() end
	
	
	
	
}// class end
