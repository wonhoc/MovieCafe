package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class SendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("sendMsgContent",request.getParameter("sendMsgContent"));
		
		request.setAttribute("recieveId", request.getParameter("recieveId"));
		
		request.setAttribute("sos", "zzzzzzz");
		
		return new ActionForward("/SendMsgList.jsp", true);
		
	}//execute() end
}// class end
