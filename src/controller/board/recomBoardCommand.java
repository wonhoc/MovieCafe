package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.RecomVo;
import model.dao.board.RecomDao;

public class recomBoardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		int cnt = 0;
		boolean isRecom = true;
		try {
		//1. 게시글 번호를 구한다.
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		RecomDao recomDao = RecomDao.getInstance();
		System.out.println("추천요청 id : "+userId);
		
		ArrayList<String> recommenders = recomDao.selectRecommender(boardNo);
		for(String recommender : recommenders)
		{
			if(recommender.equals(userId));
			cnt ++;
		}
		if(cnt == 0) {
		RecomVo recom = new RecomVo(boardNo, userId); 		
		recomDao.insertRecommend(recom);
		isRecom = true;
		
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("recom", recom);
		request.setAttribute("isRecom",isRecom);
		return new ActionForward("/board/listRecom.jsp", false);
		} else {
			RecomVo recom = new RecomVo(boardNo, userId); 		
			recomDao.deleteRecommend(recom);
			isRecom = false;
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("recom", recom);
			request.setAttribute("isRecom",isRecom);
			return new ActionForward("/board/listRecom.jsp", false);
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
