package model.service.movie;

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
		try {
			movieInfoDao.insertMovie(movie);
		} catch (Exception e) {
			throw e;
		}
		
	}
}