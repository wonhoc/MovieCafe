package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.UserService;

public class CheckNickNameCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userNick = request.getParameter("userNick");
		UserService service = UserService.getInstance();
		
		boolean result = service.checkNickName(userNick);
		request.setAttribute("isUserNick", result);
		
		return new ActionForward("/member/isUserNick.jsp", false);
	}
}

