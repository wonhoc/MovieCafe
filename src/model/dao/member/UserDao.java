package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.member.UserInfoVo;
import model.DBConn;

public class UserDao {
	private static UserDao userDao;

	private UserDao() {

	}

	public static UserDao getInstance() {
		if (userDao == null) {
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

			pstmt = conn.prepareStatement(sql.toString());

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
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}

		}

	}
  
  // 회원 아이디 중복 검사
	public boolean existId(String userId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isId = false;
		try {
			
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  *           ");
			sql.append("FROM user_info     ");
			sql.append("WHERE user_id = ?   ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isId = true;
				
			}
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return isId;
	}
  
  // 회원 아이디, 닉네임, 등급번호를 조회한다.
	public UserInfoVo selectUserIdNickRank(String userId) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		UserInfoVo userInfoVo = new UserInfoVo();
		
		try {
			
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT user_info.user_id, user_info.user_nick, rank.rank_type     ");
			sql.append("FROM user_info INNER JOIN rank                           		");
			sql.append("ON (user_info.rank_no = rank.rank_no) AND user_info.user_id = ?                  	");
			
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userInfoVo.setUserId(rs.getString(1));;
				userInfoVo.setUserNick(rs.getString(2));
				userInfoVo.setRankType(rs.getString(3));
				System.out.println(rs.getString(1));
			}
		
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		
		return userInfoVo;
	}
  
  
  
	// 로그인 아이디, 패스워드 Vo
	public int selectCountUser(UserInfoVo userInfoVo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int loginCheck = 0;

		try {
			conn = DBConn.getConnection();
			
				
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT COUNT( * ) FROM user_info        ");
			sql.append(" WHERE user_id = ? AND user_pwd = ?    ");

			pstmt = conn.prepareStatement(sql.toString());

			
			pstmt.setString(1, userInfoVo.getUserId());
			pstmt.setString(2, userInfoVo.getUserPwd());
			

			rs = pstmt.executeQuery();

			// 아이디와 패스워드가 일치하면 1, 아니면 0
			if (rs.next()) {
				loginCheck = rs.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				throw e2;
			}

		}

		return loginCheck;

	}
  
  //회원 상세정보를 조회한다.
	public UserInfoVo selectUser(String userId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoVo user = new UserInfoVo();
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT user_id, user_pwd, user_nick, user_email, user_birth,                 ");
			sql.append("user_contact, gender, user_name, photo_origin, photo_sys                    ");
			sql.append("FROM user_info  ");

			sql.append("WHERE user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getString(1));
				user.setUserPwd(rs.getString(2));
				user.setUserNick(rs.getString(3));
				user.setUserEmail(rs.getString(4));
				user.setUserBirth(rs.getString(5));
				user.setUserContact(rs.getString(6));
				user.setGender(rs.getString(7));
				user.setUserName(rs.getString(8));
				user.setPhotoOrigin(rs.getString(9));
				user.setPhotoSys(rs.getString(10));
			}
	
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return user;
	}

	
	//회원의 정보를 수정한다.
	public void updateUser(UserInfoVo user, Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE user_info                                       ");
			sql.append("SET  user_pwd = ?, user_nick = ?, user_email = ?,      ");
			sql.append("user_birth = ?, user_contact = ?, user_name = ?,       ");
			sql.append("photo_origin = ?, photo_sys = ?                        ");
			sql.append("WHERE user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, user.getUserPwd());
			pstmt.setString(2, user.getUserNick());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserBirth());
			pstmt.setString(5, user.getUserContact());
			pstmt.setString(6, user.getUserName());
			pstmt.setString(7, user.getPhotoOrigin());
			pstmt.setString(8, user.getPhotoSys());
			pstmt.setString(9, user.getUserId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	//자진탈퇴를 하면 탈퇴유형과 탈퇴날짜를 변경한다.
	public void deleteUser(String userId, Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE user_info                                       ");
			sql.append("SET  exit_type = 'S', exitDate = NOW() ");
			sql.append("WHERE user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, userId);

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	//닉네임중복검사
	public boolean confirmNickName(String userNick) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isNick = false;
		try {
			
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * ");
			sql.append("FROM user_info  ");
			sql.append("WHERE user_nick = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userNick);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isNick = true;
			}
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return isNick;
	}
}

