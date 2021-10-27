package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.dao.board.BoardDao;
import model.dao.board.CommentDao;
import model.dao.board.RecomDao;

public class RecomReviewListCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//추천 리뷰 및 공지사항 게시글 목록 조회 요청 처리
			
		BoardDao boardDao = BoardDao.getInstance();		
				ArrayList<BoardVo> boards = boardDao.selectRecomRevList();
				ArrayList<BoardVo> noticeBoards = boardDao.selectNoticeList();
				
				RecomDao recomDao = RecomDao.getInstance();
				
				for(BoardVo board : boards) {
					int cnt = recomDao.selectRecomCnt(board.getBoardNo());
					board.setRecomCount(cnt);
				}
				
				for(BoardVo board : noticeBoards) {
					int cnt = recomDao.selectRecomCnt(board.getBoardNo());
					board.setRecomCount(cnt);
				}
				
				//댓글 수를 구하다.
				CommentDao commDao = CommentDao.getInstance();
				
				for (BoardVo board : boards) {
					int cnt = commDao.selectCommCnt(board.getBoardNo());
					board.setCommentCount(cnt);
				}
					
				for (BoardVo board : noticeBoards) {
					int cnt = commDao.selectCommCnt(board.getBoardNo());
					board.setCommentCount(cnt);
				}
				request.setAttribute("boards", boards);
				request.setAttribute("noticeBoards", noticeBoards);
				return new ActionForward("/board/recomReview.jsp", false);
	
	}
	
}
