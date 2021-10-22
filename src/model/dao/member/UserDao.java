package model.dao.member;

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
			sql.append("user_birth, user_contact, gender, exitdate, user_name,                          ");
			sql.append("photo_origin, photo_sys, board_count, com_count, exit_type, joindate, rank_no)  ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                        ");
			
			pstmt.setString(1, userInfoVo.getUserId());
			pstmt.setString(2, userInfoVo.getUserPwd());
			pstmt.setString(3, userInfoVo.getUserNick());
					
				
			
			
			
			
			
			
			
			
			
		}
		
		
	}
	
	
}
