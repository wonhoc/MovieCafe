package domain.movie;

public class MovieGuanramVo {
	private String userId;
	private int movieNo;
	private String guanramReview;
	private int guanramRating;
	private String guanramWdate;
	private int guanramLike;
	
	public MovieGuanramVo() {
		super();
	}

	public MovieGuanramVo(String userId, int movieNo, String guanramReview, int guanramRating) {
		super();
		this.userId = userId;
		this.movieNo = movieNo;
		this.guanramReview = guanramReview;
		this.guanramRating = guanramRating;
	}

	public MovieGuanramVo(String userId, int movieNo, String guanramReview, int guanramRating, String guanramWdate,
			int guanramLike) {
		super();
		this.userId = userId;
		this.movieNo = movieNo;
		this.guanramReview = guanramReview;
		this.guanramRating = guanramRating;
		this.guanramWdate = guanramWdate;
		this.guanramLike = guanramLike;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public String getGuanramReview() {
		return guanramReview;
	}

	public void setGuanramReview(String guanramReview) {
		this.guanramReview = guanramReview;
	}

	public int getGuanramRating() {
		return guanramRating;
	}

	public void setGuanramRating(int guanramRating) {
		this.guanramRating = guanramRating;
	}

	public String getGuanramWdate() {
		return guanramWdate;
	}

	public void setGuanramWdate(String guanramWdate) {
		this.guanramWdate = guanramWdate;
	}

	public int getGuanramLike() {
		return guanramLike;
	}

	public void setGuanramLike(int guanramLike) {
		this.guanramLike = guanramLike;
	}

	

}
