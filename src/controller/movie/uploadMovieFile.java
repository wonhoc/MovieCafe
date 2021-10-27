package controller.movie;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import domain.movie.MovieInfoVo;
import model.service.movie.MovieService;
import util.file.FileUploadUtils;

@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = -1L, maxRequestSize = -1L, location = "/temp")
@WebServlet("/uploadMovieFile")
public class uploadMovieFile extends HttpServlet {	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String path = request.getServletContext().getRealPath("/");
			System.out.println("path : " + path);

			request.setCharacterEncoding("utf-8");
			
			String title = request.getParameter("title");
			String director = request.getParameter("director");
			String actor = request.getParameter("actor");
			String genre = request.getParameter("genre");
			int runtime = Integer.parseInt(request.getParameter("runtime"));
			String link = request.getParameter("link");
			String age = request.getParameter("age");
			String date = request.getParameter("date");

			Part part = request.getPart("imgInput");

			ArrayList<String> fileName = FileUploadUtils.upload(part, request, "movie");
			
			String originalFileName = fileName.get(0);
			String systemFileName = fileName.get(1);
					
			MovieService movieService = MovieService.getInstace();
			
			if(request.getParameter("type").equals("modify")) {				
				HttpSession session = request.getSession();
				MovieInfoVo movieInfo = (MovieInfoVo)session.getAttribute("movieInfo");
				
				movieInfo.setMovieTitle(title);
				movieInfo.setMovieDir(director);
				movieInfo.setMovieActor(actor);
				movieInfo.setMovieGenre(genre);
				movieInfo.setMovieRuntime(runtime);
				movieInfo.setMovieLink(link);
				movieInfo.setMovieAge(age);
				movieInfo.setMovieRelease(date);
				
				movieService.modifyMovie(movieInfo);
			} else {
				
				MovieInfoVo movieInfo = new MovieInfoVo(title, director, actor, genre, runtime, link, age, date,
						originalFileName, systemFileName);
				
				int exists = movieService.compaerMovie(movieInfo.getMovieTitle());
				
				if(exists == 1) {
					request.setAttribute("exists", 1);
				} else {
						movieService.registerMovie(movieInfo);	
				}
			}

			response.sendRedirect(request.getContextPath() + "/main.do");	
				
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}