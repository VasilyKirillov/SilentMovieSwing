package model;

import java.util.List;

public class Movie {
	
	private int id;
	private String title;
	private int directorId;
	private List<Integer> genreIds;
	private int releaseYear;
	private float rating;
	private String about;
	
	//constructor1
	public Movie() {
	}

	//constructor2 run when read from database
	public Movie(int id, String title, int directorId, int releaseYear, float rating, String about,
			List<Integer> genreIds) {
		this.id = id;
		this.title = title;
		this.directorId = directorId;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.about = about;
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getRating() {
		return rating;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAbout() {
		return about;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", directorId=" + directorId + ", genreIds=" + genreIds
				+ ", releaseYear=" + releaseYear + ", rating=" + rating + "]";
	}

//	@Override
//	public String toString() {
//		return "[ Movie Title: " + title + ", released in " + releaseYear + "," + " imdb rating " + rating + " ]";
//	}

}
