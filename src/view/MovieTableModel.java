package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Director;
import model.Movie;

public class MovieTableModel extends AbstractTableModel {

	/**
	 * Implementation of AbstractTableModel for JTable.
	 */
	private static final long serialVersionUID = 1L;

	private List<Director> directors;
	private List<Movie> movies;
	private String[] genres;
	private Controller controller;

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	private static final String[] ROW_NAMES = { "ID", "TITLE", "DIRECTOR", "GENRES", "RELEASED IN", "RATING",
			"DESCRIPTION" };

	public MovieTableModel() {
		controller = Controller.getInstance();
		genres = controller.getGenres();
		directors = controller.getDirectorReference();
		movies = controller.getMovieReference();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return movies.isEmpty() ? 0 : movies.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return movies.get(row).getId();
		case 1:
			return movies.get(row).getTitle();
		case 2:
			return directors.get(movies.get(row).getDirectorId() - 1);
		case 3:
			return getMovieGenres(movies.get(row).getGenreIds());
		case 4:
			return movies.get(row).getReleaseYear();
		case 5:
			return movies.get(row).getRating();
		case 6:
			return movies.get(row).getAbout();

		}
		return "NOTHING";
	}

	@Override
	public String getColumnName(int row) {
		return ROW_NAMES[row];
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	public List<String> getMovieGenres(List<Integer> genreIds) {
		List<String> mg = new ArrayList<String>(10);
		for (Integer i : genreIds) {
			mg.add(genres[i - 1]);
		}
		return mg;
	}

}
