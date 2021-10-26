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

	

	//총 회원수를 구한다.
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
	
	

}
