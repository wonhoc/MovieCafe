package domain.board;

import java.util.ArrayList;

public class BoardFileVo {
	private int boardfileNo;
	private String boardfileOrigin;
	private String boardfileSys;
	private long boardfileSize;
	private int boardNo;
	private String boardfileType;
	public BoardFileVo() {
		super();
	}
	public BoardFileVo(int boardfileNo, String boardfileOrigin, String boardfileSys, long boardfileSize, int boardNo) {
		super();
		this.boardfileNo = boardfileNo;
		this.boardfileOrigin = boardfileOrigin;
		this.boardfileSys = boardfileSys;
		this.boardfileSize = boardfileSize;
		this.boardNo = boardNo;
	}
	public BoardFileVo(String boardfileOrigin, String boardfileSys, long boardfileSize) {
		super();
		this.boardfileOrigin = boardfileOrigin;
		this.boardfileSys = boardfileSys;
		this.boardfileSize = boardfileSize;
	}
	
	
	
	public BoardFileVo(int boardfileNo, String boardfileOrigin, String boardfileSys, long boardfileSize, int boardNo,
			String boardfileType) {
		super();
		this.boardfileNo = boardfileNo;
		this.boardfileOrigin = boardfileOrigin;
		this.boardfileSys = boardfileSys;
		this.boardfileSize = boardfileSize;
		this.boardNo = boardNo;
		this.boardfileType = boardfileType;
	}
	public int getBoardfileNo() {
		return boardfileNo;
	}
	public void setBoardfileNo(int boardfileNo) {
		this.boardfileNo = boardfileNo;
	}
	public String getBoardfileOrigin() {
		return boardfileOrigin;
	}
	public void setBoardfileOrigin(String boardfileOrigin) {
		this.boardfileOrigin = boardfileOrigin;
	}
	public String getBoardfileSys() {
		return boardfileSys;
	}
	public void setBoardfileSys(String boardfileSys) {
		this.boardfileSys = boardfileSys;
	}
	public long getBoardfileSize() {
		return boardfileSize;
	}
	public void setBoardfileSize(long boardfileSize) {
		this.boardfileSize = boardfileSize;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardfileType() {
		return boardfileType;
	}
	public void setBoardfileType(String boardfileType) {
		this.boardfileType = boardfileType;
	}
	
	
	
	




}
