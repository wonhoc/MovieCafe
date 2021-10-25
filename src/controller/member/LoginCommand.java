package controller.member;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import model.service.member.UserService;

public class LoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			try {
				String userId = request.getParameter("userId");
				String userPwd = request.getParameter("userPwd");
				
				System.out.println("userId : " + userId);
				System.out.println("userPwd : " + userPwd);
				
				
				// 로그인 확인 부분
				UserService service = UserService.getInstance();
				
				UserInfoVo userInfoVo = new UserInfoVo(userId, userPwd);
				// 회원, 아이디가 있으면 1, 없으면 0
				int isMember = service.loginUser(userInfoVo);
				System.out.println(isMember);
				
				// 로그인 확인 부분 끝				
				
				// 계정이 있을 경우 세션에서 회원 정보 가지고 옴
				if(isMember == 1) {
					HttpSession session = request.getSession();
					UserInfoVo user = service.retrieveIdRankNick(userId);
					
					
					session.setAttribute("userInfo", user);
					
					session.setMaxInactiveInterval(30 * 60);
					System.out.println("성공");
					return new ActionForward("/indexControl.jsp?contentTemplate=main", false);
					
					
				} else {
					System.out.println("실패");
					return new ActionForward("/indexControl.jsp?contentTemplate=main", false);
					
				}				
				
				
			} catch(Exception e) {
				e.getStackTrace();
				request.setAttribute("exception", e);
				RequestDispatcher dis = request.getRequestDispatcher("");
				dis.forward(request, response);
				return null;
				
			}
			// 세션 끝.
	}

}
