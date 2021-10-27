package domain.board;

import java.util.ArrayList;

public class BoardVo {

	private int boardNo;
	private String userId;
	private String boardTitle;
	private String boardWdate;
	private String boardContent;
	private int boardCount;
	private int recomCount;
	private int commentCount;
	private int cateNo;
	private int horseNo;
	private String horse;
	private int boardNotice;
	private String userNick;
	private String apiX;
	private String apiY;

	private ArrayList<BoardFileVo> boardfileList = new ArrayList<BoardFileVo>();
	private ArrayList<BoardLocationVo> boardLocationList = new ArrayList<BoardLocationVo>();
	private ArrayList<CommentVo> commentList = new ArrayList<CommentVo>();

	public BoardVo() {
		super();
	}

	public BoardVo(String userId, String boardTitle, String boardContent, int horseNo, String apiX, String apiY) {
		super();
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.horseNo = horseNo;
		this.apiX = apiX;
		this.apiY = apiY;
	}


	

	public BoardVo(int boardNo, String userId, String boardTitle, String boardWdate, String boardContent,
			int boardCount, int recomCount, int commentCount, int cateNo, int horseNo, String horse, int boardNotice,
			String userNick, String apiX, String apiY, ArrayList<BoardFileVo> boardfileList,
			ArrayList<BoardLocationVo> boardLocationList, ArrayList<CommentVo> commentList) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardWdate = boardWdate;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.recomCount = recomCount;
		this.commentCount = commentCount;
		this.cateNo = cateNo;
		this.horseNo = horseNo;
		this.horse = horse;
		this.boardNotice = boardNotice;
		this.userNick = userNick;
		this.apiX = apiX;
		this.apiY = apiY;
		this.boardfileList = boardfileList;
		this.boardLocationList = boardLocationList;
		this.commentList = commentList;
	}

	public BoardVo(String userId, String boardTitle, String boardContent, int horseNo) {
		super();
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.horseNo = horseNo;
	}

	

	public BoardVo(int boardNo, String boardTitle, int boardCount, String boardWdate,int boardNotice, String userId
			) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardWdate = boardWdate;
		this.boardCount = boardCount;
		this.boardNotice = boardNotice;
	}

	
	
	
	
	
	public BoardVo(int boardNo, String boardTitle, String userNick, String boardWdate, int boardCount, int cateNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWdate = boardWdate;
		this.boardCount = boardCount;
		this.cateNo = cateNo;
		this.userNick = userNick;
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

	

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
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

	public String getApiX() {
		return apiX;
	}

	public void setApiX(String apiX) {
		this.apiX = apiX;
	}

	public String getApiY() {
		return apiY;
	}

	public void setApiY(String apiY) {
		this.apiY = apiY;
	}

	public ArrayList<BoardFileVo> getBoardfileList() {
		return boardfileList;
	}

	public void setBoardfileList(ArrayList<BoardFileVo> boardfileList) {
		this.boardfileList = boardfileList;

	}

	
	
	
	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
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

	public ArrayList<CommentVo> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<CommentVo> commentList) {
		this.commentList = commentList;
	}

	public ArrayList<BoardLocationVo> getBoardLocationList() {
		return boardLocationList;
	}

	public void setBoardLocationList(ArrayList<BoardLocationVo> boardLocationList) {
		this.boardLocationList = boardLocationList;
	}

	

	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", userId=" + userId + ", boardTitle=" + boardTitle + ", boardWdate="
				+ boardWdate + ", boardContent=" + boardContent + ", boardCount=" + boardCount + ", recomCount="
				+ recomCount + ", commentCount=" + commentCount + ", cateNo=" + cateNo + ", horseNo=" + horseNo
				+ ", horse=" + horse + ", boardNotice=" + boardNotice + ", userNick=" + userNick + ", apiX=" + apiX
				+ ", apiY=" + apiY + ", boardfileList=" + boardfileList + ", boardLocationList=" + boardLocationList
				+ ", commentList=" + commentList + "]";
	}

	public void addBoardFile(BoardFileVo boardFile) {
		this.boardfileList.add(boardFile);
	}

	public void addBoardLocation(BoardLocationVo boardLocation) {
		this.boardLocationList.add(boardLocation);
	}
	public void addComment(CommentVo comment) {
		this.commentList.add(comment);
	}

}