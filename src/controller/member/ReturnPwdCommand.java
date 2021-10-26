package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.UserService;

public class ReturnPwdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("inputUserId2");
		String userContact = request.getParameter("inputUserCon2");
		String userBirth = request.getParameter("inputUserBir2");
		
		
		System.out.println(userId);
		System.out.println(userContact);
		System.out.println(userBirth);
		
		UserService service = UserService.getInstance();
		
		String returnPwd = service.returnUserPwd(userId, userContact, userBirth);
		request.setAttribute("returnPwd", returnPwd);
		System.out.println(returnPwd);
		return new ActionForward("member/returnForgetPwd.jsp", false);
	}
	
	

}
