package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.dao.movie.GuanramDao;
import model.service.movie.MovieService;

public class ModifyGuanramLikeCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		
		/*
		 * HttpSession session = request.getSession();
		 * UserInfoVo userInfo = (UserInfoVo)session.getAttribute("userInfo"); String
		 * userId = userInfo.getUserId();
		 */
		
		String userId = "test_user04";
		
		MovieService movieService = MovieService.getInstace();
		int no = movieService.upLikeGuanram(movieNo, userId);
		request.setAttribute("upLikeCount", no);
		
		return new ActionForward("/movie/upLikeGuanram.jsp", false);
	}

}
