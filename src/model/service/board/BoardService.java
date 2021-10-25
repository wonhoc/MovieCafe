package model.service.board;

import java.sql.Connection;
import java.util.*;

import domain.board.BoardFileVo;
import domain.board.BoardVo;
import model.DBConn;
import model.dao.board.BoardDao;
import model.dao.board.BoardFileDao;

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
	
	//게시글 목록조회
	public List<BoardVo> retrieveBoardList(int startRow, int postSize) throws Exception {
		return BoardDao.getInstance().selectBoardList(startRow, postSize);
	}
	
	//게시글 찾기
	public List<BoardVo> findBoardList(String keyfield, String keyword) throws Exception{
		return BoardDao.getInstance().selectBoardList(keyfield, keyword);
	}
	
	//총 게시글 수
	public int retrieveTotalPostCount() throws Exception {
		return BoardDao.getInstance().selectTotalPostCount();
	}
	//게시글 상세조회
	public BoardVo detailBoard(int boardNo) throws Exception{
		return BoardDao.getInstance().selectBoard(boardNo);
		
	}

	//게시글 작성
	public void registerBoard(BoardVo board) throws Exception{
		Connection conn = null;
		boolean isSuccess = false;
		
		try {
			conn = DBConn.getConnection();
		
		//시작
			conn.setAutoCommit(false);
		
			int no = BoardDao.getInstance().insertBoard(board, conn);
			
			for(BoardFileVo file : board.getBoardfileList()) {
				file.setBoardNo(no);
				BoardFileDao.getInstance().insertBoardFile(file, conn);
			}
			
			
			
			isSuccess = true;
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) {
					if(isSuccess)
					conn.commit();
				}else {
					conn.rollback();
				}
				conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
		
	}
	
	//게시글 수정
	public void modifyBoard(BoardVo board) throws Exception{
		
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			BoardDao boardDao = BoardDao.getInstance();
			boardDao.updateBoard(board, conn);
			
			BoardFileDao fileDao = BoardFileDao.getInstance();
			for(BoardFileVo file : board.getBoardfileList()) {
				file.setBoardNo(board.getBoardNo());
				fileDao.insertBoardFile(file, conn);
				
			}
			isSuccess = true;
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) {
					if (isSuccess) {
						conn.commit();	
					}else {
						conn.rollback();
				}
			}
				
				
		}catch (Exception e2) {
			throw e2;
		}
		
		
	}
	
}
	
	//게시글 삭제
	public void removeBoard(int boardNo) throws Exception{
		
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			//파일삭제
			BoardFileDao boardFileDao = BoardFileDao.getInstance();
			boardFileDao.deleteFile(boardNo, conn);
			
			//게시글 삭제
			BoardDao boardDao = BoardDao.getInstance();
			boardDao.deleteBoard(boardNo, conn);
			
			isSuccess = true;
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
						}
				
			}
			}catch (Exception e2) {
				throw e2;
			}
			
		}
		
		}
	}
	
	
	
	
