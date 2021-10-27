package domain.board;

public class BoardLocationVo {

	private int boardNo;
	private String apiX;
	private String apiY;
	public BoardLocationVo() {
		super();
	}
	public BoardLocationVo(int boardNo, String apiX, String apiY) {
		super();
		this.boardNo = boardNo;
		this.apiX = apiX;
		this.apiY = apiY;
	}
	
	
	
	public BoardLocationVo(String apiX, String apiY) {
		super();
		this.apiX = apiX;
		this.apiY = apiY;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
	
	
	
	
	
}
