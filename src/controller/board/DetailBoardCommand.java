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

public class DetailBoardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1. �Խñ� ��ȣ�� ���Ѵ�.
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				int cateNo = Integer.parseInt(request.getParameter("cateNo"));
				System.out.println("카테번호"+cateNo);
				//int cateNo = 2;
				System.out.println("����ȸ��û �Խñ۹�ȣ : "+boardNo);
				//System.out.println(cateNo);
				BoardDao boardDao = BoardDao.getInstance();
				
				//2. DB조회수 증가
				boardDao.upHitcount(boardNo);
				BoardService service = BoardService.getInstance();
				BoardVo board = service.detailBoard(boardNo);
				
				request.setAttribute("board", board);
				//request.setAttribute("boardNo", boardNo);
				request.setAttribute("cateNo", cateNo);
				System.out.println("ī�׹�ȣ"+cateNo);
				List<CommentVo> commentList = CommentDao.getInstance().selectCommentList(boardNo);
				System.out.println("��� ��ȸ ���"+commentList.size());
				request.setAttribute("commentList", commentList);
				return new ActionForward("/indexControl.jsp?contentTemplate=/board/detailBoard", false);
			//	return new ActionForward("/board/detailBoard.jsp", false);
	}

}
