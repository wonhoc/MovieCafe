package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.movie.MovieInfoDao;

public class RemoveMovieCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		
		MovieInfoDao.getInstance().deleteMovie(movieNo);
		
		return new ActionForward("/main.do", false);
		
	}

}
