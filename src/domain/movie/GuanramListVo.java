package domain.movie;

public class GuanramListVo {

	private String userId;
	private String photoOrigin;
	private String photoSys;
	private String nickName;
	private String guanramReview;
	private String guanramWdate;
	private int guanramLike;
	private int guanramRating;
	
	public GuanramListVo() {
		super();
	}
	
	public GuanramListVo(String userId, String photoOrigin, String photoSys, String nickName, String guanramReview,
			String guanramWdate, int guanramLike, int guanramRating) {
		super();
		this.userId = userId;
		this.photoOrigin = photoOrigin;
		this.photoSys = photoSys;
		this.nickName = nickName;
		this.guanramReview = guanramReview;
		this.guanramWdate = guanramWdate;
		this.guanramLike = guanramLike;
		this.guanramRating = guanramRating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhotoOrigin() {
		return photoOrigin;
	}

	public void setPhotoOrigin(String photoOrigin) {
		this.photoOrigin = photoOrigin;
	}

	public String getPhotoSys() {
		return photoSys;
	}

	public void setPhotoSys(String photoSys) {
		this.photoSys = photoSys;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGuanramReview() {
		return guanramReview;
	}

	public void setGuanramReview(String guanramReview) {
		this.guanramReview = guanramReview;
	}

	public String getGuanramWdate() {
		return guanramWdate;
	}

	public void setGuanramWdate(String guanramWdate) {
		this.guanramWdate = guanramWdate.substring(0, 11);
	}

	public int getGuanramLike() {
		return guanramLike;
	}

	public void setGuanramLike(int guanramLike) {
		this.guanramLike = guanramLike;
	}

	public int getGuanramRating() {
		return guanramRating;
	}

	public void setGuanramRating(int guanramRating) {
		this.guanramRating = guanramRating;
	}
	
}
