package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.message.SendMessageVo;
import model.dao.message.AddressDao;
import model.service.message.MsgService;

public class SendMsgListController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		 //세션에서 id정보 가져오기 
		//HttpSession session = request.getSession();
		//String userId = session.getParameter("userId");
		
		//로컬에서 테스트하기
		//String userId = request.getParameter("userId");
		String userId = "test_user01";
		
		MsgService service = MsgService.getInstance();
		//내가쓴 메세지 정보들 객체
		 ArrayList<SendMessageVo> sendMsgList = service.retrieveSendMsgList(userId); 
		
		//request영역에 sendMsgList이름으로 내가쓴 정보들 객체 바인딩
		request.setAttribute("sendMsgList", sendMsgList);
		
		return new ActionForward("/message/sendMsgList.jsp", false);
		
	}//execute() end
}// class end
