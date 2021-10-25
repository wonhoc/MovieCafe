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
	public static final int POST_PER_PAGE = 5;
	public static final int PAGE_BLOCK = 3;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//받은 쪽지 목록 요청
		//로그인 기능 구현되면 
		//HttpSession session = request.getSession();
		//String userId = (String)session.getAttribute("userId");
		//로컬에서 테스트
		String userId = "test_user02";

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
		
		//MsgService 객체 생성
		MsgService service = MsgService.getInstance();
		//내게 온 메세지 목록 조회 method 호출
		ArrayList<ReceiveMsgVo>	receiveMsgList =  service.retrieveReceiveMsgList(userId, startRow, POST_PER_PAGE);
		
		//request영역에 receiveMsgList이름으로 내게온 쪽지 정보 바인딩
		request.setAttribute("userId", userId);
		request.setAttribute("receiveMsgList", receiveMsgList);
		
		//3. Block을 구한다.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1 ;
		
		//4. 현재 페이지가 속한 페이지 블록의 시작 번호와 페이지 번호를 구한다.
		int startPage = 1 + (currentBlock -1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK -1);
		System.out.println("startPage : " + startPage);
		System.out.println("endPage : " + endPage);
		//5. 총 게시글 수를 구한다.
		int totalPostCount = service.rerieveTotalReceiveMsg(userId);
		System.out.println("totalPostCount : " + totalPostCount);
		
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
		
		return new ActionForward("/message/receiveMsgList.jsp?currentPage=" + currentPage, false);
		
	}//execute() end
}// class end
