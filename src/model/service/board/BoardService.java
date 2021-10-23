package model.service.board;

import java.util.ArrayList;
import java.util.List;

import domain.board.BoardVo;
import model.dao.board.BoardDao;


public class BoardService {

	//싱글톤 패턴
	private static BoardService service;
	
	private BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardService();
		}
		return service;
	}
	
	
	//게시글 목록을 조회하다.
	public ArrayList<BoardVo> retrieveBoardList(int cateNo,int startRow, int postSize) throws Exception {
		BoardDao boardDao = BoardDao.getInstance();
		
		//return boardDao.selectBoardList(cateNo,startRow, postSize);
		//아래는 디버깅용
		ArrayList<BoardVo> boards = boardDao.selectBoardList(cateNo,startRow, postSize);
		System.out.println("retrieve 개수: "+boards.size());
		return boards;
	}
	
	//총 게시글 수를 구하다.
		public int retrieveTotalPostCount(int cate_no) throws Exception{
			return BoardDao.getInstance().selectTotalPostCount(cate_no);
		}
		
		/*
		//검색결과의 총 게시글 수를 구하다
		
		public int retrieveFindTotalPostCount(int cate_no) throws Exception{
			return BoardDao.getInstance().selectTotalPostCount(cate_no);
		}
		*/
		//게시글을 검색하다.(keyfield 있을 때)
		public ArrayList<BoardVo> findBoardList(int cateNo, String keyfield, String keyword, int startRow, int postSize) throws Exception {
			ArrayList<BoardVo> boards = BoardDao.getInstance().selectBoardList(cateNo,keyfield, keyword,startRow, postSize);
			System.out.println("findboard결과 "+boards.size());
			//return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
			return boards;
		}
		//게시글을 검색하다.(전체 검색시)
				public ArrayList<BoardVo> findBoardList(int cateNo, String keyword, int startRow, int postSize) throws Exception {
					ArrayList<BoardVo> boards = BoardDao.getInstance().selectBoardList(cateNo, keyword,startRow, postSize);
					System.out.println("all findboard결과 "+boards.size());
					//return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
					return boards;
				}
				
				//게시글을 검색하다.(말머리별 검색)
				public ArrayList<BoardVo> findHorseBoardList(int cateNo, String keyfield, String keyword, int startRow, int postSize) throws Exception {
					ArrayList<BoardVo> boards = BoardDao.getInstance().selectHorseBoardList(cateNo,keyfield, keyword,startRow, postSize);
					System.out.println("findHorseboard결과 "+boards.size());
					//return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
					return boards;
				}
}
