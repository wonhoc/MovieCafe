package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieGuanramVo;
import model.dao.movie.GuanramDao;

public class RegisterGuanramCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			String userId = request.getParameter("userId");
			int movieNo = Integer.parseInt(request.getParameter("movieNo"));
			String review = request.getParameter("review");
			int rating = Integer.parseInt(request.getParameter("rating"));
			
			GuanramDao guanramDao = GuanramDao.getInstance();
			guanramDao.insertGuanram(new MovieGuanramVo(userId, movieNo, review, rating));
			
			request.setAttribute("movieNo", movieNo);
			
			return new ActionForward("/detailMovie.do", false);	
		} catch (Exception e) {
			throw e;
		}
		
	}

}
