package domain.movie;

public class MovieInfoVo {

	// field
	private int movieNo;
	private String movieTitle;
	private String movieDir;
	private String movieActor;
	private String movieGenre;
	private String movieLink;
	private char movieAge;
	private String movieRelease;
	private String posterOrigin;
	private String posterSys;
	private int movieAvg;
	
	// constructor
	public MovieInfoVo() {
		super();
	}

	public MovieInfoVo(int movieNo, String movieTitle, String movieDir, String movieActor, String movieGenre,
			String movieLink, char movieAge, String movieRelease, String posterOrigin, String posterSys, int movieAvg) {
		super();
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.movieDir = movieDir;
		this.movieActor = movieActor;
		this.movieGenre = movieGenre;
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

	public String getMovieLink() {
		return movieLink;
	}

	public void setMovieLink(String movieLink) {
		this.movieLink = movieLink;
	}

	public char getMovieAge() {
		return movieAge;
	}

	public void setMovieAge(char movieAge) {
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