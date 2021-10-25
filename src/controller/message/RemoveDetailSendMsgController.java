package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class RemoveDetailSendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//보낸 쪽지 상세보기 페이지에서 삭제 요청
		try {
			 int sendMsgNo = Integer.parseInt(request.getParameter("sendMsgNo"));
			 System.out.println(sendMsgNo);
			 //삭제
			 MsgService service = MsgService.getInstance();
			 service.removeSendMsg(sendMsgNo);
			 
		} catch (Exception e) {
			throw e;
		}// end
		
		 
		
		return new ActionForward("/sendMsgList.do", false);
		
	}//execute() end
}// class end
