package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.CommentVo;
import model.dao.board.CommentDao;

public class ModifyCommentCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int comNo = Integer.parseInt(request.getParameter("comNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String comContent = request.getParameter("comContent");
		
		CommentVo comment = new CommentVo(comNo, comContent);
		System.out.println(comment);
		CommentDao commentDao = CommentDao.getInstance();
		
		commentDao.updateComment(comment);

		List<CommentVo> commentList = commentDao.selectCommentList(boardNo);
		
		for (CommentVo commentVo : commentList) {
			System.out.println(commentVo);
		}
		request.setAttribute("commentList", commentList);

		return new ActionForward("/board/listComment.jsp", false);
	
	}
 
}
