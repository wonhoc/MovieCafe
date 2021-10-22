package model.dao.member;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.member.UserInfoVo;

public class UserDao {
	// Singleton Pattern
	private static UserDao userDao;
	
	private UserDao() {
		
	}
	
	public static UserDao getInstance() {
		if(userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}
	
	// 회원 정보를 등록하다.
	public void insertUser(UserInfoVo userInfoVo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO user_info(user_id, user_pwd, user_nick, user_email,                 ");
			sql.append("user_birth, user_contact, gender, user_name,                          ");
			sql.append("photo_origin, photo_sys)                                                        ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)                        ");
			
			pstmt.setString(1, userInfoVo.getUserId());
			pstmt.setString(2, userInfoVo.getUserPwd());
			pstmt.setString(3, userInfoVo.getUserNick());
			pstmt.setString(4, userInfoVo.getUserEmail());
			pstmt.setString(5, userInfoVo.getUserBirth());
			pstmt.setString(6, userInfoVo.getUserContact());
			pstmt.setString(7, userInfoVo.getGender());
			
			pstmt.setString(8, userInfoVo.getUserName());
			pstmt.setString(9, userInfoVo.getPhotoOrigin());
			pstmt.setString(10, userInfoVo.getPhotoSys());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2){
				throw e2;
			}
				
		}
		
		
	}
	// 회원 정보를 조회한다.
	
	
}
