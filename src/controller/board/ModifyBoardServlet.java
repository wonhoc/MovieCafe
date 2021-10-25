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
@WebServlet("/modifyBoard")
public class ModifyBoardServlet extends HttpServlet {
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
				}
				
			}else {
				if (part.getSize() != 0) {
					BoardFileVo file = FileUtils.uploadFile(part);
					board.addBoardFile(file);
				}
			}
		}
		
			if (isVlid) {
				BoardService boardService = BoardService.getInstance();
				boardService.modifyBoard(board);
				session.removeAttribute("board");
				response.sendRedirect(request.getContextPath() + "/board/listBoard.do");
			}else {
				response.sendRedirect(request.getContextPath() + "/board/modifyBoardForm.jsp");
			}
		
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

}
