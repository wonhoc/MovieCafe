package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import domain.message.SendMessageVo;
import model.dao.message.AddressDao;
import model.service.message.MsgService;

public class SendMsgListController implements Command {
	public static final int POST_PER_PAGE = 5;
	public static final int PAGE_BLOCK = 3;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		
		HttpSession session = request.getSession();
		UserInfoVo user = (UserInfoVo)session.getAttribute("userInfo");
		String userId = user.getUserId();
		System.out.println("sendListuserId: " + userId);
		
		
		
		//����¡ ó�� ����
		//1. ���� ������ ��ȣ ��������
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}// end
		
		//2. ���� �������� ������ �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
		int startRow = (currentPage -1) * POST_PER_PAGE;
		
		
		MsgService service = MsgService.getInstance();
		//������ �޼��� ������ ��ü
		 ArrayList<SendMessageVo> sendMsgList = service.retrieveSendMsgList(userId, startRow, POST_PER_PAGE); 
		//request������ sendMsgList�̸����� ������ ������ ��ü ���ε�
		request.setAttribute("sendMsgList", sendMsgList);
		
		//3. Block�� ���Ѵ�.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1 ;
		
		//4. ���� �������� ���� ������ ����� ���� ��ȣ�� ������ ��ȣ�� ���Ѵ�.
		int startPage = 1 + (currentBlock -1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK -1);
		//5. �� �Խñ� ���� ���Ѵ�.
		int totalPostCount = service.rerieveTotalSendMsg(userId);
		
		//6.�� ���������� ���Ѵ�.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : totalPostCount / POST_PER_PAGE + 1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		} //if end
		
		//7. request ������ ������ ������ ���ε�
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalPostCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		
		return new ActionForward("/message/sendMsgList.jsp?currentPage=" + currentPage, false);
		
	}//execute() end
}// class end
