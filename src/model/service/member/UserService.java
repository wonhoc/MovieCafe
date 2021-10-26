package model.service.member;

import java.sql.Connection;

import domain.member.UserInfoVo;
import model.DBConn;
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
		//ȸ�� ���� ��� ����
		public void registUser(UserInfoVo user) throws Exception {
			
			UserDao userDao = UserDao.getInstance();
			userDao.insertUser(user);
			userDao.inserUserRank(user.getUserId());
		}
		
		// ���̵� �ߺ��˻縦 �ϴ�.
		public boolean checkId(String userId) throws Exception {
				UserDao userDao = UserDao.getInstance();
				return userDao.existId(userId);

		}//checkId end
		
		
		// ȸ���� ���̵�, ȸ�����, �г����� ��ȸ�Ѵ�.
		public UserInfoVo retrieveIdRankNick(String userId) throws Exception {
				UserDao userDao = UserDao.getInstance();
				return userDao.selectUserIdNickRank(userId);
		}
		
		
		// �α���
		public int loginUser(UserInfoVo userInfoVo) throws Exception {
			
			try {
				UserDao userDao = UserDao.getInstance();
				
				int checkIdPwd = userDao.selectCountUser(userInfoVo);
				
				System.out.println("checkIdPwd = " + checkIdPwd);
				// checkIdPwd = 1 -> 
				// checkIdPwd = 0 -> �α��� �Ұ� = ���� ����
				return checkIdPwd;
				
			} catch (Exception e) {
				throw e;
			} 
	
		}
		
		//ȸ�� �������� ��ȸ�Ѵ�.
		public UserInfoVo retrieveUser(String userId) throws Exception {

			UserDao userDao = UserDao.getInstance();
			return userDao.selectUser(userId);
		}
		

		
		//ȸ���� ������ �����Ѵ�.
		public void modifyUser(UserInfoVo user) throws Exception {
			boolean isSuccess = false;
			Connection conn = null;
			try {
				conn = DBConn.getConnection();//�����ͺ��̽�
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
		//ȸ���� ����Ż�� �ϸ� Ż�������� Ż��¥�� ������Ʈ�Ѵ�.
		public void removeUser(String userId) throws Exception {
			boolean isSuccess = false;
			Connection conn = null;
			try {
				conn = DBConn.getConnection();//�����ͺ��̽�
				conn.setAutoCommit(false);

				UserDao userDao = UserDao.getInstance();
				userDao.deleteUser(userId, conn);

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
		//�г����ߺ��˻縦 �ϴ�.
		public boolean checkNickName(String userNick) throws Exception {
				UserDao userDao = UserDao.getInstance();
				return userDao.confirmNickName(userNick);
			
		}
		//아이디 찾기
		public String returnUserId(String userName, String userContact) throws Exception {
			UserDao userDao = UserDao.getInstance();
			System.out.println(userDao.forgetIdPwd(userName, userContact));
			return userDao.forgetIdPwd(userName, userContact);
			
		}
		//비밀번호 찾기
		public String returnUserPwd(String userId, String userContact, String userBirth) throws Exception {
			UserDao userDao = UserDao.getInstance();
			System.out.println(userDao.forgetPwd(userId, userContact, userBirth));
			return userDao.forgetPwd(userId, userContact, userBirth);
		}

		
	}