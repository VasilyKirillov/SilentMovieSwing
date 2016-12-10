package dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import model.Movie;

public class MovieDaoImpl extends DaoFactory implements MovieDao {

	private List<Movie> movieList = new LinkedList<Movie>();

	@Override
	public void createMovie(Movie m) {
		int id = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL.MOVIE_ID.query());
			result = pstmt.executeQuery();
			result.next();
			id = result.getInt(1);
			pstmt = conn.prepareStatement(SQL.INSERT_MOVIE.query());
			pstmt.setInt(1, id);
			pstmt.setString(2, m.getTitle());
			pstmt.setInt(3, m.getDirectorId());
			pstmt.setInt(4, m.getReleaseYear());
			pstmt.setDouble(5, m.getRating());
			pstmt.setString(6, m.getAbout());
			pstmt.execute();
			for (int genreId : m.getGenreIds()) {
				pstmt = conn.prepareStatement(SQL.INSERT_MOVIE_GENRES.query());
				pstmt.setInt(1, id);
				pstmt.setInt(2, genreId);
				pstmt.execute();
			}
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Can't insert movie.");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Can't rollback.");
				e1.printStackTrace(System.out);
			}
			e.printStackTrace(System.out);
		} finally {
			closeConnection(result, pstmt, conn);
		}
		System.out.format("Movie id: %d  inserted.\n", id);
	}

	@Override
	public void updateMovie(Movie m) {
		int id = m.getId();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL.UPDATE_MOVIE.query());
			pstmt.setString(1, m.getTitle());
			pstmt.setInt(2, m.getDirectorId());
			pstmt.setInt(3, m.getReleaseYear());
			pstmt.setDouble(4, m.getRating());
			pstmt.setString(5, m.getAbout());
			pstmt.setInt(6, id);
			pstmt.addBatch();
			pstmt = conn.prepareStatement(SQL.DELETE_MOVIEGENRE_BY_ID.query());
			pstmt.setInt(1, id);
			pstmt.addBatch();
			for (int g : m.getGenreIds()) {
				pstmt = conn.prepareStatement(SQL.INSERT_MOVIE_GENRES.query());
				pstmt.setInt(1, id);
				pstmt.setInt(2, g);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Can't update movie " + e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Can't rollback.");
				e1.printStackTrace(System.out);
			}
		} finally {
			closeConnection(pstmt, conn);
		}
		System.out.format("Movie id: %d  updated.\n", id);
	}
	
	@Override
	public void deleteMovie(int id) {
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL.DELETE_MOVIEGENRE_BY_ID.query());
			pstmt.setInt(1, id);
			pstmt.execute();
			pstmt = conn.prepareStatement(SQL.DELETE_MOVIE_BY_ID.query());
			pstmt.setInt(1, id);
			pstmt.execute();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Can't delete movie id: " + id);
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Can't rollback.");
				e1.printStackTrace();
			}
		} finally {
			closeConnection(pstmt, conn);
		}
		System.out.format("Movie id: %d deleted.\n", id);
	}

	@Override
	protected int listFiller(ResultSet rs) throws SQLException {
		int cnt = 0;
		movieList.clear();
		while (rs.next()) {
			movieList.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5),
					rs.getString(6), null));
			cnt++;
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
