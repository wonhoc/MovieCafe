package model.service.message;

import java.sql.Connection;
import java.util.ArrayList;

import domain.message.ReceiveMsgVo;
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
				rmsdao.insertMessage(conn, msgVo.getSendMsgContent(), addr, sendMsgNo, msgVo.getWriterId());
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
	
	
	//내게 온 메세지 목록 조회
	public ArrayList<ReceiveMsgVo> retrieveReceiveMsgList(String userId) throws Exception {
		ArrayList<ReceiveMsgVo> receiveMsgList = new ArrayList<ReceiveMsgVo>(); //반환할 메세지 정보가 들은 ArrayList
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			ReceiveMSgDao receiveMsgDao = ReceiveMSgDao.getInstance(); //ReceiveMSgDao 객체생성
			//내게 온 메세지 조회 method
			receiveMsgList = receiveMsgDao.selectReceiveMsgList(conn, userId);
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(conn != null) conn.close();
		}// end
		
		return receiveMsgList;
	}//retrieveSendMsgList() end
	
	//내게 온 메세지 삭제
	public void removeReceiveMsg(int receiveMsgNo) throws Exception {
		Connection conn = null;
		
		try {
			//ReceiveMSgDao 객체 생성
			ReceiveMSgDao rmd = ReceiveMSgDao.getInstance();
			//내게온 메세지를 삭제
			rmd.deleteReceiveMsg(conn, receiveMsgNo);
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}// end
		
		
	}//removeReceiveMsg() end
	
	//읽음상태 읽음으로 바꾸고 내게온 메세지 삭제하기
	public void ReadUpdateRemove(int receiveMsgNo, String receiveId) throws Exception {
		Connection conn = null;
		boolean isSucess = false;	//트랜잭션 처리를 위한 값
		try {
		conn = DBConn.getConnection();
		conn.setAutoCommit(false); //트랜잭션 시작
		
		AddressDao addrDao = AddressDao.getInstance(); //AddressDao 객체 생성
		ReceiveMSgDao rmd = ReceiveMSgDao.getInstance(); //ReceiveMSgDao 객체 생성
		//수신확인 상태를 읽음으로 변경
		addrDao.Readed(conn, receiveMsgNo, receiveId);
		//삭제 요청한 내게 온 쪽지 번호 맞는 쪽지 삭제
		rmd.deleteReceiveMsg(conn, receiveMsgNo);
		
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
		}//end
		
	}//ReadUpdate() end
	
	//내게 온 메세지 상세 조회하고 읽음상태 update
		public ReceiveMsgVo retrieveReceiveMsg(int receiveMsgNo) throws Exception {	
			Connection conn = null;
			try {
				conn = DBConn.getConnection();
				conn.setAutoCommit(false); // 트랜잭션 시작
				ReceiveMSgDao receiveMsgDao = ReceiveMSgDao.getInstance(); //ReceiveMSgDao 객체생성
				//내게 온 메세지 조회 method
				ReceiveMsgVo rmv = receiveMsgDao.selectReceiveMsg(conn, receiveMsgNo);
				return rmv;
			} catch (Exception e) {
				throw e;
			}finally {
				try {
				if(conn != null) conn.close();
				} catch (Exception e2) {
				throw e2;
				}// end
			}// end
		}//retrieveSendMsg() end
	
	//읽음 상태만 true로 만듦
	public void updateRead(int sendMsgNo, String receiveId) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			AddressDao addrDao = AddressDao.getInstance();
			addrDao.Readed(conn, sendMsgNo, receiveId);
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {	
				if(conn != null) conn.close();			
			} catch(Exception e2) {
				throw e2;
			}// end
		} //end
	}//updateRead() end
	
	
	
}// class end
