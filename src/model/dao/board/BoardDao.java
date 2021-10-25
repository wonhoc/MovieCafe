package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import domain.board.BoardFileVo;
import domain.board.BoardVo;
import domain.category.CategoryVo;

import model.DBConn;




public class BoardDao {

	private static BoardDao boardDao;

	private BoardDao() {

	}

	public static BoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}

	//게시글 삭제
	public void deleteBoard(int no, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE FROM movie.board			");
			sql.append("WHERE board_no =?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}
	
	
	
	//게시글 작성
	public int insertBoard(BoardVo board, Connection conn)throws Exception{
		int no = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO movie.board(user_id, board_title, board_content, horse_no, board_notice)	");
			sql.append("VALUES (?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, board.getUserId());		
			pstmt.setString(2, board.getBoardTitle());		
			pstmt.setString(3, board.getBoardContent());		
			pstmt.setInt(4, board.getHorseNo());		
			pstmt.setInt(5, board.getBoardNotice());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			stmt = conn.createStatement();
			
			sql.delete(0, sql.length());
			sql.append("SELECT LAST_INSERT_ID()");
			rs = stmt.executeQuery(sql.toString());
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return no;
	}
	
	//게시글 수정
	public void updateBoard(BoardVo board, Connection conn)throws Exception{
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE movie.board																	");
			sql.append("SET board_title = ?, board_content = ? , horse_no = ?, board_notice = ?				");
			sql.append("where board_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getHorseNo());
			pstmt.setInt(4, board.getBoardNotice());
			pstmt.setInt(5, board.getBoardNo());			
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
	
	
	
	// 말머리 구하기
	public ArrayList<CategoryVo> selectHorseList() throws Exception {
		ArrayList<CategoryVo> horseList = new ArrayList<CategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			int ti = 4;

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT horse 											");
			sql.append("FROM movie.horse join movie.category using (cate_no)	");
			sql.append("Where category.cate_no = ?;");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, ti);

			rs = pstmt.executeQuery();
			
			
			
			while (rs.next()) {
				String horse = rs.getString(1);
				horseList.add(new CategoryVo(horse));
			};

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return horseList;
	}

	//게시판 목록페이지
	public List<BoardVo> selectBoardList(int startRow, int postSize) throws Exception {
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			int ti = 4;
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT board_no, user_Id, board_title, DATE_FORMAT(board_wdate, '%Y/%m/%d %H:%i:%s') as writedate,		");
			sql.append("board_count, board_count, board_count															");
			sql.append("FROM movie.board join movie.horse using(horse_no)												");
			sql.append("WHERE cate_no = ?																				");
			sql.append("ORDER BY board_wdate DESC																		");
			sql.append("LIMIT ? OFFSET ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, ti);
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVo board = new BoardVo();
				board.setBoardNo(rs.getInt(1));
				board.setUserId(rs.getString(2));
				board.setBoardTitle(rs.getString(3));
				board.setBoardWdate(rs.getString(4));
				board.setHitCount(rs.getInt(5));
				board.setRecomCount(rs.getInt(6));
				board.setCommentCount(rs.getInt(7));
				boardList.add(board);
			}
			return boardList;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	
	// 게시판 찾기
	public ArrayList<BoardVo> selectBoardList(String keyfield, String keyword) throws Exception {
		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			int ti = 2;

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT user_Id, board_title, DATE_FORMAT(board_wdate, '%Y/%m/%d %H:%i:%s') as writedate,		");
			sql.append("board_count, board_count, board_count							");
			sql.append("FROM movie.board join movie.horse using(horse_no)				");
			sql.append("WHERE " + keyfield + " LIKE CONCAT('%',?,'%') 		");
			sql.append("ORDER BY board_wdate DESC");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String userId = rs.getString(1);
				String boardTitle = rs.getString(2);
				String boardWdate = rs.getString(3);
				int hitCount = rs.getInt(4);
				int recomCount = rs.getInt(5);
				int commentCount = rs.getInt(6);
				boards.add(new BoardVo(userId, boardTitle, boardWdate, hitCount, recomCount, commentCount));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				throw e2;
			}
		}
		return boards;

	}

	// 총 게시글 수
	public int selectTotalPostCount() throws Exception {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			int ti = 4;
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) 										 ");
			sql.append("FROM movie.board join movie.horse using(horse_no)		 ");
			sql.append("WHERE cate_no =?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, ti);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return count;
	}

	// 게시판 상세보기
	public BoardVo selectBoard(int boardNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("DAO");
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.board_no, b.horse_no, b.board_title, b.user_id, b.board_count,							");
			sql.append("date_format(b.board_wdate, '%Y/%m/%d %H:%i:%S') as writedate, b.board_content,  b.board_count, b.board_count,		");
			sql.append("f.boardfile_origin, f.boardfile_sys, f.boardfile_size , f.boardfile_no							");
			sql.append("FROM movie.board as b LEFT JOIN movie.boardFile as f	 										");
			sql.append("ON b.board_no = f.board_no	 																	");
			sql.append("WHERE b.board_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			BoardVo board = new BoardVo();
			boolean isFirst = true;
			while (rs.next()) {
				if (isFirst) {
					board.setBoardNo(rs.getInt(1));
					board.setHorseNo(rs.getInt(2));
					board.setBoardTitle(rs.getString(3));
					board.setUserId(rs.getString(4));
					board.setHitCount(rs.getInt(5));
					board.setBoardWdate(rs.getString(6));
					board.setBoardContent(rs.getString(7));
					board.setRecomCount(rs.getInt(8)); 	//추천수와 댓글 수 수정해야된다 (테스트용)
					board.setCommentCount(rs.getInt(9));
					
					isFirst = false;
				}
				if(rs.getString(10) != null) {
					BoardFileVo boardfileList = new BoardFileVo();
					boardfileList.setBoardfileOrigin(rs.getString(10));
					boardfileList.setBoardfileSys(rs.getString(11));				
					boardfileList.setBoardfileSize(rs.getInt(12));
					boardfileList.setBoardfileNo(rs.getInt(13));
					board.addBoardFile(boardfileList);
				}
					
					}
			
			
			
		return board;
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch (Exception e2){
			throw e2;
		}
	}
	
	}
	
	
	
	
	
}
