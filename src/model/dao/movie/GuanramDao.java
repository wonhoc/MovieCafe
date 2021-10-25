package model.dao.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.movie.GuanramListVo;
import domain.movie.MovieGuanramVo;
import model.DBConn;

public class GuanramDao {

	private static GuanramDao guanramDao;
	
	private GuanramDao() {};
	
	public static GuanramDao getInstance() {
		if(guanramDao == null) {
			guanramDao = new GuanramDao();
		}
		return guanramDao;
	}
	
	// 관람평 리스트 조회
	public ArrayList<GuanramListVo> selectGuanramList(int movieNo) throws Exception {
		
		ArrayList<GuanramListVo> guanramList = new ArrayList<GuanramListVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT user_id, photo_origin, photo_sys, ");
			sql.append("user_nick, guanram_review, guanram_wdate, guanram_like, guanram_rating ");
			sql.append("FROM user_info JOIN movie_guanram USING(user_id)  ");
			sql.append("WHERE movie_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, movieNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuanramListVo guanram = new GuanramListVo();
				
				guanram.setUserId(rs.getString(1));
				guanram.setPhotoOrigin(rs.getString(2));
				guanram.setPhotoSys(rs.getString(3));
				guanram.setNickName(rs.getString(4));
				guanram.setGuanramReview(rs.getString(5));
				guanram.setGuanramWdate(rs.getString(6));
				guanram.setGuanramLike(rs.getInt(7));
				guanram.setGuanramRating(rs.getInt(8));
				
				guanramList.add(guanram);
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
		return guanramList;
	}
	
	// 관람평 작성
	public void insertGuanram(MovieGuanramVo guanram) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO movie_guanram(user_id, movie_no, guanram_review, guanram_rating) ");
			sql.append("VALUES ( ?, ?, ?, ? ) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			System.out.println(guanram.getUserId());
			System.out.println(guanram.getMovieNo());
			System.out.println(guanram.getGuanramReview());
			System.out.println(guanram.getGuanramRating());
			
			pstmt.setString(1, guanram.getUserId());
			pstmt.setInt(2, guanram.getMovieNo());
			pstmt.setString(3, guanram.getGuanramReview());
			pstmt.setInt(4, guanram.getGuanramRating());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		
	}
		
	// 사용자 아이디 비교
	public int compareUserId(String userId) throws Exception {
		int exists = 0;
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBConn.getConnection();
					
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT EXISTS(SELECT user_id FROM movie_guanram WHERE user_id = ? ) ");
					
			pstmt = conn.prepareStatement(sql.toString());
					
			pstmt.setString(1, userId);
					
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
