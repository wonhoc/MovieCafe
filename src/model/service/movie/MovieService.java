package model.service.movie;

import java.sql.Connection;
import java.util.ArrayList;

import domain.movie.MovieGuanramVo;
import domain.movie.MovieInfoVo;
import model.dao.member.DBConn;
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
	
	// ��ȭ ���� ��� 
	public void registerMovie(MovieInfoVo movie) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		movieInfoDao.insertMovie(movie);		
	}
	
	// ��ȭ ���� ��
	public int compaerMovie(String movieTitle) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		int exists = movieInfoDao.compareMovie(movieTitle);
		return exists;
	}
	
	// ��ȭ ��� ��ȸ
	public ArrayList<MovieInfoVo> retrieveMovieList(int startRow, int postSize) throws Exception {
		MovieInfoDao movieInfoDao = MovieInfoDao.getInstance();
		return movieInfoDao.selectMovieList(startRow, postSize);
	}
	
	// ��ȭ �Խñ� ���� ���Ѵ�.
	public int retrieveTotalMovieCount() throws Exception {
		return MovieInfoDao.getInstance().selectTotlaMovieCount();
	}
	
	// ��ȭ ���� ����
	public void removeMovie(int movieNo) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			GuanramDao.getInstance().deleteMovieGuanram(movieNo, conn);
			
			MovieInfoDao.getInstance().deleteMovie(movieNo, conn);
			
			isSuccess = true;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null) {
					if(isSuccess) conn.commit();
					else conn.rollback();
					conn.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}
		
		
		
	}
	
	// ��ȭ ���� �� ��ȸ
	public MovieInfoVo retrieveMovieDetail(String userId, int movieNo) throws Exception {
		MovieInfoVo movieDetail = new MovieInfoVo();
		movieDetail = MovieInfoDao.getInstance().selectMovie(userId, movieNo);
		movieDetail.setGuanramList(GuanramDao.getInstance().selectGuanramList(movieNo));
		return movieDetail;	
	}
	
	// ��ȭ ���� ����
	public void modifyMovie(MovieInfoVo movie) throws Exception {
		MovieInfoDao.getInstance().updateMovie(movie);
	}
	
	// ������ ���
	public void registerGuanram(MovieGuanramVo guanram) throws Exception {
		GuanramDao.getInstance().insertGuanram(guanram);
	}
	
	// ������ ����
	public void removeGuanram(int movieNo, String userId) throws Exception {
		GuanramDao.getInstance().deleteGuanram(movieNo, userId);
	}
	
	// ������ ��õ ����
	public int upLikeGuanram(int movieNo, String userId) throws Exception {
		GuanramDao guanramDao = GuanramDao.getInstance();
		guanramDao.upLikecount(movieNo, userId);
		
		return guanramDao.selectGuanramLike(movieNo, userId);	
	}
	
}