package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.message.ReceiveMsgVo;
import model.dao.message.AddressDao;
import model.service.message.MsgService;

public class ReceivceMsgDetailController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//내게 온 쪽지 상세보기 요청
		//내게온 쪽지 번호
		int receiveMsgNo = Integer.parseInt(request.getParameter("receiveMsgNo"));
		System.out.println(receiveMsgNo);
		//메세지 확인 여부
		int isRead = Integer.parseInt(request.getParameter("isRead"));
		System.out.println(isRead);
		//address에 수신여부 바꿀 id
		String receiveId = request.getParameter("receiveId");
		System.out.println(receiveId);
		
		
		try {
			MsgService service = MsgService.getInstance(); //service 객체 생성
			
		
		//만약 쪽지를 확인하지 않았다면 DB에 확인정보 update
		if(isRead == 0) {
			service.updateRead(receiveMsgNo, receiveId);
		}//if end
		
		//글의 상세정보 가져오고 request영역에 바인딩
		ReceiveMsgVo rmv = service.retrieveReceiveMsg(receiveMsgNo);
		rmv.setReceiveMsgNo(receiveMsgNo); //받은 쪽지 번호
		request.setAttribute("receiveMsg", rmv);
		
		} catch (Exception e) {
			throw e;
			
		}//end
		
		
		return new ActionForward("/message/receiveMsgDetail.jsp", false);
		
	}//execute() end
}// class end
