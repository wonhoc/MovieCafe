package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import model.service.member.UserService;





public class PwdCheckCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		HttpSession session = req.getSession();
		String userId=(String)session.getAttribute("userId");
		String userPwd = req.getParameter("userPwd");
		UserService service = UserService.getInstance();
		UserInfoVo user = service.retrieveUser(userId);
		
		boolean result = user.getUserPwd().equals(userPwd);
		if(result) {
			return new ActionForward("/indexControl.jsp?contentTemplate=member/userDelete", false);
		}else {
			return new ActionForward("/indexControl.jsp?contentTemplate=member/pwdCheck", false); 
		}
		
		
		
	}
	
}
