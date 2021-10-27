package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import domain.board.CommentVo;
import model.dao.board.BoardDao;
import model.dao.board.CommentDao;
import model.service.board.BoardService;

public class TipDetailBoardCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		
	
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		System.out.println("아아아아아아아앙므ㅏㅣ : " + boardNo);
		int cateNo = Integer.parseInt(req.getParameter("cateNo"));
		
		System.out.println("아아아아아아아앙므ㅏㅣ : " + cateNo);
		//int cateNo = 2;
		System.out.println("아아아아아아아앙므ㅏㅣ : "+boardNo);
		//System.out.println(cateNo);
		BoardDao boardDao = BoardDao.getInstance();
		
		//2. DB조회수 증가
		boardDao.upHitcount(boardNo);
		BoardService service = BoardService.getInstance();
		BoardVo board = service.detailBoard(boardNo);
		
		
		req.setAttribute("board", board);
		
		
		List<CommentVo> commentList = CommentDao.getInstance().selectCommentList(boardNo);
		System.out.println("��� ��ȸ ���"+commentList.size());
		req.setAttribute("commentList", commentList);
		
		return new ActionForward("/indexControl.jsp?contentTemplate=/board/tipDetailBoard", false);
	
	}

	
	
}
