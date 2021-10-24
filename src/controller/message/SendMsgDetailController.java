package controller.message;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.message.SendMessageVo;
import model.service.message.MsgService;

public class SendMsgDetailController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//내가보낸 메세지 상세보기 요청
		try {
		 int sendMsgNo = Integer.parseInt(request.getParameter("sendMsgNo"));
		 MsgService service = MsgService.getInstance();
		 //상세보기 정보가 담겨있는 객체
		 SendMessageVo smv = service.retrieveSendMsg(sendMsgNo);
		 request.setAttribute("sendMsg", smv);
		 
	
		} catch (Exception e) {
		//오류시 오류페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
		dispatcher.forward(request, response);
		return null;
		}//end
		
		return new ActionForward("/message/sendMsgDetail.jsp", false);
	}//execute() end
}// class end
