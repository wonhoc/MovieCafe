package model.service.member;

import java.sql.Connection;

import domain.BoardFileVo;
import domain.member.UserInfoVo;
import model.dao.member.DBConn;
import model.dao.member.UserDao;

public class UserService {
	
	private static UserService service;
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
		}
		return service;
	}
	//유저 정보를 등록하다.
		public void registUser(UserInfoVo user) throws Exception {
			Connection conn = null;
			boolean isSuccess = false;
			
			try {
				conn = DBConn.getConnection();
				conn.setAutoCommit(false);
				
				//1. 회원 정보 등록
				UserDao userDao = UserDao.getInstance();
				userDao.insertUser(user);
				
				//2. 파일 정보 등록
				BoardFileDao fileDao = boardFileDao.getInstance();
				
				for (BoardFileVo file : user.getFileList()) {
					fileDao.insertBoardFile(file, conn);
				}
				isSuccess = true;
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if(conn != null) {
						if(isSuccess) {
							conn.commit();
						} else {
							conn.rollback();
						}
						conn.close();
					}
				} catch (Exception ex) {
					throw ex;
				}
			}
			
			
		}
	
	
	
	
}

	
