package model.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.message.ReceiveMsgVo;
import domain.message.SendMessageVo;
import model.DBConn;


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
	public void insertMessage(Connection conn,String sendMsg ,String receiveId, int sendMsgNo, String writer) throws Exception {
		PreparedStatement pstmt = null;
		
		try {
		
		StringBuffer sql = new StringBuffer();
		
		//쪽지 정보 저장
		sql.append(" INSERT INTO receive_msg(receive_msg_content, receive_id, receive_msg_no, writer) ");
		sql.append(" VALUES (?, ?, ?, ?) ");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setString(1, sendMsg);
		pstmt.setString(2, receiveId);
		pstmt.setInt(3, sendMsgNo);
		pstmt.setString(4, writer);
		
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
	
	//받은 쪽지 목록 조회
	public ArrayList<ReceiveMsgVo> selectReceiveMsgList(Connection conn, String userId, int startRow, int postSize) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReceiveMsgVo> receiveMsgList = new ArrayList<ReceiveMsgVo>(); //반환할 ArrayList객체	
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT receive_msg_no, writer, receive_msg_content, msg_wdate, is_read ");
			sql.append(" FROM receive_msg ");
			sql.append(" WHERE receive_id = ? ");
			sql.append(" ORDER BY msg_wdate DESC ");
			sql.append(" LIMIT ? OFFSET ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReceiveMsgVo receiveMsgVo = new ReceiveMsgVo();
				
				receiveMsgVo.setReceiveMsgNo(rs.getInt(1));
				receiveMsgVo.setWriterId(rs.getString(2));
				receiveMsgVo.setReceiveMsgContent(rs.getString(3));
				receiveMsgVo.setMsgWdate(rs.getString(4));
				receiveMsgVo.setIsRead(rs.getInt(5));
				//반환할 ArrayList에 add
				receiveMsgList.add(receiveMsgVo);
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
		return receiveMsgList;
			
	}//selectReceiveMsgList() end
	
	//삭제할 메세지를 삭제하는 method
	public void deleteReceiveMsg(Connection conn, int receiveMsgNo) throws Exception{
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM receive_msg ");
			sql.append(" WHERE receive_msg_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, receiveMsgNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				
			} catch (Exception e2) {
				throw e2;
			}
		}// end
		
	}//deleteReceiveMsg() end
	
		//받은 쪽지  조회
		public ReceiveMsgVo selectReceiveMsg(Connection conn, int receiveMsgNo) throws Exception{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReceiveMsgVo receiveMsgVo = new ReceiveMsgVo();
			try {
				StringBuffer sql = new StringBuffer();
				
				sql.append(" SELECT writer, receive_msg_content, msg_wdate");
				sql.append(" FROM receive_msg ");
				sql.append(" WHERE receive_msg_no = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setInt(1, receiveMsgNo);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					receiveMsgVo.setWriterId(rs.getString(1));
					receiveMsgVo.setReceiveMsgContent(rs.getString(2));
					receiveMsgVo.setMsgWdate(rs.getString(3));
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
			
			return receiveMsgVo;
			
		}//selectReceiveMsg() end
		
		//받은 모든 쪽지 수를구하기
		public int selectTotalReceiveMsg(Connection conn ,String userId) throws Exception {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;		
			try {

				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT COUNT( * ) ");
				sql.append(" FROM receive_msg ");
				sql.append(" WHERE receive_id = ? ");
				
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
		
		//받는 아이디 갯수 조회하기
		public int selectId(String userId) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				int resultCount = 0;
				conn = DBConn.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT COUNT(user_Id) ");
				sql.append(" FROM user_info ");
				sql.append(" WHERE user_Id = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, userId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					resultCount = rs.getInt(1);
				}//if end
				
				return resultCount;

			} catch (Exception e) {
				throw e;
			}finally {
				try {
					
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (Exception e2) {
					throw e2;
				}
			}// end
			
			
		}//selectId() end
		
	
}// class end
