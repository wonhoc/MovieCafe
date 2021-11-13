package controller.movie;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import domain.movie.MovieInfoVo;
import model.dao.board.BoardDao;
import model.dao.board.CommentDao;
import model.dao.board.RecomDao;
import model.dao.movie.MovieInfoDao;
import model.service.movie.MovieService;

public class MainCommand implements Command {

	private static final int POST_PER_PAGE = 3;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 메인 상단
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();

		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int startRow = (currentPage - 1) * POST_PER_PAGE;

		ArrayList<MovieInfoVo> movieList = movieInfoDao.selectMovieList(startRow, POST_PER_PAGE);

		request.setAttribute("movieList", movieList);

		int totalPostCount = MovieService.getInstace().retrieveTotalMovieCount();

		if(totalPostCount == 0) {
			return new ActionForward("/template.jsp?contentTemplate=main", false);
		} else {
			int currentBlock = currentPage % totalPostCount == 0 ? currentPage / totalPostCount : currentPage / totalPostCount + 1;

			int startPage = 1 + (currentBlock - 1) * totalPostCount;
			int endPage = startPage + (totalPostCount - 1);

			int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE
					: totalPostCount / POST_PER_PAGE + 1;
			
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			request.setAttribute("pageBlock", totalPostCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("totalPostCount", totalPostCount);
			request.setAttribute("postSize", POST_PER_PAGE);
			
		// 메인 하단
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

			return new ActionForward("/template.jsp?contentTemplate=main&currentPage=" + currentPage, false);	
		}
		
	}

}
