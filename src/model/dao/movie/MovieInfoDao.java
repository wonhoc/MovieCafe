package model.dao.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.movie.MovieInfoVo;
import model.DBConn;

public class MovieInfoDao {
	private static MovieInfoDao movieInfoDao;
	
	private MovieInfoDao() {};
	
	public static MovieInfoDao getInstance() {
		if(movieInfoDao == null) {
			movieInfoDao = new MovieInfoDao();
		}
		return movieInfoDao;
	}
	
	// 영화 정보 등록 
	public void insertMovie(MovieInfoVo movie) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO movie_info(movie_title, movie_dir, movie_actor, movie_genre, ");
			sql.append("movie_runtime, movie_link, movie_age, movie_release, poster_origin, poster_sys) ");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, movie.getMovieTitle());
			pstmt.setString(2, movie.getMovieDir());
			pstmt.setString(3, movie.getMovieActor());
			pstmt.setString(4, movie.getMovieGenre());
			pstmt.setInt(5, movie.getMovieRuntime());
			pstmt.setString(6, movie.getMovieLink());
			pstmt.setString(7, movie.getMovieAge());
			pstmt.setString(8, movie.getMovieRelease());
			pstmt.setString(9, movie.getPosterOrigin());
			pstmt.setString(10, movie.getPosterSys());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		
	}
	
	// 영화 제목 비교
	public int compareMovie(String movieTitle) throws Exception {
		int exists = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT EXISTS(SELECT movie_title FROM movie_info WHERE movie_title = ? ) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, movieTitle);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exists = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		System.out.println(exists);
		return exists;
	}

}