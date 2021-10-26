package model.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.message.SendMessageVo;

public class SendMsgDao {
	
	private static SendMsgDao sendmsgdao;
	
	private SendMsgDao() {}
	
	public static SendMsgDao getInstance() {
		if(sendmsgdao == null){
			sendmsgdao = new SendMsgDao();
		}//if end
		
		return sendmsgdao;
	}//getInstance() end
	
	//내가 쓴 쪽지 등록
	public int insertMessage(Connection conn, SendMessageVo msgVo) throws Exception {
		int sendMsgNo = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		
		StringBuffer sql = new StringBuffer();
		
		//쪽지 정보 저장
		sql.append(" INSERT INTO send_msg(send_msg_content, writer_id) ");
		sql.append(" VALUES (?, ?) ");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setString(1, msgVo.getSendMsgContent());
		pstmt.setString(2, msgVo.getWriterId());
		
		pstmt.executeUpdate();
		pstmt.close();
		
		//방금 저장된 쪽지 번호 가져오기.
		stmt = conn.createStatement();
		sql.delete(0, sql.length());
		sql.append(" SELECT LAST_INSERT_ID() ");
		rs = stmt.executeQuery(sql.toString());
		
		if(rs.next()) {
			sendMsgNo = rs.getInt(1);
		}//if end
		
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (Exception e2) {
				throw e2;
			}//end
		}// end
		
		return sendMsgNo;
		
	}//insertMessage() end
	
	//내가 쓴 쪽지 조회하는 method
	public ArrayList<SendMessageVo> selectSendmsg(Connection conn, String userId, int startRow, int postSize) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SendMessageVo> sendMsgs = new ArrayList<SendMessageVo>();
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT send_msg_no, send_msg_content, msg_wdate ");
			sql.append(" FROM send_msg ");
			sql.append(" WHERE writer_id = ? ");
			sql.append(" ORDER BY msg_wdate DESC ");
			sql.append(" LIMIT ? OFFSET ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);
			
			rs = pstmt.executeQuery();
			//rs에서 값 가져오기
			while(rs.next()) {
				SendMessageVo sendMsg = new SendMessageVo();
				int sendMsgNo = rs.getInt(1);
				sendMsg.setSendMsgNo(sendMsgNo);
				
				//주소록 추가하기
				AddressDao addrDao = AddressDao.getInstance();
				sendMsg.setAddress( addrDao.selectAddr(conn, sendMsgNo));
				sendMsg.setSendMsgContent(rs.getString(2));
				sendMsg.setMsgWdate(rs.getString(3));
				
				//반환할 ArrayList에 add
				sendMsgs.add(sendMsg);
			}//while end
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}// end
		}// end
		
		return sendMsgs;
		
	}//selectMsg() end
	
	//받은 쪽지 삭제하기
	public void deleteSendMsg(Connection conn, int SendMsgNo)throws Exception {
		
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM send_msg ");
			sql.append(" WHERE send_msg_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());	
			pstmt.setInt(1, SendMsgNo);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}// end
		}// end

	}//deleteSendMsg()
	
	
	//쪽지 상세조회
	public SendMessageVo selectSendMsg(Connection conn, int sendMsgNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			SendMessageVo sendMsg = new SendMessageVo();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT send_msg_content, msg_wdate, writer_id ");
			sql.append(" FROM send_msg ");
			sql.append(" WHERE send_msg_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sendMsgNo);
			
			rs = pstmt.executeQuery();
			//rs에서 값 가져오기
			while(rs.next()) {		
				//주소록 추가하기
				AddressDao addrDao = AddressDao.getInstance();
				sendMsg.setAddress(addrDao.selectAddr(conn, sendMsgNo));
				//조회여부 추가하기
				sendMsg.setIsRead(addrDao.selectIsread(conn, sendMsgNo));
				sendMsg.setSendMsgContent(rs.getString(1));
				sendMsg.setMsgWdate(rs.getString(2));
				sendMsg.setWriterId(rs.getString(3));
			}//while end
			return sendMsg;
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}// end
		}// end

	}//selectSendMsg() end
	
	//보낸 모든 쪽지 수를구하기
	public int selectTotalSendMsg(Connection conn ,String userId) throws Exception {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT COUNT(*) ");
			sql.append(" FROM send_msg ");
			sql.append(" WHERE writer_id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}//if end
			
			
			
		} catch (Exception e) {
			throw e;
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}// end
		}// end
		return count;
	}//selectTotalReceiveMsg() end
	
	
	
	
	
}// class end
