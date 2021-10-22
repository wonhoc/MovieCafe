package domain.board;

public class BoardVo {

	private int boardNo;
	private String userId;
	private String boardTitle;
	private String boardWdate;
	private int hitcount;
	private int recomNo;
	private int commentCount;
	
	public BoardVo() {
		super();
	}
	//게시판 리스트
	public BoardVo(int boardNo, String userId, String boardTitle, String boardWdate, int hitcount, int recomNo,
			int commentCount) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardWdate = boardWdate;
		this.hitcount = hitcount;
		this.recomNo = recomNo;
		this.commentCount = commentCount;
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
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	public int getRecomNo() {
		return recomNo;
	}
	public void setRecomNo(int recomNo) {
		this.recomNo = recomNo;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", userId=" + userId + ", boardTitle=" + boardTitle + ", boardWdate="
				+ boardWdate + ", hitcount=" + hitcount + ", recomNo=" + recomNo + ", commentCount=" + commentCount
				+ "]";
	}
	
	
	
	
	
}
