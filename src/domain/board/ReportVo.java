package domain.board;

public class ReportVo {
	private int boardNo;
	private String reportedId;
	public ReportVo() {
		super();
	}

	public ReportVo(int boardNo) {
		super();
		this.boardNo = boardNo;
		
	}

	public ReportVo(int boardNo, String reportedId) {
		super();
		this.boardNo = boardNo;
		this.reportedId = reportedId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getReportedId() {
		return reportedId;
	}

	public void setReportedId(String reportedId) {
		this.reportedId = reportedId;
	}
	
	
}
