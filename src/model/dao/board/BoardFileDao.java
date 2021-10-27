package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import domain.board.BoardFileVo;

public class BoardFileDao {

	private static BoardFileDao boardFileDao;
	
	private BoardFileDao() {
		
	}
	
	public static BoardFileDao getInstance() {
		if (boardFileDao == null) {
			boardFileDao = new BoardFileDao();			
		}
		return boardFileDao;		
	}
	
	//파일등록
	public void insertBoardFile(BoardFileVo file, Connection conn) throws Exception{
		PreparedStatement  pstmt = null;
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO movie.boardfile(boardfile_origin, boardfile_sys, boardfile_size, board_no)			 ");
			sql.append("VALUES (?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			
			
			pstmt.setString(1, file.getBoardfileOrigin());
			pstmt.setString(2, file.getBoardfileSys());
			pstmt.setLong(3, file.getBoardfileSize());
			pstmt.setInt(4, file.getBoardNo());
			
			
			
			pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		
	}
	
	
	//파일삭제
	public void deleteFile(int boardNo, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM movie.boardFile 	");
			sql.append("WHERE board_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, boardNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		
		
	}
	
	//파일만 삭제
	public void deleteOnlyFile(int boardfileNo, Connection conn) throws Exception{
		
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM movie.boardFile		");
			sql.append("WHERE boardfile_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, boardfileNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			}catch (Exception e2) {
			 throw e2;
			}
		}
		
		
		
	}
	
	
	
}
