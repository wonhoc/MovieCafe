
package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

	// 관리자가 회원정보를 조회한다.
	public ArrayList<UserInfoVo> selectUserList(int startRow, int postSize) throws Exception {
		ArrayList<UserInfoVo> users = new ArrayList<UserInfoVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT u.user_name, u.user_id,                                          ");
			sql.append("(SELECT COUNT(*) FROM report re  WHERE re.user_id  = u.user_id ) as report_count,    ");
			sql.append("rank_type, u.exit_type   ");
			sql.append("FROM user_info u JOIN rank r   ");
			sql.append("ON u.user_id = r.user_id   ");
			sql.append("LIMIT ? OFFSET ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, postSize);
			pstmt.setInt(2, startRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String userName = rs.getString(1);
				String userId = rs.getString(2);
				int reportCount = rs.getInt(3);
				String rankType = rs.getString(4);
				String exitType = rs.getString(5);
				users.add(new UserInfoVo(userName, userId, reportCount, rankType, exitType));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return users;
	}

	// 총 회원수를 구한다.
	public int selectUserTotalCount() throws Exception {
		int count = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*)   ");
			sql.append("FROM user_info");

			rs = stmt.executeQuery(sql.toString());
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return count;
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
			if (rs.next()) {
				isId = true;

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

			sql.append(
					"SELECT user_info.user_id, user_info.user_nick, rank.rank_type, user_info.photo_sys, user_info.joindate     ");
			sql.append("FROM user_info INNER JOIN rank                           		");
			sql.append("ON (user_info.user_id = rank.user_id) AND user_info.user_id = ?                  	");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				userInfoVo.setUserId(rs.getString(1));
				userInfoVo.setUserNick(rs.getString(2));
				userInfoVo.setRankType(rs.getString(3));
				userInfoVo.setPhotoSys(rs.getString(4));
				userInfoVo.setJoindate(rs.getString(5));
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

	// 회원 상세정보를 조회한다.
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
			if (rs.next()) {
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
		return user;
	}

	// 회원의 정보를 수정한다.
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

	// 자진탈퇴를 하면 탈퇴유형과 탈퇴날짜를 변경한다.
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

	// 닉네임중복검사
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
			if (rs.next()) {
				isNick = true;
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
		return isNick;
	}

	// 아이디 찾기 : 이름, 연락처를 넘겨주고 아이디를 받아옴
	public String forgetIdPwd(String userName, String userContact) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String returnUserId = "";

		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT user_id FROM user_info    ");
			sql.append("WHERE user_name = ?  and user_contact = ?   ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userName);
			pstmt.setString(2, userContact);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				returnUserId = rs.getString(1);
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
		return returnUserId;
	}

	// 비밀번호 찾기
	public String forgetPwd(String userId, String userContact, String userBirth) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String returnUserPwd = "";

		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT user_pwd FROM user_info   ");
			sql.append("WHERE user_id = ?  AND user_contact = ? AND user_birth = ?    ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userContact);
			pstmt.setString(3, userBirth);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				returnUserPwd = rs.getString(1);
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
		return returnUserPwd;

	}
	//랭크등록
	public void inserUserRank(String userId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO rank(user_id, rank_type)    ");
			sql.append("VALUE ( ?, 'N'   )   ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userId);
			
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
	
	
}
