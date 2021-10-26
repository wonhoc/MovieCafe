package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

public class LogoutCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			return new ActionForward("/main.do", true);
		
	}
	

}
