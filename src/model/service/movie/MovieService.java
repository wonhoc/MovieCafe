package model.service.movie;

import java.util.ArrayList;

import domain.movie.MovieInfoVo;
import model.dao.movie.GuanramDao;
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
	
	// 영화 목록 조회
	public ArrayList<MovieInfoVo> retrieveMovieList(int startRow, int postSize) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		return movieInfoDao.selectMovieList(startRow, postSize);
	}
	
	// 영화 게시글 수를 구한다.
	public int retrieveTotalMovieCount() throws Exception {
		return MovieInfoDao.getInstance().selectTotlaMovieCount();
	}
	
	// 영화 정보 삭제
	public void removeMovie(int movieNo) throws Exception {
		MovieInfoDao.getInstance().deleteMovie(movieNo);
	}
	
	// 영화 정보 상세 조회
	public MovieInfoVo retrieveMovieDetail(String userId, int movieNo) throws Exception {
		MovieInfoVo movieDetail = new MovieInfoVo();
		movieDetail = MovieInfoDao.getInstance().selectMovie(userId, movieNo);
		movieDetail.setGuanramList(GuanramDao.getInstance().selectGuanramList(movieNo));
		System.out.println(movieDetail);
		return movieDetail;	
	}
	
}