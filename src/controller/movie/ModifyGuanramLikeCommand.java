package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import model.service.movie.MovieService;

public class ModifyGuanramLikeCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		String userId = request.getParameter("reviewId");
				
		MovieService movieService = MovieService.getInstace();
		int no = movieService.upLikeGuanram(movieNo, userId);
		request.setAttribute("upLikeCount", no);
		
		return new ActionForward("/movie/upLikeGuanram.jsp", false);
	}

}
