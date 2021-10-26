package controller.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import model.service.member.UserService;

public class ListUserCommand implements Command {

	private static final int POST_PER_PAGE = 10;
	private static final int PAGE_BLOCK = 5;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		
		int startRow = (currentPage - 1) * POST_PER_PAGE;

		
		ArrayList<UserInfoVo> users = UserService.getInstance().retrieveUserList(startRow, POST_PER_PAGE);

		
		request.setAttribute("users", users);

		
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		
		int totalUserCount = UserService.getInstance().retrieveUserTotalCount();

		
		int totalPage = totalUserCount % POST_PER_PAGE == 0 ? totalUserCount / POST_PER_PAGE
				: totalUserCount / POST_PER_PAGE + 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalUserCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		return new ActionForward("/template.jsp?contentTemplate=member/listUser&currentPage=" + currentPage, false);
		

	}
}
