package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import domain.board.BoardLocationVo;
import domain.board.BoardVo;

public class BoardLocationDao {

	private static BoardLocationDao boardLocationDao;
	
	private BoardLocationDao() {
		
	}
	
	public static BoardLocationDao getInstance() {
		if(boardLocationDao == null) {
			boardLocationDao = new BoardLocationDao();
		}
		return boardLocationDao;
		
	}
	
	public void insertBoardLocation(BoardLocationVo location, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO movie.location(board_no, api_x, api_y)		");
			sql.append("VALUES (?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, location.getBoardNo());
			pstmt.setString(2, location.getApiX());
			pstmt.setString(3, location.getApiY());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			}catch(Exception e2) {
				throw e2;
			}
		}
	}
	
}
