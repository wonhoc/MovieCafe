package controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class SearchBoardCommand implements Command {
	private static final int POST_PER_PAGE = 10; 
	private static final int PAGE_BLOCK = 5; 

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		/*
		 * try { ArrayList<BoardVo> boardList =
		 * BoardService.getInstance().findBoardList(cateNo, keyfield, keyword);
		 * request.setAttribute("boardList", boardList); return new
		 * ActionForward("/board/findBoard.jsp", false); } catch (Exception e) {
		 * e.printStackTrace(); //request.setAttribute("exception", e);
		 * //RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
		 * //dispatcher.forward(request, response); return null; }
		 * 
		 * }
		 */
		// int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int startRow = (currentPage - 1) * POST_PER_PAGE; 

		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		if (keyfield.equals("all")) {
			boards = BoardService.getInstance().findBoardList(cateNo, keyword, startRow, POST_PER_PAGE);
		} else if (keyfield.equals("user_id") || keyfield.equals("board_title") || keyfield.equals("board_content")) {
			boards = BoardService.getInstance().findBoardList(cateNo, keyfield, keyword, startRow, POST_PER_PAGE);
		} else { 
			int horseNo = Integer.parseInt(keyfield);

			boards = BoardService.getInstance().findHorseBoardList(cateNo, horseNo, keyword, startRow, POST_PER_PAGE);
		}

		request.setAttribute("boards", boards);

		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		int totalPostCount = boards.size();
		
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
		
		return new ActionForward("/template.jsp?contentTemplate=board/findBoard&currentPage=" + currentPage, false);
	}

}
