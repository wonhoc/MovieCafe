package controller.movie;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.movie.MovieInfoVo;
import model.dao.movie.MovieInfoDao;
import model.service.movie.MovieService;

public class MovieListCommand implements Command {

	private static final int POST_PER_PAGE = 3;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();

		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int startRow = (currentPage - 1) * POST_PER_PAGE;
		System.out.println(startRow + ": startRow");

		ArrayList<MovieInfoVo> movieList = movieInfoDao.selectMovieList(startRow, POST_PER_PAGE);

		request.setAttribute("movieList", movieList);

		int totalPostCount = MovieService.getInstace().retrieveTotalMovieCount();
		
		int currentBlock = currentPage % totalPostCount == 0 ? currentPage / totalPostCount : currentPage / totalPostCount + 1;

		int startPage = 1 + (currentBlock - 1) * totalPostCount;
		int endPage = startPage + (totalPostCount - 1);

		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE
				: totalPostCount / POST_PER_PAGE + 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("pageBlock", totalPostCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalPostCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		
		/* return new ActionForward("/main.jsp?currentPage=" + currentPage, false); */
		return new ActionForward("/indexControl.jsp?contentTemplate=main&currentPage=" + currentPage, false);
	}

}
