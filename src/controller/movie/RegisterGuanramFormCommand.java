package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieInfoVo;

public class RegisterGuanramFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MovieInfoVo movie = new MovieInfoVo();
		String movieImgName = movie.getPosterSys();
		
		request.setAttribute("movieImg", movieImgName);
		
		return new ActionForward("/indexControl.jsp?contentTemplate=movie/registerGuanram", false);
	}

}
