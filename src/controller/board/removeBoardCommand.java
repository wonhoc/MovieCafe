package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class removeBoardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		
		BoardService boardService = BoardService.getInstance();
		BoardVo board = boardService.detailBoard(boardNo);
		
		boardService.removeBoard(boardNo);
		
		return new ActionForward("/index.jsp", true);
		
	}
	
}
