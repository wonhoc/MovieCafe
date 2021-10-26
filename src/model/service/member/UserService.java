package model.service.member;

import java.sql.Connection;
import java.util.ArrayList;

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

	

	//관리자가 회원 정보 조회를 한다.
	public ArrayList<UserInfoVo> retrieveUserList(int startRow, int postSize) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.selectUserList(startRow, postSize);
	}

	

	// 총 회원의 수를 구한다.
	public int retrieveUserTotalCount() throws Exception {
		return UserDao.getInstance().selectUserTotalCount();
	}
}