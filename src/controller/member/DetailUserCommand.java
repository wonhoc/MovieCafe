package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import model.service.member.UserService;

public class DetailUserCommand implements Command {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("call");
		
		// 회원정보 상세조회 요청 처리 커맨드
		String userId = req.getParameter("userId");
		UserService service = UserService.getInstance();
		UserInfoVo user = service.retrieveUser(userId);
		System.out.println(user);
		
		
		req.setAttribute("user", user);		

		ActionForward forward = new ActionForward("/indexControl.jsp?contentTemplate=member/modifyUserForm", false);
		return forward;

	}
}