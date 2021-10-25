package controller.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class detailBoardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		try {
			System.out.println("dddddssssssd");
			
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			BoardVo board = BoardService.getInstance().detailBoard(boardNo);
			
			System.out.println(board);
			
			req.setAttribute("board", board);
			
			
			return new ActionForward("/board/detailBoard.jsp", false);
			
		} catch (Exception e) {
			
			throw e;
		}
		
	}

	
	
	
	
}
