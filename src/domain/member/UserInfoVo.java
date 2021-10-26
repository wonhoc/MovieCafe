package domain.member;

public class UserInfoVo {
	private String userId;
	private String userPwd;
	private String userNick;
	private String userEmail;
	private String userBirth;
	private String userContact;
	private String gender;
	private String exitdate;
	private String userName;
	private String photoOrigin;
	private String photoSys;
	private int boardCount;
	private int comCount;
	private String exitType;
	private String joindate;
	private int rankNo;
	private String rankType;
	
	public UserInfoVo() {
		super();
	}
	
	
	public UserInfoVo(String userId, String userPwd, String userNick, String userEmail, String userBirth,
			String userContact, String gender, String exitdate, String userName, String photoOrigin, String photoSys,
			int boardCount, int comCount, String exitType, String joindate, int rankNo, String rankType) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userBirth = userBirth;
		this.userContact = userContact;
		this.gender = gender;
		this.exitdate = exitdate;
		this.userName = userName;
		this.photoOrigin = photoOrigin;
		this.photoSys = photoSys;
		this.boardCount = boardCount;
		this.comCount = comCount;
		this.exitType = exitType;
		this.joindate = joindate;
		this.rankNo = rankNo;
		this.rankType = rankType;
	}

	public UserInfoVo(String userId) {
		super();
		this.userId = userId;
	}
	

	public UserInfoVo(String userId, String exitdate, String exitType) {
		super();
		this.userId = userId;
		this.exitdate = exitdate;
		this.exitType = exitType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userid) {
		this.userId = userid;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getExitdate() {
		return exitdate;
	}

	public void setExitdate(String exitdate) {
		this.exitdate = exitdate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getComCount() {
		return comCount;
	}

	public void setComCount(int comCount) {
		this.comCount = comCount;
	}

	public String getExitType() {
		return exitType;
	}

	public void setExitType(String exitType) {
		this.exitType = exitType;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public int getRankNo() {
		return rankNo;
	}

	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}
	
	public String getRankType() {
		return rankType;
	}


	public void setRankType(String rankType) {
		this.rankType = rankType;
	}


	@Override
	public String toString() {
		return "UserInfoVo [userId=" + userId + ", userPwd=" + userPwd + ", userNick=" + userNick + ", userEmail="
				+ userEmail + ", userBirth=" + userBirth + ", userContact=" + userContact + ", gender=" + gender
				+ ", exitdate=" + exitdate + ", userName=" + userName + ", photoOrigin=" + photoOrigin + ", photoSys="
				+ photoSys + ", boardCount=" + boardCount + ", comCount=" + comCount + ", exitType=" + exitType
				+ ", joindate=" + joindate + ", rankNo=" + rankNo + "]";
	}

	
	

	
	
	
	
	
	
}
