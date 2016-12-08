package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Movie;

public class MovieDaoImpl extends DaoFactory implements MovieDao {

	private List<Movie> movieList = new LinkedList<Movie>();

	public void createMovie(Movie m) {
		executor(SQL.INSERT_MOVIE, m.getTitle(), m.getDirectorId(), m.getReleaseYear(), m.getRating(), m.getAbout());
		for (int g : m.getGenreIds()) {
			executor(SQL.INSERT_MOVIE_GENRES, m.getId(), g);
		}
	}

	@Override
	public void updateMovie(Movie m) {
		executor(SQL.UPDATE_MOVIE, m.getTitle(), m.getDirectorId(), m.getReleaseYear(), m.getRating(), m.getAbout(),
				m.getId());
		executor(SQL.DELETE_MOVIEGENRE_BY_ID, m.getId());
		for (int g : m.getGenreIds()) {
			executor(SQL.INSERT_MOVIE_GENRES, m.getId(), g);
		}

	}

	@Override
	public void deleteMovie(int id) {
											System.out.println("======movieDaoImpl=="+id);
		executor(SQL.DELETE_MOVIEGENRE_BY_ID, id);
		executor(SQL.DELETE_MOVIE_BY_ID, id);
	}

	@Override
	protected int listFiller(ResultSet rs) throws SQLException {
		int cnt = 0;
		movieList.clear();
		while (rs.next()) {
			movieList.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5),
					rs.getString(6), null));
			cnt ++;
		}
		setGenreIds(movieList);
		return cnt;
	}

	private List<Integer> getGenreIds(int movieId) throws SQLException {
		List<Integer> genreIds = new ArrayList<Integer>(16);
		conn = getConnection();
		pstmt = conn.prepareStatement(SQL.MOVIE_LIST_GENRES.query());
		pstmt.setInt(1, movieId);
		result = pstmt.executeQuery();
		while (result.next()) {
			genreIds.add(result.getInt(1));
		}
		return genreIds;
	}

	private void setGenreIds(List<Movie> movieList) throws SQLException {
		for (Movie m : movieList) {
			m.setGenreIds(getGenreIds(m.getId()));
		}
	}

	public List<Movie> getList() {
		executor(SQL.ALL_MOVIES);
		return movieList;
	}

	@Override
	public List<Movie> getMoviesByDirector(String last) {
		executor(SQL.MOVIES_BY_DIRECTOR, last);
		return movieList;
	}

	@Override
	public List<Movie> getMoviesByGenre(int id) {
		executor(SQL.MOVIES_BY_GENRE, id);
		return movieList;
	}

	@Override
	public List<Movie> getMoviesByYear(int year) {
		executor(SQL.MOVIES_BY_YEAR, year);
		return movieList;
	}

	@Override
	public Movie getMovieInfoById(int id) {
		executor(SQL.MOVIE_BY_ID, id);
		return movieList.get(0);
	}

}
