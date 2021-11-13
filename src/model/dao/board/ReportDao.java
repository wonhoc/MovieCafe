package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.board.ReportVo;
import model.DBConn;

public class ReportDao {
	private static ReportDao reportDao;

	private ReportDao() {

	}

	public static ReportDao getInstance() {
		if (reportDao == null) {
			reportDao = new ReportDao();
		}
		return reportDao;
	}
	
	
		public void insertReportBoard(ReportVo report) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {

				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO report (board_no,user_id) ");
				sql.append("VALUES (? , ?) ");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, report.getBoardNo());
				pstmt.setString(2, report.getReportedId());
				
				pstmt.executeUpdate();

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
		
}
