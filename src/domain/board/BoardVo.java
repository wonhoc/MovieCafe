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
	private String horse;
	private String userNick;
	private int cateNo;
	private ArrayList<BoardFileVo> fileList = new ArrayList<BoardFileVo>();
	private ArrayList<CommentVo> commentList = new ArrayList<CommentVo>();

	public BoardVo() {
		super();
	}

	
	public BoardVo(int boardNo, String boardTitle, String boardContent, int boardCount, String boardWdate,
			boolean boardNotice, String userId, int horseNo, int commentCount, int recomCount,
			String horse, String userNick, ArrayList<BoardFileVo> fileList, ArrayList<CommentVo> commentList) {
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
		this.horse = horse;
		this.userNick = userNick;
		this.fileList = fileList;
		this.commentList = commentList;
	}


	public BoardVo(int boardNo, String boardTitle, String boardContent, int boardCount, String boardWdate,
			boolean boardNotice, String userId, int horseNo, int commentCount, int recomCount,
			String horse, String userNick, ArrayList<domain.board.BoardFileVo> fileList) {
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
		this.horse = horse;
		this.userNick = userNick;
		this.fileList = fileList;
	}

	public BoardVo(int boardNo, String boardTitle, String boardContent, int boardCount, String boardWdate,
			boolean boardNotice, String userId, int horseNo,
			int commentCount, int recomCount) {
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

	public BoardVo(int boardNo, String boardTitle, String boardContent, int boardCount, String boardWdate,
			boolean boardNotice, int commentCount, int recomCount, String horse, String userNick) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardWdate = boardWdate;
		this.boardNotice = boardNotice;
		this.commentCount = commentCount;
		this.recomCount = recomCount;
		this.horse = horse;
		this.userNick = userNick;
	}

	
	public BoardVo(String boardTitle, String boardContent, int boardCount, String boardWdate, String horse,
			String userNick, ArrayList<BoardFileVo> fileList, ArrayList<CommentVo> commentList) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardWdate = boardWdate;
		this.horse = horse;
		this.userNick = userNick;
		this.fileList = fileList;
		this.commentList = commentList;
	}

	

	public BoardVo(int boardNo, String boardTitle, String userNick, String boardWdate, int boardCount , int cateNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCount = boardCount;
		this.boardWdate = boardWdate;
		this.userNick = userNick;
		this.cateNo = cateNo;
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

	

	public String getHorse() {
		return horse;
	}

	public void setHorse(String horse) {
		this.horse = horse;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public void setHorseNo(int horseNo) {
		this.horseNo = horseNo;
	}

	public ArrayList<BoardFileVo> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<BoardFileVo> fileList) {
		this.fileList = fileList;
	}
	
	public ArrayList<CommentVo> getCommentList() {
		return commentList;
	}


	public void setCommentList(ArrayList<CommentVo> commentList) {
		this.commentList = commentList;
	}


	public int getCateNo() {
		return cateNo;
	}


	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}


	public void addBoardFile(BoardFileVo file) {
		this.fileList.add(file);
	}
	
	public void addComment(CommentVo comment) {
		this.commentList.add(comment);
	}
}
