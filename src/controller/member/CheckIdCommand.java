package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.UserService;

public class CheckIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String userId = request.getParameter("userId");
			UserService service = UserService.getInstance();
			
			boolean result2 = service.checkId(userId);
			request.setAttribute("isUserId", result2);
			return new ActionForward("/member/isUserId.jsp", false);
	}

	
	
}//class end
