package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class SendMsgRemoveController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//메세지 삭제 요청
		try {
		MsgService service = MsgService.getInstance();
		//삭제 요청한 쪽지 번호
		String[] removeNos = request.getParameterValues("removeCheckBox");
		for(String no : removeNos) {
		int sendMsgNo = Integer.parseInt(no);
		service.removeMsg(sendMsgNo);
		}//for end
		
		} catch (Exception e) {
			throw e;
		}//end
		
		return new ActionForward("/sendMsgList.do", true);
		
	}//execute() end
}// class end
