package model.service.movie;

import javax.servlet.http.HttpServletRequest;

import domain.movie.MovieInfoVo;
import model.dao.movie.MovieInfoDao;

public class MovieService {

	private static MovieService movieService;
	
	private MovieService() {}
	
	public static MovieService getInstace() {
		if(movieService == null) {
			movieService = new MovieService();
		}
		return movieService;
	}
	
	// 영화 정보 등록 
	public void registerMovie(MovieInfoVo movie) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		movieInfoDao.insertMovie(movie);		
	}
	
	// 영화 제목 비교
	public int compaerMovie(String movieTitle) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		int exists = movieInfoDao.compareMovie(movieTitle);
		return exists;
	}
}