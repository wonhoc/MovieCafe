package domain.board;

import java.util.ArrayList;


public class BoardVo {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardCount;
	private String boardWdate;
	private boolean boardNotice;
	private String userId;
	private int horseNo;
	private int commentCount;
	private int recomCount;
	private String x;
	private String y;
	
	public BoardVo() {
		super();
	}

	public BoardVo(int boardNo, String boardTitle, String boardContent, int boardCount, String boardWdate,
			boolean boardNotice, String userId, int horseNo,
			int commentCount, int recomCount, String x, String y) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardWdate = boardWdate;
		this.boardNotice = boardNotice;
		this.userId = userId;
		this.horseNo = horseNo;
		this.commentCount = commentCount;
		this.recomCount = recomCount;
		this.x = x;
		this.y = y;
	}

	
	public BoardVo(int boardNo, String boardTitle, int boardCount, String boardWdate, boolean boardNotice, String userId) 
	{
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCount = boardCount;
		this.boardWdate = boardWdate;
		this.boardNotice = boardNotice;
		this.userId = userId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public String getBoardWdate() {
		return boardWdate;
	}

	public void setBoardWdate(String boardWdate) {
		this.boardWdate = boardWdate;
	}

	public boolean isBoardNotice() {
		return boardNotice;
	}

	public void setBoardNotice(boolean boardNotice) {
		this.boardNotice = boardNotice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getHorseNo() {
		return horseNo;
	}

	public void setHorseNo(String horse) {
		this.horseNo = horseNo;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getRecomCount() {
		return recomCount;
	}

	public void setRecomCount(int recomCount) {
		this.recomCount = recomCount;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	
	
}
