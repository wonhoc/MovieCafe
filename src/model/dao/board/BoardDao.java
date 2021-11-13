package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import domain.board.BoardFileVo;
import domain.board.BoardLocationVo;
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
		
		// 10.22  댓글수, 추천수 아직,.,.,.,ㅠ
		public ArrayList<BoardVo> selectBoardList(int cateNo, int startRow, int postSize) throws Exception {
			ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("select board_no,user_id, board_title, board_notice,	 ");
				sql.append("date_format(board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate,board_count	");
				sql.append("from board join horse	");
				sql.append("on(board.horse_no = horse.horse_no)	");
				
				sql.append("where cate_no = ?			");

				sql.append("order by board_notice desc, board_wdate desc	");
				sql.append("LIMIT ? OFFSET ?	");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, cateNo);
				pstmt.setInt(2, postSize);
				pstmt.setInt(3, startRow);

				rs = pstmt.executeQuery();

				// (int boardNo, String boardTitle, String boardContent, int boardCount, String boardWdate,
				//boolean boardNotice, int commentCount, int recomCount, String horse, String userNick)
				

				while (rs.next()) {
					int boardNo = rs.getInt(1);
					String userId = rs.getString(2);
					String boardTitle = rs.getString(3);
					int boardNotice = rs.getInt(4);
					String boardWdate = rs.getString(5);
					int boardCnt = rs.getInt(6);
					boards.add(new BoardVo(boardNo, boardTitle, boardCnt, boardWdate, boardNotice, userId));
				}
			} catch (Exception e) {
				throw e;
			} finally {
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
			return boards;
		}
		

	// 게시글 조회수를 증가하다.
	public void upHitcount(int boardNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE 	board		 ");
			sql.append("SET board_count = board_count+1	 ");
			sql.append("WHERE board_no = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);

			pstmt.executeUpdate();

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
	}

	// 게시글 삭제
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
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	//selectTotalMyPostCount 나으ㅢ 총 게시글 수를 구한다
		public int selectTotalMyPostCount(String userId) throws Exception {
			int count = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT COUNT(*)	");
				sql.append("FROM board join horse 	");
				sql.append("on(board.horse_no = horse.horse_no)	");
				sql.append("where user_id = ?");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setString(1, userId);

				rs = pstmt.executeQuery();

				if (rs.next()) {
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
	// 추천 리뷰를 조회하다
	public ArrayList<BoardVo> selectRecomRevList() throws Exception {

		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT b.board_no, b.board_title, u.user_nick,  cate_no,                                         ");
			sql.append("DATE_FORMAT(b.board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate, b.board_count     ");
			sql.append("FROM board as b left join user_info as u		");
			sql.append("using(user_id)	");
			sql.append("left join horse as h	");
			sql.append("using(horse_no)		");
			sql.append("where cate_no != 1	or cate_no !=5	");
			sql.append("ORDER BY board_count DESC		");
			sql.append("LIMIT 5	");

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				int boardNo = rs.getInt(1);
				String boardTitle = rs.getString(2);
				String userNick = rs.getString(3);
				int cateNo = rs.getInt(4);
				String boardWdate = rs.getString(5);
				int boardCount = rs.getInt(6);
				boards.add(new BoardVo(boardNo, boardTitle, userNick, boardWdate, boardCount, cateNo));
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

	// 최근 공지글을 조회하다.
	public ArrayList<BoardVo> selectNoticeList() throws Exception {
		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT b.board_no, b.board_title, u.user_nick,  cate_no,                                      ");
			sql.append("DATE_FORMAT(b.board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate, b.board_count     ");
			sql.append("FROM board as b left join user_info as u		");
			sql.append("using(user_id)	  ");
			sql.append("left join horse   ");
			sql.append("using(horse_no)   ");
			sql.append("where board_notice=true	");
			sql.append("ORDER BY board_wdate DESC		");
			sql.append("LIMIT 5	");

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				int boardNo = rs.getInt(1);
				String boardTitle = rs.getString(2);
				String userNick = rs.getString(3);
				int cateNo = rs.getInt(4);
				String boardWdate = rs.getString(5);
				int boardCount = rs.getInt(6);
				boards.add(new BoardVo(boardNo, boardTitle, userNick, boardWdate, boardCount, cateNo));
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

	// 나의 게시물 조회
	public ArrayList<BoardVo> selectMyBoardList(String userId, int startRow, int postSize) throws Exception {

		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT b.board_no, b.board_title, u.user_nick,  cate_no,                                         ");
			sql.append("DATE_FORMAT(b.board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate, b.board_count     ");
			sql.append("FROM board as b left join user_info as u		");
			sql.append("using(user_id)	");
			sql.append("left join horse as h	");
			sql.append("using(horse_no)		");
			sql.append("where b.user_id = ?		");
			sql.append("ORDER BY board_wdate DESC		");
			sql.append("LIMIT ? OFFSET ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, userId);
			pstmt.setInt(2, postSize);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo board = new BoardVo();
				board.setBoardNo(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserNick(rs.getString(3));
				board.setCateNo(rs.getInt(4));
				board.setBoardWdate(rs.getString(5));
				board.setBoardCount(rs.getInt(6));

				boards.add(board);

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

	// 영화관람 작성
	public int insertLocationBoard(BoardVo board, Connection conn) throws Exception {
		int no = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"INSERT INTO movie.board(user_id, board_title, board_content, horse_no , api_x, api_y, board_notice)		");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getUserId());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getHorseNo());
			pstmt.setString(5, board.getApiX());
			pstmt.setString(6, board.getApiY());
			pstmt.setInt(7, board.getBoardNotice());
			pstmt.executeUpdate();
			pstmt.close();

			stmt = conn.createStatement();

			sql.delete(0, sql.length());
			sql.append("SELECT LAST_INSERT_ID()");
			rs = stmt.executeQuery(sql.toString());

			if (rs.next()) {
				no = rs.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return no;
	}

	// 게시글 작성
	public int insertBoard(BoardVo board, Connection conn) throws Exception {
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

			if (rs.next()) {
				no = rs.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return no;
	}

	// 게시글 수정
	public void updateBoard(BoardVo board, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE movie.board																	");
			sql.append(
					"SET board_title = ?, board_content = ? , horse_no = ?, board_notice = ?, api_x = ?, api_y = ?		");
			sql.append("where board_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getHorseNo());
			pstmt.setInt(4, board.getBoardNotice());
			pstmt.setString(5, board.getApiX());
			pstmt.setString(6, board.getApiY());
			pstmt.setInt(7, board.getBoardNo());
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
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
			}
			;

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

	// 게시판 목록페이지
	

	// 게시판 찾기
	/*public ArrayList<BoardVo> selectBoardList(String keyfield, String keyword) throws Exception {
		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			int ti = 2;

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT user_Id, board_title, DATE_FORMAT(board_wdate, '%Y/%m/%d %H:%i:%s') as writedate,		");
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

	}*/
	
	
	// 총 게시글 수를 구한다.
		public int selectTotalPostCount(int cateNo) throws Exception {
			int count = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT COUNT(*)	");
				sql.append("FROM board join horse 	");
				sql.append("on(board.horse_no = horse.horse_no)	");
				sql.append("where cate_no = ?");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, cateNo);

				rs = pstmt.executeQuery();

				if (rs.next()) {
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
	// 총 게시글 수
	/*
	 * public int selectTotalPostCount() throws Exception { int count = 0;
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * try { conn = DBConn.getConnection();
	 * 
	 * int ti = 4;
	 * 
	 * StringBuffer sql = new StringBuffer();
	 * sql.append("SELECT COUNT(*) 										 ");
	 * sql.append("FROM movie.board join movie.horse using(horse_no)		 ");
	 * sql.append("WHERE cate_no =?"); pstmt =
	 * conn.prepareStatement(sql.toString());
	 * 
	 * pstmt.setInt(1, ti);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { count = rs.getInt(1); }
	 * 
	 * } catch (Exception e) { throw e; } finally { try { if (pstmt != null)
	 * pstmt.close(); if (conn != null) conn.close(); } catch (Exception e2) { throw
	 * e2; } } return count; }
	 */
	// 게시글 검색하다(아이디, 작성일자, 제목, 내용 검색)
		public ArrayList<BoardVo> selectBoardList(int cateNo, String keyfield, String keyword, int startRow, int postSize)
				throws Exception {
			ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT board_no, user_id, board_title, board_notice,		");
				sql.append("date_format(board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate,board_count   ");
				sql.append("FROM board join horse	");
				sql.append("on(board.horse_no = horse.horse_no)	");
				sql.append("WHERE (cate_no= ?) and	" + keyfield + "	LIKE CONCAT('%', ?, '%')  ");
				sql.append("ORDER BY board_wdate DESC	");
				sql.append("LIMIT ? OFFSET ?	");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, cateNo);
				pstmt.setString(2, keyword);
				pstmt.setInt(3, postSize);
				pstmt.setInt(4, startRow);
				rs = pstmt.executeQuery();

	////int boardNo, String boardTitle, int boardCount, String boardWdate, boolean boardNotice, String userId
				while (rs.next()) {
					BoardVo board = new BoardVo();
					board.setBoardNo(rs.getInt(1));
					board.setUserId(rs.getString(2));
					board.setBoardTitle(rs.getString(3));
					board.setBoardNotice(rs.getInt(4));
					board.setBoardWdate(rs.getString(5));
					board.setBoardCount(rs.getInt(6));
					boardList.add(board);

				}
				return boardList;

			} catch (Exception e) {
				throw e;
			} finally {
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
	
	// 게시글 검색하다(전체 검색시)
		public ArrayList<BoardVo> selectBoardList(int cateNo, String keyword, int startRow, int postSize) throws Exception {
			ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT board_no, user_id, board_title, board_notice,		");
				sql.append("date_format(board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate,board_count   ");
				sql.append("FROM board join horse	");
				sql.append("on(board.horse_no = horse.horse_no)	");
				sql.append("WHERE (cate_no= ?) and ((board_title LIKE CONCAT('%', ?, '%')) or  ");
				sql.append("(board_content LIKE CONCAT('%', ?, '%')) or  ");
				sql.append("(board_wdate LIKE CONCAT('%', ?, '%')) or  ");
				sql.append("(user_id LIKE CONCAT('%', ?, '%')) or  ");
				sql.append("(horse LIKE CONCAT('%', ?, '%')))  ");
				sql.append("ORDER BY board_wdate DESC	");
				sql.append("LIMIT ? OFFSET ?	");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, cateNo);
				pstmt.setString(2, keyword);
				pstmt.setString(3, keyword);
				pstmt.setString(4, keyword);
				pstmt.setString(5, keyword);
				pstmt.setString(6, keyword);
				pstmt.setInt(7, postSize);
				pstmt.setInt(8, startRow);
				rs = pstmt.executeQuery();

				//// int boardNo, String boardTitle, int boardCount, String boardWdate, boolean
				//// boardNotice, String userId
				while (rs.next()) {
					BoardVo board = new BoardVo();
					board.setBoardNo(rs.getInt(1));
					board.setUserId(rs.getString(2));
					board.setBoardTitle(rs.getString(3));
					board.setBoardNotice(rs.getInt(4));
					board.setBoardWdate(rs.getString(5));
					board.setBoardCount(rs.getInt(6));
					boardList.add(board);

				}
				return boardList;

			} catch (Exception e) {
				throw e;
			} finally {
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
	// 말머리별 게시글 검색하다
		public ArrayList<BoardVo> selectHorseBoardList(int cateNo, int horseNo, String keyword, int startRow,
				int postSize) throws Exception {
			ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT board_no, user_id, board_title, board_notice,		");
				sql.append("date_format(board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate,board_count   ");
				sql.append("FROM board join horse	");
				sql.append("on(board.horse_no = horse.horse_no)	");
				sql.append("WHERE (cate_no= ?) and	(board.horse_no=  ? ) and ((board_title LIKE CONCAT('%', ?, '%')) or  ");
				sql.append("(board_content LIKE CONCAT('%', ?, '%')) or  ");
				sql.append("(user_id LIKE CONCAT('%', ?, '%')))  ");
				sql.append("ORDER BY board_wdate DESC	");
				sql.append("LIMIT ? OFFSET ?	");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, cateNo);
				pstmt.setInt(2, horseNo);
				pstmt.setString(3, keyword);
				pstmt.setString(4, keyword);
				pstmt.setString(5, keyword);
				pstmt.setInt(6, postSize);
				pstmt.setInt(7, startRow);
				rs = pstmt.executeQuery();

				//// int boardNo, String boardTitle, int boardCount, String boardWdate, boolean
				//// boardNotice, String userId
				while (rs.next()) {
					BoardVo board = new BoardVo();
					board.setBoardNo(rs.getInt(1));
					board.setUserId(rs.getString(2));
					board.setBoardTitle(rs.getString(3));
					board.setBoardNotice(rs.getInt(4));
					board.setBoardWdate(rs.getString(5));
					board.setBoardCount(rs.getInt(6));
					boardList.add(board);

				}
				return boardList;

			} catch (Exception e) {
				throw e;
			} finally {
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

	//게시글 상세조회
		public BoardVo selectBoard(int boardNo) throws Exception{
			BoardVo board = new BoardVo();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBConn.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append("select b.user_id, u.user_nick, h.horse, b.board_title, b.board_content,b.board_no,	");
				sql.append("date_format(b.board_wdate, '%Y/%m/%d %h:%i:%s') as board_wdate, b.board_count, b.api_x, b.api_y,	");
				sql.append("f.boardfile_origin, f.boardfile_sys, f.boardfile_size	");
				sql.append("from board as b left join boardfile as f	");
				sql.append("on b.board_no = f.board_no	");
				sql.append("left join horse as h  ");
				sql.append("on b.horse_no = h.horse_no	");
				sql.append("left join user_info as u  ");
				sql.append("on b.user_id = u.user_id	");
				sql.append("where b.board_no = ?");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setInt(1, boardNo);
				
				rs = pstmt.executeQuery();
				
				boolean isFirst = true;
				//while문을 돌리는 이유: 업로드된 파일이 여러개 존재할 수 있으니까!
				while(rs.next()) {
					if(isFirst) {
						board.setUserId(rs.getString(1));
						board.setUserNick(rs.getString(2));
						board.setHorse(rs.getString(3));
						board.setBoardTitle(rs.getString(4));
						board.setBoardContent(rs.getString(5));
						board.setBoardNo(rs.getInt(6));
						board.setBoardWdate(rs.getString(7));
						board.setBoardCount(rs.getInt(8));
						board.setApiX(rs.getString(9));
						board.setApiY(rs.getString(10));
						isFirst = false;
					}
					//첨부 파일 정보가 존재하지 않을 경우
					
					if(rs.getString(11) != null) {
						BoardFileVo file = new BoardFileVo();
						file.setBoardfileOrigin(rs.getString(11));
						file.setBoardfileSys(rs.getString(12));
						file.setBoardfileSize(rs.getInt(13));
						board.addBoardFile(file);
					}
					
					
				}
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if( rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e2) {
					throw e2;
				}
			}
			return board;
		}
	// 게시판 상세보기
		/*
		 * //public BoardVo selectBoard(int boardNo) throws Exception { Connection conn
		 * = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { conn = DBConn.getConnection(); StringBuffer sql = new StringBuffer();
		 * sql.append(
		 * "SELECT b.board_no, b.horse_no, b.board_title, b.user_id, b.board_count,							"
		 * ); sql.append(
		 * "date_format(b.board_wdate, '%Y/%m/%d %H:%i:%S') as writedate, b.board_content,  b.board_count, b.board_count, 		"
		 * ); sql.append(
		 * "b.api_x, b.api_y , f.boardfile_origin, f.boardfile_sys, f.boardfile_size , f.boardfile_no						"
		 * ); sql.append(
		 * "FROM movie.board as b LEFT JOIN movie.boardFile as f	 										"
		 * ); sql.append(
		 * "ON b.board_no = f.board_no	 																	"
		 * ); sql.append("WHERE b.board_no = ?"); pstmt =
		 * conn.prepareStatement(sql.toString());
		 * 
		 * pstmt.setInt(1, boardNo);
		 * 
		 * rs = pstmt.executeQuery();
		 * 
		 * BoardVo board = new BoardVo(); boolean isFirst = true; while (rs.next()) { if
		 * (isFirst) { board.setBoardNo(rs.getInt(1)); board.setHorseNo(rs.getInt(2));
		 * board.setBoardTitle(rs.getString(3)); board.setUserId(rs.getString(4));
		 * board.setHitCount(rs.getInt(5)); board.setBoardWdate(rs.getString(6));
		 * board.setBoardContent(rs.getString(7)); board.setRecomCount(rs.getInt(8)); //
		 * 추천수와 댓글 수 수정해야된다 (테스트용) board.setCommentCount(rs.getInt(9));
		 * board.setApiX(rs.getString(10)); board.setApiY(rs.getString(11));
		 * 
		 * isFirst = false; } if (rs.getString(12) != null) { BoardFileVo boardfileList
		 * = new BoardFileVo(); boardfileList.setBoardfileOrigin(rs.getString(12));
		 * boardfileList.setBoardfileSys(rs.getString(13));
		 * boardfileList.setBoardfileSize(rs.getInt(14));
		 * boardfileList.setBoardfileNo(rs.getInt(15));
		 * board.addBoardFile(boardfileList); }
		 * 
		 * }
		 * 
		 * return board;
		 * 
		 * } catch (Exception e) { throw e; } finally { try { if (rs != null)
		 * rs.close(); if (pstmt != null) pstmt.close(); if (conn != null) conn.close();
		 * } catch (Exception e2) { throw e2; } }
		 * 
		 * }
		 */

	// 영화관람 팁 게시판상세조회
	public BoardVo selectTipBoard(int boardNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT b.board_no, b.horse_no, b.board_title, b.user_id, b.board_count,			");
			sql.append(
					"date_format(b.board_wdate, '%Y/%m/%d %H:%i:%S') as writedate, b.board_content,  b.board_count, b.board_count,			");
			sql.append("l.api_x, l.api_y										");
			sql.append("FROM movie.board as b LEFT JOIN movie.location as l								");
			sql.append("ON b.board_no = l.board_no											");
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
					board.setBoardCount(rs.getInt(5));
					board.setBoardWdate(rs.getString(6));
					board.setBoardContent(rs.getString(7));
					board.setRecomCount(rs.getInt(8)); // 추천수와 댓글 수 수정해야된다 (테스트용)
					board.setCommentCount(rs.getInt(9));

					isFirst = false;
				}
				if (rs.getString(10) != null) {
					BoardLocationVo boardLocationList = new BoardLocationVo();
					boardLocationList.setApiX(rs.getString(10));
					boardLocationList.setApiY(rs.getString(11));
					board.addBoardLocation(boardLocationList);

				}
			}
			return board;
		} catch (Exception e) {
			throw e;
		} finally {
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

}
