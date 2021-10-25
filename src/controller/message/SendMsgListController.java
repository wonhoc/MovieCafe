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
	public static final int POST_PER_PAGE = 5;
	public static final int PAGE_BLOCK = 3;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		 //세션에서 id정보 가져오기 
		//HttpSession session = request.getSession();
		//String userId = session.getParameter("userId");
		
		//로컬에서 테스트하기
		//String userId = request.getParameter("userId");
		String userId = "test_user01";
		
		//페이징 처리 구현
		//1. 현재 페이지 번호 가져오기
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}// end
		
		//2. 현재 페이지에 보여줄 게시글의 시작 행 번호를 구한다.
		int startRow = (currentPage -1) * POST_PER_PAGE;
		
		
		MsgService service = MsgService.getInstance();
		//내가쓴 메세지 정보들 객체
		 ArrayList<SendMessageVo> sendMsgList = service.retrieveSendMsgList(userId, startRow, POST_PER_PAGE); 
		
		//request영역에 sendMsgList이름으로 내가쓴 정보들 객체 바인딩
		request.setAttribute("sendMsgList", sendMsgList);
		
		//3. Block을 구한다.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1 ;
		
		//4. 현재 페이지가 속한 페이지 블록의 시작 번호와 페이지 번호를 구한다.
		int startPage = 1 + (currentBlock -1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK -1);
		//5. 총 게시글 수를 구한다.
		int totalPostCount = service.rerieveTotalSendMsg(userId);
		
		//6.총 페이지수를 구한다.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : totalPostCount / POST_PER_PAGE + 1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		} //if end
		
		//7. request 영역에 페이지 정보를 바인딩
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalPostCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		
		return new ActionForward("/message/sendMsgList.jsp?currentPage=" + currentPage, false);
		
	}//execute() end
}// class end
