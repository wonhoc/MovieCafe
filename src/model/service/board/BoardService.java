package model.service.board;

import java.sql.Connection;
import java.util.*;

import domain.board.BoardFileVo;
import domain.board.BoardLocationVo;
import domain.board.BoardVo;
import domain.board.RecomVo;
import model.DBConn;
import model.dao.board.BoardDao;
import model.dao.board.BoardFileDao;
import model.dao.board.BoardLocationDao;
import model.dao.board.RecomDao;

public class BoardService {

	private static BoardService service;

	private BoardService() {

	}

	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardService();
		}
		return service;
	}

	// 게시글 목록을 조회하다.
	public ArrayList<BoardVo> retrieveBoardList(int cateNo, int startRow, int postSize) throws Exception {
		BoardDao boardDao = BoardDao.getInstance();

		// return boardDao.selectBoardList(cateNo,startRow, postSize);
		// 아래는 디버깅용
		ArrayList<BoardVo> boards = boardDao.selectBoardList(cateNo, startRow, postSize);
		System.out.println("retrieve 개수: " + boards.size());
		return boards;
	}
	
	// 게시글 목록
	// public List<BoardVo> retrieveBoardList(int startRow, int postSize) throws
	// Exception {
	// return BoardDao.getInstance().selectBoardList(startRow, postSize);
	// }

	// 게시글을 검색하다.(keyfield 있을 때)
	public ArrayList<BoardVo> findBoardList(int cateNo, String keyfield, String keyword, int startRow, int postSize)
			throws Exception {
		ArrayList<BoardVo> boards = BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword, startRow,
				postSize);
		System.out.println("findboard결과 " + boards.size());
		// return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
		return boards;
	}

	// 게시글을 검색하다.(전체 검색시)
	public ArrayList<BoardVo> findBoardList(int cateNo, String keyword, int startRow, int postSize) throws Exception {
		ArrayList<BoardVo> boards = BoardDao.getInstance().selectBoardList(cateNo, keyword, startRow, postSize);
		System.out.println("all findboard결과 " + boards.size());
		// return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
		return boards;
	}

	// 게시글을 검색하다.(말머리별 검색)
	public ArrayList<BoardVo> findHorseBoardList(int cateNo, int horseNo, String keyword, int startRow, int postSize)
			throws Exception {
		ArrayList<BoardVo> boards = BoardDao.getInstance().selectHorseBoardList(cateNo, horseNo, keyword, startRow,
				postSize);
		System.out.println("findHorseboard결과 " + boards.size());
		// return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
		return boards;
	}

	// 총 게시글 수를 구하다.
	public int retrieveTotalPostCount(int cate_no) throws Exception {
		return BoardDao.getInstance().selectTotalPostCount(cate_no);
	}

	// 총게시글
	/*
	 * public int retrieveTotalPostCount() throws Exception { return
	 * BoardDao.getInstance().selectTotalPostCount(); }
	 */
	// 상세보기
	// public BoardVo detailBoard(int boardNo) throws Exception {
	// return BoardDao.getInstance().selectBoard(boardNo);

	//}

//게시글을 상세조회하다.
	public BoardVo detailBoard(int boardNo) throws Exception {
		BoardDao boardDao = BoardDao.getInstance();
		BoardVo board = boardDao.selectBoard(boardNo);
		System.out.println("service 상세보기 완료. 댓글 개수는 " + board.getCommentList().size());
		return board;
	}

//게시글을 추천하다. recomBoard
	public void recomBoard(RecomVo recom) throws Exception {
		RecomDao recomDao = RecomDao.getInstance();
		recomDao.insertRecommend(recom);
		System.out.println("service 추천 완료 ");

	}

	// 영화관람 팁 등록
	public void registerBoardLocation(BoardVo board) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;

		try {
			conn = DBConn.getConnection();

			conn.setAutoCommit(false);

			int no = BoardDao.getInstance().insertLocationBoard(board, conn);

			for (BoardFileVo file : board.getBoardfileList()) {
				file.setBoardNo(no);
				BoardFileDao.getInstance().insertBoardFile(file, conn);
			}

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				if (isSuccess) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
		}
	}

	// 게시글 등록
	public void registerBoard(BoardVo board) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;

		try {
			conn = DBConn.getConnection();

			conn.setAutoCommit(false);

			int no = BoardDao.getInstance().insertBoard(board, conn);

			for (BoardFileVo file : board.getBoardfileList()) {
				file.setBoardNo(no);
				BoardFileDao.getInstance().insertBoardFile(file, conn);
			}

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess)
						conn.commit();
				} else {
					conn.rollback();
				}
				conn.close();
			} catch (Exception e2) {
				throw e2;
			} finally {

			}
		}

	}

	// �Խñ� ����
	public void modifyBoard(BoardVo board) throws Exception {

		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			BoardDao boardDao = BoardDao.getInstance();
			boardDao.updateBoard(board, conn);

			BoardFileDao fileDao = BoardFileDao.getInstance();
			for (BoardFileVo file : board.getBoardfileList()) {
				file.setBoardNo(board.getBoardNo());
				fileDao.insertBoardFile(file, conn);

			}
			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}

			} catch (Exception e2) {
				throw e2;
			}

		}

	}

	// 파일삭제
	public void removeFile(int boardNo) throws Exception {

		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			//
			BoardFileDao boardFileDao = BoardFileDao.getInstance();
			boardFileDao.deleteFile(boardNo, conn);

			isSuccess = true;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}

				}
			} catch (Exception e2) {
				throw e2;
			}
		}

	}

	// retrieveTotalMyPostCount 나의ㅣ 게시글 총 수를 구한다
	public int retrieveTotalMyPostCount(String userId) throws Exception {
		return BoardDao.getInstance().selectTotalMyPostCount(userId);
	}

	// 게시판 삭제
	public void removeBoard(int boardNo) throws Exception {

		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			// ���ϻ���
			BoardFileDao boardFileDao = BoardFileDao.getInstance();
			boardFileDao.deleteFile(boardNo, conn);

			// �Խñ� ����
			BoardDao boardDao = BoardDao.getInstance();
			boardDao.deleteBoard(boardNo, conn);

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}

				}
			} catch (Exception e2) {
				throw e2;
			}

		}

	}
}
