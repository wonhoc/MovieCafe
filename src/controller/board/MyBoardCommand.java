package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.dao.board.BoardDao;
import model.dao.board.CommentDao;
import model.dao.board.RecomDao;
import model.service.board.BoardService;

public class MyBoardCommand implements Command{
	private static final int POST_PER_PAGE = 10; // 한페이지에 보여줄 게시글 개수
	private static final int PAGE_BLOCK = 5; // 한페이지에 몇개의 페이지를 보여줄것인지
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		//나의 ㄱㅔ시글 목록 조회 요청 처리///
		// 1. 현재 페이지 번호를 구한다.
				int currentPage = 0;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (Exception e) {
					currentPage = 1;
				}

				// 2. 현재 페이지에 보여줄 게시글의 시작 행 번호를 구한다.
				int startRow = (currentPage - 1) * POST_PER_PAGE; // mysql은 행이 0부터 시작하니까 맨 뒤에 +1안해도됌

				BoardDao boardDao = BoardDao.getInstance();	
				System.out.println(userId+"의 게시글 조회");
						ArrayList<BoardVo> boards = boardDao.selectMyBoardList(userId, startRow, POST_PER_PAGE);
						
						RecomDao recomDao = RecomDao.getInstance();
						
						for(BoardVo board : boards) {
							int cnt = recomDao.selectRecomCnt(board.getBoardNo());
							board.setRecomCount(cnt);
						}
						
						
						
						//댓글 수를 구하다.
						CommentDao commDao = CommentDao.getInstance();
						
						for (BoardVo board : boards) {
							int cnt = commDao.selectCommCnt(board.getBoardNo());
							board.setCommentCount(cnt);
						}
						
						request.setAttribute("boards", boards);
						// 5. block을 구한다.
						int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
						// ?앞의조건이 참인경우 왼 : 오 중 왼이실행.

						// 6. 현재 페이지가 속한 페이지블록의 시작 페이지 번호와 페이지 번호를 구한다.
						int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
						int endPage = startPage + (PAGE_BLOCK - 1);

						// 7. 총 게시글 수를 구한다.
						int totalPostCount = BoardService.getInstance().retrieveTotalMyPostCount(userId);
						System.out.println(totalPostCount);
						// 8. 총 페이지 수를 구한다.
						int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE
								: totalPostCount / POST_PER_PAGE + 1;

						if (endPage > totalPage) {
							endPage = totalPage;
						}

						// 9. request 영역에 페이지 정보를 저장한다.

						
						request.setAttribute("pageBlock", PAGE_BLOCK);
						request.setAttribute("startPage", startPage);
						request.setAttribute("endPage", endPage);
						request.setAttribute("totalPage", totalPage);
						request.setAttribute("totalPostCount", totalPostCount);
						request.setAttribute("postSize", POST_PER_PAGE);
						
						return new ActionForward("/indexControl.jsp?contentTemplate=/board/listBoard&currentPage=" + currentPage, false);
	
	}
	
}
