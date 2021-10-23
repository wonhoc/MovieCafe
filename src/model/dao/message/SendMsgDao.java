package model.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	public SendMessageVo selectSendmsg(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT  ");
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}// end
		
		
	}//selectMsg() end
	
}// class end
