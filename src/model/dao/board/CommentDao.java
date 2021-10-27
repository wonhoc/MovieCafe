package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.board.CommentVo;
import model.DBConn;

public class CommentDao {
	private static CommentDao commentDao;

	private CommentDao() {

	}

	public static CommentDao getInstance() {
		if (commentDao == null) {
			commentDao = new CommentDao();
		}
		return commentDao;
	}
	
	
	// ´ñ±ÛÀ» Á¶È¸ÇÏ´Ù.
	public List<CommentVo> selectCommentList(int boardNo) throws Exception {
		List<CommentVo> commentList = new ArrayList<CommentVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT com_no, board_no, user_id, com_content,                    ");
			sql.append("DATE_FORMAT(com_wdate, '%Y/%m/%d') as com_wdate   ");
			sql.append("FROM comment    ");
			sql.append("WHERE board_no = ? 	");
			sql.append("ORDER BY com_wdate ASC");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1,  boardNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentVo comment = new CommentVo();
				comment.setComNo(rs.getInt(1));
				comment.setBoardNo(rs.getInt(2));
				comment.setUserId(rs.getString(3));
				comment.setComContent(rs.getString(4));
				comment.setComWdate(rs.getString(5));
				commentList.add(comment);
			}

			System.out.println("´ñ Á¶È¸ ¿Ï·á" + commentList.size());
			return commentList;

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

	public void insertComment(CommentVo comment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO comment (board_no, user_id, com_content, com_wdate) ");
			sql.append("VALUES (?, ?, ?, now()) ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setString(2, comment.getUserId());
			pstmt.setString(3, comment.getComContent());

			pstmt.executeUpdate();

			System.out.println("´ñ insert¿Ï·á" + comment.getComContent());
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}

	}
	
	//´ñ±Û »èÁ¦
	public void deleteComment(int no) throws Exception {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM comment      ");		
			sql.append("WHERE com_no = ?");			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}

	
	}
	
	//´ñ±Û ¼öÁ¤
	public void updateComment(CommentVo comment) throws Exception {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE comment      ");
			sql.append("SET com_content = ?     ");
			sql.append("WHERE com_no = ?");			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, comment.getComContent());
			pstmt.setInt(2, comment.getComNo());
			
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}	
	}

	
	//´ñ±Û¼ö¸¦ ±¸ÇÏ´Ù.
	public int selectCommCnt(int boardNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select count(*)  ");
			sql.append("from comment left join board	");
			sql.append("using(board_no) ");
			sql.append("where board_no = ?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
			cnt = rs.getInt(1);
			}
			System.out.println(boardNo+"´ñ±Û¼ö Á¶È¸ ¿Ï·á" + cnt);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if(rs != null) rs.close();
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw e2;
			}
		}
		return cnt;
	}
	
}
