package model.dao.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.movie.GuanramListVo;
import domain.movie.MovieInfoVo;
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
}
