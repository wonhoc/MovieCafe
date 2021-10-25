package model.service.member;

import java.sql.Connection;

import domain.member.UserInfoVo;
import model.DBConn;
import model.dao.member.UserDao;


public class UserService {
	// single pattern
		private static UserService service;

		private UserService() {

		}

		public static UserService getInstance() {
			if (service == null) {
				service = new UserService();
			}
			return service;
		}
	
	
	
	//회원 상세정보를 조회한다.
	public UserInfoVo retrieveUser(String userId) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.selectUser(userId);
	}
	
	//회원의 정보를 변경한다.
	public void modifyUser(UserInfoVo user) throws Exception {
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();//데이터베이스
			conn.setAutoCommit(false);

			UserDao userDao = UserDao.getInstance();
			userDao.updateUser(user, conn);

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	//회원이 자진탈퇴를 하면 탈퇴유형과 탈퇴날짜를 업데이트한다.
	public void removeUser(String userId) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();//데이터베이스
			UserDao userDao = UserDao.getInstance();
			userDao.deleteUser(userId, conn);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	//닉네임중복검사를 하다.
	public boolean checkNickName(String userNick) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.confirmNickName(userNick);
		
}
}
