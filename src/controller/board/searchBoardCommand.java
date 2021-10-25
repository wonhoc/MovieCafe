package controller.board;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class searchBoardCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

			String keyField = req.getParameter("keyField");
			String keyWord = req.getParameter("keyword");
			
			try {
				List<BoardVo> boards = BoardService.getInstance().findBoardList(keyField, keyWord);
				req.setAttribute("boards", boards);
				return new ActionForward("/board/findBoard.jsp",false);
						
			}catch (Exception e) {
				throw e;
			}
		
	}

}
