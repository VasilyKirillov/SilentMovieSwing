package dao;

import java.util.List;

import model.Movie;

public interface MovieDao {
	void createMovie(Movie m);

	void updateMovie(Movie m);

	void deleteMovie(int id);
	
	List<Movie> getMoviesByDirector(String last);
	
	List<Movie> getMoviesByGenre(int id);
	
	List<Movie> getMoviesByYear(int year);
	
	Movie getMovieInfoById(int id);
	
	

}
