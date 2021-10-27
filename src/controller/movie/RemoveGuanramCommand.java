package controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.movie.GuanramDao;

public class RemoveGuanramCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		String userId = request.getParameter("userId");

		GuanramDao.getInstance().deleteGuanram(movieNo, userId);

		return new ActionForward("/detailMovie.do", false);
	}

}
