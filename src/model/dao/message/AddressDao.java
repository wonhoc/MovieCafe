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
	
	//메세지를 받을 사람들 조회
	public ArrayList<String> selectAddr(Connection conn, int sendMsgNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> receiveIds = new ArrayList<String>();
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT receive_id ");
			sql.append(" FROM address ");
			sql.append(" WHERE send_msg_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sendMsgNo);
			
			rs = pstmt.executeQuery();
			//rs에서 값 가져오기
			while(rs.next()) {
			String receiveId = rs.getString(1);
			
			receiveIds.add(receiveId);
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
		
		return receiveIds;
		
	}//selectMsg() end
	
}// class end
