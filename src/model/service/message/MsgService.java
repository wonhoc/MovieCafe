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
	public void registerMsg(SendMessageVo msgVo) throws Exception {
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
		
		
	}//registerMsg() end
	
	//내가 쓴 쪽지 조회하기
	public ArrayList<SendMessageVo> retrieveSendMsgList(String userId) throws Exception {
		ArrayList<SendMessageVo> sendMsgList = new ArrayList<SendMessageVo>(); //반환할 메세지 정보가 들은 ArrayList
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao 객체생성
			//메세지 조회 method
			sendMsgList = sendmsgDao.selectSendmsg(conn, userId);
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(conn != null) conn.close();
		}// end
		
		return sendMsgList;
	}//retrieveSendMsgList() end
	
	//쪽지정보 삭제하고 보낸 주소록 삭제
	public void removeMsg(int sendMsgNo) throws Exception {
		Connection conn = null;
		boolean isSucess = false;	//트랜잭션 처리를 위한 값
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false); //트랜잭션 시작
			
			//주소록에서 삭제
			AddressDao addrDao = AddressDao.getInstance(); //AddressDao 객체생성
			addrDao.deleteAddress(conn, sendMsgNo);
			
			
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao 객체생성
			//내가쓴 쪽지 정보 삭제
			sendmsgDao.deleteSendMsg(conn, sendMsgNo);
			
			
			
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
		
	}//removeMsg() end
	
	//내가 쓴 쪽지, 수신여부 조회하기
	public SendMessageVo retrieveSendMsg(int sendMsgNo) throws Exception {
		SendMessageVo sendMs = new SendMessageVo(); //반환할 메세지 정보가 들은 객체
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao 객체생성
			//메세지 조회 method
			sendMs = sendmsgDao.selectSendMsg(conn, sendMsgNo);
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(conn != null) conn.close();
		}// end
		
		return sendMs;
	}//retrieveSendMsg() end
	
	
	
}// class end
