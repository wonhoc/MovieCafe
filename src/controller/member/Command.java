package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception;

}
