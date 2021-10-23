package controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class SearchBoardCommand implements Command{
	private static final int POST_PER_PAGE = 10; // 한페이지에 보여줄 게시글 개수
	private static final int PAGE_BLOCK = 5; // 한페이지에 몇개의 페이지를 보여줄것인지
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		int cateNo = 2;
		String keyfield = request.getParameter("keyfield");
		System.out.println(keyfield);
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
	/*
		try {
			ArrayList<BoardVo> boardList = BoardService.getInstance().findBoardList(cateNo, keyfield, keyword);			
			request.setAttribute("boardList", boardList);
			return new ActionForward("/board/findBoard.jsp", false);
		} catch (Exception e) {
			e.printStackTrace();
			//request.setAttribute("exception", e);			
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
			//dispatcher.forward(request, response);
			return null;
		}
		
	}
	*/
		//페이징 처리
				//int cateNo = Integer.parseInt(request.getParameter("cateNo"));
						//1. 현재 페이지 번호를 구한다.
						int currentPage = 0;
						try {
							currentPage = Integer.parseInt(request.getParameter("currentPage"));
						} catch (Exception e) {
							currentPage = 1;
						}
						
						//2. 현재 페이지에 보여줄 게시글의 시작 행 번호를 구한다.
						int startRow = (currentPage - 1) * POST_PER_PAGE;	//mysql은 행이 0부터 시작하니까 맨 뒤에 +1안해도됌
						
						//3. DB에서 게시글 목록을 조회한다.
						ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
						if(keyfield.equals("all"))
						{
							boards = BoardService.getInstance().findBoardList(cateNo, keyword,startRow, POST_PER_PAGE);
							//디버깅
							System.out.println("all find결과 "+boards.size());
						} else if(keyfield.equals("user_id")||keyfield.equals("board_title")||keyfield.equals("board_content"))
						{
						boards = BoardService.getInstance().findBoardList(cateNo, keyfield, keyword,startRow, POST_PER_PAGE);
						//디버깅
						System.out.println("id find결과 "+boards.size());
						} else {	//말머리 검색
							boards = BoardService.getInstance().findHorseBoardList(cateNo, keyfield, keyword,startRow, POST_PER_PAGE);
							//디버깅
							System.out.println("말머리별 find결과 "+boards.size());
							}
						
						//4. request 영역에 "boards"라는 속성이름으로 게시글 목록을 저장한다.
						//왜냐면 이건 listBoard.jsp에서 보여주고 안쓸 속성이니까 requset영역에 저장
						request.setAttribute("boards", boards);
						
						//5. block을 구한다.
						int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage /PAGE_BLOCK + 1; 	
						//?앞의조건이 참인경우 왼 : 오  중 왼이실행.
						
						//6. 현재 페이지가 속한 페이지블록의 시작 페이지 번호와 페이지 번호를 구한다.
						int startPage = 1+ (currentBlock - 1) * PAGE_BLOCK;
						int endPage = startPage + (PAGE_BLOCK - 1);
						
						//7. 총 게시글 수를 구한다.
						int totalPostCount = boards.size();
						System.out.println(totalPostCount);
						//8. 총 페이지 수를 구한다.
						int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : 
																				totalPostCount / POST_PER_PAGE + 1 ;
						
						if(endPage > totalPage) {
							endPage = totalPage;
						}
						
						//9. request 영역에 페이지 정보를 저장한다.
						request.setAttribute("cateNo", cateNo);
						request.setAttribute("pageBlock", PAGE_BLOCK);
						request.setAttribute("startPage", startPage);
						request.setAttribute("endPage", endPage);
						request.setAttribute("totalPage", totalPage);
						request.setAttribute("totalPostCount", totalPostCount);
						request.setAttribute("postSize", POST_PER_PAGE);
						// list.board.jsp로 이동~
						return new ActionForward("/board/findBoard.jsp?currentPage="+currentPage, false);		
					}
					
}
	

