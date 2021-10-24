package controller.member;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.UserService;

public class CheckIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		String ok = "default";
		try {
			String userId = req.getParameter("inputId");
			UserService service = UserService.getInstance();

			if(service.checkId(userId)) {
				ok = "중복된 아이디가 없습니다.";
			}else {
				ok = "아이디가 중복됩니다.";
			}//if end
			System.out.println(ok);
			req.setAttribute("ok", ok);
			return new ActionForward("/member/resultOk.jsp",false);
			
		}catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dis = req.getRequestDispatcher("/error");
			dis.forward(req, res);
			return null;
		}
		
		
		
		
		
		
		
		

	}//execute() end
	
}//class end
