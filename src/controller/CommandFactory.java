package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;

	private Map<String, String> map = new HashMap<String, String>();

	// constructor
	private CommandFactory() {
		map.put("/board/listBoard.do", "controller.board.listBoardCommand");

		// 게시판리스트
		// 영화리뷰 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_MovieReview.do", "controller.board.ListBoardCommand");
		// 시사회정보 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_sisa.do", "controller.board.ListBoardCommand");
		// 영화관람팁 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_Tip.do", "controller.board.ListBoardCommand");
		// 티켓마켓 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_Ticket.do", "controller.board.ListBoardCommand");
		// 이벤트 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_Event.do", "controller.board.ListBoardCommand");
		// 새싹게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_NewMem.do", "controller.board.ListBoardCommand");

		// 검색
		// 게시글 검색 요청
		map.put("/board/searchBoard.do", "controller.board.SearchBoardCommand");
		// 게시글 상세조회ㅐ 요청
		map.put("/detailBoard.do", "controller.board.DetailBoardCommand");

		// 댓글
		// 댓글 작성 요청
		map.put("/board/writeComment.do", "controller.board.WriteCommentCommand");
		// 댓글 삭제 요청
		map.put("/board/removeComment.do", "controller.board.RemoveCommentCommand");
		// 댓글 수정 요청
		map.put("/board/modifyComment.do", "controller.board.ModifyCommentCommand");

		// 추천 리뷰와 공지사항 조회 요청(메인)
		map.put("/board/recomReview.do", "controller.board.RecomReviewListCommand");

		// 부가기능
		// 게시글 추천 요청
		map.put("/board/recomBoard.do", "controller.board.recomBoardCommand");
		// 게시글 신고 요청
		map.put("/board/reportBoard.do", "controller.board.ReportBoardCommand");

		//게시판 상세보기
		// 게시판 상세보기
		map.put("/board/detailBoard.do", "controller.board.detailBoardCommand");
		// 나의 게시물 보기 요청
		map.put("/board/myBoard.do", "controller.board.MyBoardCommand");
		// 게시글 상세조회ㅐ 요청
		map.put("/board/detailBoard.do", "controller.board.DetailBoardCommand");

		
		// 영화관람 팁 상세보기
		map.put("/board/tipDetailBoard.do", "controller.board.TipDetailBoardCommand");

		// 게시판 작성
		map.put("/board/writeBoardForm.do", "controller.board.WriteFormCommand");

		// 리뷰게시판
		map.put("/board/reviewWriteForm.do", "controller.board.ReviewWriteFormCommand");

		// 시사회정보게시판
		map.put("/board/previewWriteForm.do", "controller.board.PreviewWriteFormCommand");

		// 등업게시판
		map.put("/board/rankupWriteForm.do", "controller.board.RankupWriteFormCommand");

		// 티켓게시판
		map.put("/board/ticketWriteForm.do", "controller.board.TicketWriteFormCommand");

		// 이벤트게시판
		map.put("/board/eventWriteBoardForm.do", "controller.board.EventWriteFormCommand");

		// �۾��� ��û
		map.put("/board/writeBoard.do", "controller.board.WriteBoardCommand");

		// 게시판 수정
		map.put("/board/modifyBoardForm.do", "controller.board.ModifyBoardFormCommand");

		// 팁게시판 수정
		map.put("/tipModifyBoardForm.do", "controller.board.TipModifyBoardFormCommand");

		// 게시판 삭제
		map.put("/removeBoard.do", "controller.board.removeBoardCommand");

		// 파일삭제
		map.put("/removeFile.do", "controller.board.RemoveFileCommand");

		// �����Խ���
		map.put("/board/tipBoardForm.do", "controller.board.TipBoardFormCommand");

	}

	public static CommandFactory getInstance() {
		if (factory == null) {
			factory = new CommandFactory();
		}
		return factory;
	}

	public Command createCommand(String commandURI) throws Exception {
		String commandClass = map.get(commandURI);
		if (commandClass == null) {
			return null;
		}
		try {
			Class<?> cls = Class.forName(commandClass);
			Constructor<?> constructor = cls.getConstructor(null);
			Command command = (Command) constructor.newInstance();
			return command;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
