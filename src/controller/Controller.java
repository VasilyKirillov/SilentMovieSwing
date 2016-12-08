package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.DirectorDaoImpl;
import dao.GenreDaoImpl;
import dao.MovieDaoImpl;
import model.Director;
import model.Movie;

public class Controller {

	/**
	 * This class like Proxy class between DAO and view.
	 */
	private static Controller instance = null;

	private MovieDaoImpl movieDao;
	private DirectorDaoImpl directorDao;
	private GenreDaoImpl genreDao;

	private List<Movie> movieReference;
	private List<Director> directorReference;

	public List<Director> getDirectorReference() {
		return directorReference;
	}
	public List<Movie> getMovieReference() {
		return movieReference;
	}

	private Controller() {
		movieDao = new MovieDaoImpl();
		directorDao = new DirectorDaoImpl();
		genreDao = new GenreDaoImpl();
		movieReference = Collections.emptyList();
		directorReference = Collections.emptyList();
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	// MovieDaoImpl methods
	public List<Movie> getMovieList() {
		movieReference = movieDao.getList();
		return movieReference;
	}

	public List<Movie> getMovieByDirector(String lastName) {
		movieReference = movieDao.getMoviesByDirector(lastName);
		return movieReference;
	}

	public List<Movie> getMovieByGenre(Integer id) {
		movieReference = movieDao.getMoviesByGenre(id);
		return movieReference;
	}

	public List<Movie> getMovieByYear(int year) {
		movieReference = movieDao.getMoviesByYear(year);
		return movieReference;
	}

	public Movie getMovie(int id) {
		return movieDao.getMovieInfoById(id);
	}

	public void createMovie(Movie m) {
		movieDao.createMovie(m);
	}

	public void updateMovie(Movie m) {
		movieDao.updateMovie(m);
	}

	public void deleteMovie(int id) {
		movieDao.deleteMovie(id);
	}

	// DirectorDaoImpl Methods
	public List<Director> getDirectorList() {
		directorReference = directorDao.getList();
		return directorReference;
	}

	public void createDirector(Director d) {
		directorDao.createDirector(d);
	}

	public void deleteDirector(int id) {
		directorDao.deleteDirector(id);
	}

	public String[] getLastNames() {
		List<String> ln = new ArrayList<>();
		if (directorReference.isEmpty()) {
			getDirectorList();
		}
		for (Director d : directorReference) {
			ln.add(d.getLastName());
		}
		return ln.toArray(new String[ln.size()]);
	}

	// GenreDaoImpl Method
	public String[] getGenres() {
		return genreDao.getList();
	}

}
