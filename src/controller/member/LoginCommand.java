package controller.member;

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
		
				UserService service = UserService.getInstance();
				
				UserInfoVo userInfoVo = new UserInfoVo(userId, userPwd);
			
				int isMember = service.loginUser(userInfoVo);
				System.out.println(isMember);
				
				if(isMember == 1) {
					HttpSession session = request.getSession();
					UserInfoVo user = service.retrieveIdRankNick(userId);				
					
					session.setAttribute("userInfo", user);
					System.out.println(user);
					session.setMaxInactiveInterval(30 * 60);
					System.out.println("성공");
					return new ActionForward("/main.do", true);					
					
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
		
	}

}
