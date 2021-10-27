package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collection;
import javax.servlet.http.Part;

import domain.board.BoardFileVo;
import domain.board.BoardVo;
import model.service.board.BoardService;
import util.board.FileUtils;

@MultipartConfig(fileSizeThreshold = 1024, location = "/temp", maxFileSize = -1L, maxRequestSize = -1L)
@WebServlet(urlPatterns = "/modifyBoardForm")
public class TipModifyBoardServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			
		HttpSession session = request.getSession();
		BoardVo board = (BoardVo) session.getAttribute("board");
		
		board.getBoardfileList().clear();
		
		Collection<Part> parts = request.getParts();
		
		
		
		boolean isVlid = true; 
		for (Part part : parts) {
			if (!part.getHeader("content-disposition").contains("filename=")) {
				String name = part.getName();
				
				System.out.println("name : " + name);
				
				switch (name) {
				case "boardTitle":
					board.setBoardTitle(request.getParameter(name));
					break;
				case "userId":
					board.setUserId(request.getParameter(name));
					break;
				case "horseNo":
					board.setHorseNo(Integer.parseInt(request.getParameter(name)));
					break;
				case "boardContent":
					board.setBoardContent(request.getParameter(name));
					break;
				case "boardNotice":
					board.setBoardNotice(Integer.parseInt(request.getParameter(name)));
					break;
				case "apiX":
					board.setApiX(request.getParameter(name));
					break;
				case "apiY":
					board.setApiX(request.getParameter(name));
					break;
				case "cateNo":
					board.setCateNo(Integer.parseInt(request.getParameter(name)));
					break;
				}
				
			}else {
				if (part.getSize() != 0) {
					BoardFileVo file = FileUtils.uploadFile(part);
					board.addBoardFile(file);
				}
			}
		}
		
			request.setAttribute("cateNo", board.getCateNo());
			if (isVlid) {
				BoardService boardService = BoardService.getInstance();
				boardService.modifyBoard(board);
				session.removeAttribute("board");
				
				if (board.getHorseNo() == 1) {
					response.sendRedirect(request.getContextPath() + "/board/listBoard_NewMem.do?cateNo=1");
					
					
				}else if(board.getHorseNo() == 2 ||board.getHorseNo() == 3 ||board.getHorseNo() == 4 ||board.getHorseNo() == 5 ||board.getHorseNo() == 6 ||board.getHorseNo() == 7){

					response.sendRedirect(request.getContextPath() + "/board/listBoard_MovieReview.do?cateNo=2");
				}else if(board.getCateNo() == 8 ||board.getHorseNo() == 9){

					response.sendRedirect(request.getContextPath() + "/board/listBoard_sisa.do?cateNo=3");
				}else if(board.getHorseNo() == 10 ||board.getHorseNo() == 11){

					response.sendRedirect(request.getContextPath() + "/board/listBoard_Tip.do?cateNo=4");
				}else if(board.getCateNo() == 12 ||board.getHorseNo() == 13 ||board.getHorseNo() == 14){

					response.sendRedirect(request.getContextPath() + "/board/listBoard_Ticket.do?cateNo=5");
				}else if(board.getHorseNo() == 15){

					response.sendRedirect(request.getContextPath() + "/board/listBoard_Event.do?cateNo=6");
				}
				
			}else {
				response.sendRedirect(request.getContextPath() + "/board/modifyBoardForm.jsp");
			}
		
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

}
