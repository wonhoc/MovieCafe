package domain.movie;

public class MovieInfoVo {

	// field
	private int movieNo;
	private String movieTitle;
	private String movieDir;
	private String movieActor;
	private String movieGenre;
	private int movieRuntime;
	private String movieLink;
	private String movieAge;
	private String movieRelease;
	private String posterOrigin;
	private String posterSys;
	private int movieAvg;
	
	// constructor
	public MovieInfoVo() {
		super();
	}
	
	
	
	public MovieInfoVo(String movieTitle, String movieDir, String movieActor, String movieGenre, int movieRuntime,
			String movieLink, String movieAge, String movieRelease, String posterOrigin, String posterSys) {
		super();
		this.movieTitle = movieTitle;
		this.movieDir = movieDir;
		this.movieActor = movieActor;
		this.movieGenre = movieGenre;
		this.movieRuntime = movieRuntime;
		this.movieLink = movieLink;
		this.movieAge = movieAge;
		this.movieRelease = movieRelease;
		this.posterOrigin = posterOrigin;
		this.posterSys = posterSys;
	}



	public MovieInfoVo(int movieNo, String movieTitle, String movieDir, String movieActor, String movieGenre,
			int movieRuntime, String movieLink, String movieAge, String movieRelease, String posterOrigin,
			String posterSys, int movieAvg) {
		super();
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.movieDir = movieDir;
		this.movieActor = movieActor;
		this.movieGenre = movieGenre;
		this.movieRuntime = movieRuntime;
		this.movieLink = movieLink;
		this.movieAge = movieAge;
		this.movieRelease = movieRelease;
		this.posterOrigin = posterOrigin;
		this.posterSys = posterSys;
		this.movieAvg = movieAvg;
	}

	// getter & setter
	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDir() {
		return movieDir;
	}

	public void setMovieDir(String movieDir) {
		this.movieDir = movieDir;
	}

	public String getMovieActor() {
		return movieActor;
	}

	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public int getMovieRuntime() {
		return movieRuntime;
	}

	public void setMovieRuntime(int movieRuntime) {
		this.movieRuntime = movieRuntime;
	}

	public String getMovieLink() {
		return movieLink;
	}

	public void setMovieLink(String movieLink) {
		this.movieLink = movieLink;
	}

	public String getMovieAge() {
		return movieAge;
	}

	public void setMovieAge(String movieAge) {
		this.movieAge = movieAge;
	}

	public String getMovieRelease() {
		return movieRelease;
	}

	public void setMovieRelease(String movieRelease) {
		this.movieRelease = movieRelease;
	}

	public String getPosterOrigin() {
		return posterOrigin;
	}

	public void setPosterOrigin(String posterOrigin) {
		this.posterOrigin = posterOrigin;
	}

	public String getPosterSys() {
		return posterSys;
	}

	public void setPosterSys(String posterSys) {
		this.posterSys = posterSys;
	}

	public int getMovieAvg() {
		return movieAvg;
	}

	public void setMovieAvg(int movieAvg) {
		this.movieAvg = movieAvg;
	}
	

	
}