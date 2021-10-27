package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class TipModifyBoardFormCommand implements  Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		BoardService boardService = BoardService.getInstance();
		BoardVo board = boardService.detailBoard(boardNo);
		
		HttpSession session = req.getSession();
		session.setAttribute("board", board);
		
		return new ActionForward("/board/tipModifyBoard.jsp", true);

	
	
}
}