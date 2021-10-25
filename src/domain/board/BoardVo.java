package domain.board;

import java.util.ArrayList;

public class BoardVo {

	private int boardNo;
	private String userId;
	private String boardTitle;
	private String boardWdate;
	private String boardContent;
	private int hitCount;
	private int recomCount;
	private int commentCount;
	private int horseNo;
	private int boardNotice;
	
	private ArrayList<BoardFileVo> boardfileList = new ArrayList<BoardFileVo>();
	
	public BoardVo() {
		super();
	}

	public BoardVo(String userId, String boardTitle, String boardWdate, int hitCount, int recomCount,
			int commentCount) {
		super();
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardWdate = boardWdate;
		this.hitCount = hitCount;
		this.recomCount = recomCount;
		this.commentCount = commentCount;
	}

	
	
	

	
	public BoardVo(int boardNo, String userId, String boardTitle, String boardWdate, String boardContent, int hitCount,
			int recomCount, int commentCount, int horseNo) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardWdate = boardWdate;
		this.boardContent = boardContent;
		this.hitCount = hitCount;
		this.recomCount = recomCount;
		this.commentCount = commentCount;
		this.horseNo = horseNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWdate() {
		return boardWdate;
	}

	public void setBoardWdate(String boardWdate) {
		this.boardWdate = boardWdate;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public int getRecomCount() {
		return recomCount;
	}

	public void setRecomCount(int recomCount) {
		this.recomCount = recomCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getHorseNo() {
		return horseNo;
	}

	public void setHorseNo(int horseNo) {
		this.horseNo = horseNo;
	}

	
	
	
	
	
	
	
	

	public int getBoardNotice() {
		return boardNotice;
	}

	public void setBoardNotice(int boardNotice) {
		this.boardNotice = boardNotice;
	}

	public ArrayList<BoardFileVo> getBoardfileList() {
		return boardfileList;
	}

	public void setBoardfileList(ArrayList<BoardFileVo> boardfileList) {
		this.boardfileList = boardfileList;
	}

	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", userId=" + userId + ", boardTitle=" + boardTitle + ", boardWdate="
				+ boardWdate + ", boardContent=" + boardContent + ", hitCount=" + hitCount + ", recomCount="
				+ recomCount + ", commentCount=" + commentCount + ", horseNo=" + horseNo + "]";
	}

	public void addBoardFile(BoardFileVo boardFile) {
		this.boardfileList.add(boardFile);
	}
	
	
	
	}