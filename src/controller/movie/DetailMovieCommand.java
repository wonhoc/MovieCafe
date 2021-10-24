package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieInfoVo;
import model.service.movie.MovieService;

public class DetailMovieCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		String userId = request.getParameter("userId");
		
		MovieInfoVo movieDetail = MovieService.getInstace().retrieveMovieDetail(userId, movieNo);
		
		request.setAttribute("movieDetail", movieDetail);
		
		return new ActionForward("/indexControl.jsp?contentTemplate=movie/detailMovie", false);

	}

}
