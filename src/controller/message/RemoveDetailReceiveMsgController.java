package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class RemoveDetailReceiveMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//받은 쪽지 상세보기 페이지에서 삭제 요청
		try {
			 int receiveMsgNo = Integer.parseInt(request.getParameter("receiveMsgNo"));
			 System.out.println(receiveMsgNo);
			 //삭제
			 MsgService service = MsgService.getInstance();
			 service.removeReceiveMsg(receiveMsgNo);
			 
		} catch (Exception e) {
			throw e;
		}// end
		
		 
		
		return new ActionForward("/receiveMsgList.do", false);
		
	}//execute() end
}// class end
