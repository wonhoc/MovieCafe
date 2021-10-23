package model.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import domain.message.SendMessageVo;

public class ReceiveMSgDao {
	
	private static ReceiveMSgDao receovemsgdao;
	
	private ReceiveMSgDao() {}
	
	public static ReceiveMSgDao getInstance() {
		if(receovemsgdao == null){
			receovemsgdao = new ReceiveMSgDao();
		}//if end
		
		return receovemsgdao;
	}//getInstance() end
	
	//보내온 쪽지 저장
	public void insertMessage(Connection conn,String sendMsg ,String receiveId, int sendMsgNo) throws Exception {
		PreparedStatement pstmt = null;
		
		try {
		
		StringBuffer sql = new StringBuffer();
		
		//쪽지 정보 저장
		sql.append(" INSERT INTO receive_msg(receive_msg_content, receive_id, receive_msg_no) ");
		sql.append(" VALUES (?, ?, ?) ");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setString(1, sendMsg);
		pstmt.setString(2, receiveId);
		pstmt.setInt(3, sendMsgNo);
		
		pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}//end
		}// end
			
	}//insertMessage() end
	
}// class end
