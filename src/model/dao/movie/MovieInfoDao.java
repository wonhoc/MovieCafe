package model.dao.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.movie.MovieInfoVo;

public class MovieInfoDao {
	private static MovieInfoDao movieInfoDao;
	
	private MovieInfoDao() {};
	
	public static MovieInfoDao getInstance() {
		if(movieInfoDao == null) {
			movieInfoDao = new MovieInfoDao();

		}
		return movieInfoDao;
	}
	
	public void insertMovie(MovieInfoVo movie, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}