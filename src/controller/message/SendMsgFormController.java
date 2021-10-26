package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class SendMsgFormController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//메세지 보내기 폼 요청
		return new ActionForward("/message/sendMsgForm.jsp", false);
		
	}//execute() end
}// class end
