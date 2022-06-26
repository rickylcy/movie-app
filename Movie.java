
public class Movie {

	String title;
	String starring;
	String director;
	String genre;
	String classification;
	int duration;
	int releaseDate;
	
	int available = 0;
	int time_borrowed = 0;

	public Movie(String title, String starring, String director, String genre, String classification, int duration,
			int releaseDate, int copy) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.starring = starring;
		this.director = director;
		this.genre = genre;
		this.classification = classification;
		this.duration = duration;
		this.releaseDate = releaseDate;
		this.available = copy;
	}
	
	public String getTitle() {
		return title;
	}

}
