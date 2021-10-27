package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardFileVo;
import model.service.board.BoardService;

public class RemoveFileCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int boardfileNo = Integer.parseInt(req.getParameter("boardfileNo"));
		
		System.out.println(boardfileNo);
		
		BoardService boardservice = BoardService.getInstance();
		
		boardservice.removeFile(boardfileNo);
		
		
		return null;
	}

	
	
}
