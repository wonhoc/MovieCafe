package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.dao.board.CommentDao;
import model.dao.board.RecomDao;
import model.service.board.BoardService;

public class ListBoardCommand implements Command {
	private static final int POST_PER_PAGE = 10; 
	private static final int PAGE_BLOCK = 5; 

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		
		int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		
		System.out.println(cateNo);
		
		// int cateNo = 2;
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int startRow = (currentPage - 1) * POST_PER_PAGE; 

		ArrayList<BoardVo> boards = BoardService.getInstance().retrieveBoardList(cateNo, startRow, POST_PER_PAGE);
		
		RecomDao recomDao = RecomDao.getInstance();

		for (BoardVo board : boards) {
			int cnt = recomDao.selectRecomCnt(board.getBoardNo());
			board.setRecomCount(cnt);
		}

		CommentDao commDao = CommentDao.getInstance();
		
		for (BoardVo board : boards) {
			int cnt = commDao.selectCommCnt(board.getBoardNo());
			board.setCommentCount(cnt);
		}
		
		request.setAttribute("boards", boards);

		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		int totalPostCount = BoardService.getInstance().retrieveTotalPostCount(cateNo);
		System.out.println(totalPostCount);
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE
				: totalPostCount / POST_PER_PAGE + 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}


		request.setAttribute("cateNo", cateNo);
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalPostCount);
		request.setAttribute("postSize", POST_PER_PAGE);

		if(cateNo == 1) {
		return new ActionForward("/template.jsp?contentTemplate=board/listBoard_NewMem&currentPage=" + currentPage,
				false);
		} else if(cateNo == 2) {
			return new ActionForward("/template.jsp?contentTemplate=board/listBoard_MovieReview&currentPage=" + currentPage,
					false);
		}  else if(cateNo == 3) {
			return new ActionForward("/template.jsp?contentTemplate=board/listBoard_sisa&currentPage=" + currentPage,
					false);
		} else if(cateNo == 4) {
			return new ActionForward("/template.jsp?contentTemplate=board/listBoard_Tip&currentPage=" + currentPage,
					false);
		} else if(cateNo == 5) {
			return new ActionForward("/template.jsp?contentTemplate=board/listBoard_Ticket&currentPage=" + currentPage,
					false);
		} else {
			return new ActionForward("/template.jsp?contentTemplate=board/listBoard_Event&currentPage=" + currentPage,
					false);
		}
		
	}

}
