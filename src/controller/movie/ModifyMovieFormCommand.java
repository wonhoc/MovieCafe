package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieInfoVo;
import model.dao.movie.MovieInfoDao;

public class ModifyMovieFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		String userId = request.getParameter("userId");
		
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		MovieInfoVo movieInfo = movieInfoDao.selectMovie(userId, movieNo);
		
		HttpSession session = request.getSession();
		session.setAttribute("movieInfo", movieInfo);
		return new ActionForward("/indexControl.jsp?contentTemplate=movie/modifyMovieForm", true);
	}

}
