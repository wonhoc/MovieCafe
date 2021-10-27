package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import domain.board.ReportVo;
import model.dao.board.BoardDao;
import model.dao.board.ReportDao;


public class ReportBoardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			System.out.println("신고할 게시글 번호는"+boardNo);
			BoardVo board = BoardDao.getInstance().selectBoard(boardNo);
			String userId = board.getUserId();
			System.out.println("신고할 유저"+userId);
			ReportDao reportDao = ReportDao.getInstance();
			ReportVo report = new ReportVo(boardNo,userId);
			reportDao.insertReportBoard(report);
			
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("report", report);

			return new ActionForward("/board/listReport.jsp", false);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}
 
}
