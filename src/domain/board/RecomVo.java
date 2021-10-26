package domain.board;

public class RecomVo {
	private int boardNo;
	private String recommender;
	
	public RecomVo() {
		super();
	}

	public RecomVo(int boardNo, String recommender) {
		super();
		this.boardNo = boardNo;
		this.recommender = recommender;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}
	
	
}
