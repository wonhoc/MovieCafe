package controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class SearchBoardCommand implements Command {
	private static final int POST_PER_PAGE = 10; // ���������� ������ �Խñ� ����
	private static final int PAGE_BLOCK = 5; // ���������� ��� �������� �����ٰ�����

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		System.out.println("ī�׹�ȣ" + cateNo);
		String keyfield = request.getParameter("keyfield");
		System.out.println("Ű�ʵ�" + keyfield);
		String keyword = request.getParameter("keyword");
		System.out.println("Ű����" + keyword);
		/*
		 * try { ArrayList<BoardVo> boardList =
		 * BoardService.getInstance().findBoardList(cateNo, keyfield, keyword);
		 * request.setAttribute("boardList", boardList); return new
		 * ActionForward("/board/findBoard.jsp", false); } catch (Exception e) {
		 * e.printStackTrace(); //request.setAttribute("exception", e);
		 * //RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
		 * //dispatcher.forward(request, response); return null; }
		 * 
		 * }
		 */
		// ����¡ ó��
		// int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		// 1. ���� ������ ��ȣ�� ���Ѵ�.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		// 2. ���� �������� ������ �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
		int startRow = (currentPage - 1) * POST_PER_PAGE; // mysql�� ���� 0���� �����ϴϱ� �� �ڿ� +1���ص���

		// 3. DB���� �Խñ� ����� ��ȸ�Ѵ�.
		ArrayList<BoardVo> boards = new ArrayList<BoardVo>();
		if (keyfield.equals("all")) {
			boards = BoardService.getInstance().findBoardList(cateNo, keyword, startRow, POST_PER_PAGE);
			// �����
			System.out.println("all find��� " + boards.size());
		} else if (keyfield.equals("user_id") || keyfield.equals("board_title") || keyfield.equals("board_content")) {
			boards = BoardService.getInstance().findBoardList(cateNo, keyfield, keyword, startRow, POST_PER_PAGE);
			// �����
			System.out.println("id find��� " + boards.size());
		} else { // ���Ӹ� �˻�
			int horseNo = Integer.parseInt(keyfield);

			boards = BoardService.getInstance().findHorseBoardList(cateNo, horseNo, keyword, startRow, POST_PER_PAGE);
			// �����
			System.out.println("���Ӹ��� find��� " + boards.size());
		}

		// 4. request ������ "boards"��� �Ӽ��̸����� �Խñ� ����� �����Ѵ�.
		// �ֳĸ� �̰� listBoard.jsp���� �����ְ� �Ⱦ� �Ӽ��̴ϱ� requset������ ����
		request.setAttribute("boards", boards);

		// 5. block�� ���Ѵ�.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
		// ?���������� ���ΰ�� �� : �� �� ���̽���.

		// 6. ���� �������� ���� ����������� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		// 7. �� �Խñ� ���� ���Ѵ�.
		int totalPostCount = boards.size();
		System.out.println(totalPostCount);
		// 8. �� ������ ���� ���Ѵ�.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE
				: totalPostCount / POST_PER_PAGE + 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 9. request ������ ������ ������ �����Ѵ�.
		request.setAttribute("cateNo", cateNo);
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalPostCount", totalPostCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		// list.board.jsp�� �̵�~
		return new ActionForward("/indexControl.jsp?contentTemplate=/board/findBoard&currentPage=" + currentPage, false);
	}

}
