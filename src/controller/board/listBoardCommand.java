package controller.board;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import domain.category.CategoryVo;
import model.dao.board.BoardDao;
import model.service.board.BoardService;

public class listBoardCommand implements Command {

	private static final int POST_PER_PAGE = 10;
	private static final int PAGE_BLOCK = 5;
	
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
int currentPage = 0;
		
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (Exception e) {
			// curruntPage가 존재하지 않는 경우
			currentPage = 1;
		}	
			//현재 페이지에 보여줄 게시글 DB에서 게시글의 시작 행 번호를 구한다.
			int startRow = (currentPage -1)*POST_PER_PAGE;
		
			System.out.println("startRow = " + startRow);
			
			//3. DB에서 게시글 목록을 조회한다.
			List<BoardVo> boardList = BoardService.getInstance().retrieveBoardList(startRow, POST_PER_PAGE);
			
			System.out.println("bordlistSize = " + boardList.size());
			
			//4. request 영역에 "boards"속성이름으로게시글 목록을 저장한다.
			req.setAttribute("boards", boardList);
		
			
			//3. BLOCK을 구한다.
			int currentBlock = currentPage % PAGE_BLOCK == 0 ?  currentPage / PAGE_BLOCK  : currentPage / PAGE_BLOCK +1;
			
			System.out.println(currentBlock);
			
			//4. 현재 페이지가 속한 페이지 블록의 시작 페이지 번호와 페이지 번호를 구한다.
			int startPage = 1 + (currentBlock -1) * PAGE_BLOCK;
			int endPage = startPage + (PAGE_BLOCK -1 );
			
			//5. 총 게시글 수를 구한다.
			int totalPostCount = BoardService.getInstance().retrieveTotalPostCount();
			
			System.out.println(totalPostCount);
			
			//6. 총 페이지 수를 구한다.
			int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE :
																	totalPostCount / POST_PER_PAGE +1;
																	
			if(endPage > totalPage) {
				endPage =  totalPage;
			}
			
			//7. request 영역에 페이지 정보를 저장한다.
			req.setAttribute("pageBlock", PAGE_BLOCK);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("totalPostCount", totalPostCount);
			req.setAttribute("postSize", POST_PER_PAGE);
			
			return new ActionForward("listBoard.jsp?currnetPage =" + currentPage,false);
		
	
}
}