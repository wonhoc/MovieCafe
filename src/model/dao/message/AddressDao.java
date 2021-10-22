package model.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.message.SendMessageVo;

public class AddressDao {
	
	private static AddressDao addrdao;
	
	private AddressDao() {}
	
	public static AddressDao getInstance() {
		if(addrdao == null){
			addrdao = new AddressDao();
		}//if end
		
		return addrdao;
	}//getInstance() end
	
	//등록한 쪽지 보낸 사람들 등록
	public void insertAddr(Connection conn, int sendMsgNo ,String receiveId) throws Exception {
		
		PreparedStatement pstmt = null;

		try {
		
		StringBuffer sql = new StringBuffer();
		
		//쪽지 정보 저장
		sql.append(" INSERT INTO address(send_msg_no, receive_id) ");
		sql.append(" VALUES (?, ?) ");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setInt(1, sendMsgNo);
		pstmt.setString(2, receiveId);
		
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
			
	}//insertAddr() end
	
}// class end
