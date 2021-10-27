package domain.board;

public class CommentVo {
	private int comNo;
	private String comContent;
	private String comWdate;
	private String userId;
	private String userNick;
	private int boardNo;
	
	public CommentVo() {
		super();
	}

	public CommentVo(int comNo, String comContent, String comWdate, String userId, String userNick, int boardNo) {
		super();
		this.comNo = comNo;
		this.comContent = comContent;
		this.comWdate = comWdate;
		this.userId = userId;
		this.userNick = userNick;
		this.boardNo = boardNo;
	}

	public CommentVo(String userId, String comContent, int boardNo) {
		super();
		this.comContent = comContent;
		this.userId = userId;
		this.boardNo = boardNo;
	}

	
	public CommentVo(int comNo, String comContent) {
		super();
		this.comNo = comNo;
		this.comContent = comContent;
	}

	public CommentVo(String comContent, String comWdate, String userId) {
		super();
		this.comContent = comContent;
		this.comWdate = comWdate;
		this.userId = userId;
	}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComWdate() {
		return comWdate;
	}

	public void setComWdate(String comWdate) {
		this.comWdate = comWdate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
}
