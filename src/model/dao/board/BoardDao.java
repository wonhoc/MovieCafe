package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.board.BoardFileVo;
import domain.board.BoardVo;
import domain.board.RecomVo;
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

	// 게시글 상세조회
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
				Boolean boardNotice = rs.getBoolean(4);
				String boardWdate = rs.getString(5);
				int boardCnt = rs.getInt(6);
				boards.add(new BoardVo(boardNo, boardTitle, boardCnt, boardWdate, boardNotice, userId));
				System.out.println(boardTitle + userId);
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
				board.setBoardNotice(rs.getBoolean(4));
				board.setBoardWdate(rs.getString(5));
				board.setBoardCount(rs.getInt(6));
				boardList.add(board);

				System.out.println(board.getBoardTitle());
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
				board.setBoardNotice(rs.getBoolean(4));
				board.setBoardWdate(rs.getString(5));
				board.setBoardCount(rs.getInt(6));
				boardList.add(board);

				System.out.println(board.getBoardTitle());
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
	public ArrayList<BoardVo> selectHorseBoardList(int cateNo, String keyfield, String keyword, int startRow,
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
			sql.append("WHERE (cate_no= ?) and	(horse=  ? ) and ((board_title LIKE CONCAT('%', ?, '%')) or  ");
			sql.append("(board_content LIKE CONCAT('%', ?, '%')) or  ");
			sql.append("(user_id LIKE CONCAT('%', ?, '%')))  ");
			sql.append("ORDER BY board_wdate DESC	");
			sql.append("LIMIT ? OFFSET ?	");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, cateNo);
			pstmt.setString(2, keyfield);
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
				board.setBoardNotice(rs.getBoolean(4));
				board.setBoardWdate(rs.getString(5));
				board.setBoardCount(rs.getInt(6));
				boardList.add(board);

				System.out.println(board.getBoardTitle());
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
			sql.append("select u.user_nick, h.horse, b.board_title, b.board_content,b.board_no,	");
			sql.append("date_format(b.board_wdate, '%Y/%m/%d %h:%i:%s') as board_wdate, b.board_count,	");
			sql.append("f.boardfile_origin, f.boardfile_sys, f.boardfile_size, f.boardfile_type	");
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
					board.setUserNick(rs.getString(1));
					board.setHorse(rs.getString(2));
					board.setBoardTitle(rs.getString(3));
					board.setBoardContent(rs.getString(4));
					board.setBoardNo(rs.getInt(5));
					board.setBoardWdate(rs.getString(6));
					board.setBoardCount(rs.getInt(7));
					isFirst = false;
				}
				//첨부 파일 정보가 존재하지 않을 경우
				
				if(rs.getString(7) != null) {
					BoardFileVo file = new BoardFileVo();
					file.setOriginalFileName(rs.getString(7));
					file.setSystemFileName(rs.getString(8));
					file.setFileSize(rs.getInt(9));
					file.setFileType(rs.getString(10));
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
	
	//게시글 조회수를 증가하다.
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
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	
	//추천 리뷰를 조회하다
	public ArrayList<BoardVo> selectRecomRevList() throws Exception{
		
		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.board_no, b.board_title, u.user_nick,  cate_no,                                         ");
			sql.append("DATE_FORMAT(b.board_wdate, '%Y-%m-%d %H:%i:%s') as board_wdate, b.board_count     ");
			sql.append("FROM board as b left join user_info as u		");                                                        
			sql.append("using(user_id)	");
			sql.append("left join horse as h	");
			sql.append("using(horse_no)		");
			sql.append("where cate_no = 2		");
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
	
	//최근 공지글을 조회하다.
	public ArrayList<BoardVo> selectNoticeList() throws Exception{
		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.board_no, b.board_title, u.user_nick,  cate_no,                                      ");
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
	
	
}
