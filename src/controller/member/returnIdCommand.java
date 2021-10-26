package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.UserService;

public class returnIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userName = request.getParameter("userName");
		String userContact = request.getParameter("userContact");
		System.out.println(userName);
		System.out.println(userContact);
		
		UserService service = UserService.getInstance();
		
		String returnId = service.returnUserId(userName, userContact);
		System.out.println(returnId);
		request.setAttribute("returnId", returnId);
		return new ActionForward("/member/returnForgetId.jsp",false);
			
	}

}
