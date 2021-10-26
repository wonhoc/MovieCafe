package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieInfoVo;

public class JoinUserFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ActionForward("/indexControl.jsp?contentTemplate=member/joinUserForm", true);
	}

}
