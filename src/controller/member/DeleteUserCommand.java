package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.service.member.UserService;

public class DeleteUserCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userId=(String)session.getAttribute("userId");
		UserService service = UserService.getInstance();
		service.removeUser(userId);
		
		session.invalidate();
		
		ActionForward forward = new ActionForward("/main.do", false);
		return forward;
	}
	
}
