package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import controller.ActionForward;
import controller.Command;
import domain.message.AddressVo;
import domain.message.SendMessageVo;
import model.service.message.MsgService;

public class SendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//게시글 쓰기 요청 커맨드
		
		//HttpSession session = request.getSession();
		//String writer = session.getAttribute("userId"); //로그인 구현시 실행될 구문
		request.setCharacterEncoding("utf-8"); //필터 꼭 나중에 추가하기
		
		String writer = request.getParameter("userid"); //테스트용
		String receiveId = request.getParameter("reciveId"); //받는사람 아이디
		String sendMsgContent = request.getParameter("sendMsgContent"); //발신한 쪽지 내용

		SendMessageVo msgVo = new SendMessageVo();
		ArrayList<String>addrs = new ArrayList<String>(); //받을사람들을 저장할 ArrayList
		
		
		msgVo.setWriterId(writer);	//작성자
		msgVo.setSendMsgContent(sendMsgContent); //전송할 메세지
		//나중에 반복문으로 바꾸기
		addrs.add(receiveId);
		
		msgVo.setAddress(addrs);
		
		MsgService service = MsgService.getInstance();
		
		service.registerMsg(msgVo);
		
		
		
		
		
		
		
		return new ActionForward("/message/sendMsgList.jsp", true);
		
		
		
		
	}//execute() end
}// class end
