package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.message.ReceiveMsgVo;
import model.service.message.MsgService;

public class ReceivceMsgListController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//받은 쪽지 목록 요청
		//로그인 기능 구현되면 
		//HttpSession session = request.getSession();
		//String userId = (String)session.getAttribute("userId");
		//로컬에서 테스트
		String userId = "test_user02";
		
		//MsgService 객체 생성
		MsgService service = MsgService.getInstance();
		//내게 온 메세지 목록 조회 method 호출
		ArrayList<ReceiveMsgVo>	receiveMsgList =  service.retrieveReceiveMsgList(userId);
		
		//request영역에 receiveMsgList이름으로 내게온 쪽지 정보 바인딩
		request.setAttribute("userId", userId);
		request.setAttribute("receiveMsgList", receiveMsgList);

		return new ActionForward("/message/receiveMsgList.jsp", false);
		
	}//execute() end
}// class end
