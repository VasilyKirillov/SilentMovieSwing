package dao;

public enum SQL {
	/**
	 * Enumeration of Queries
	 */
	ALL_MOVIES("SELECT * FROM MOVIE ORDER BY ID"),
	MOVIE_LIST_GENRES("SELECT G.ID FROM GENRE G INNER JOIN MOVIE_HAS_GENRE MHG ON G.ID=MHG.GENRE_ID INNER JOIN MOVIE M ON M.ID=MHG.MOVIE_ID WHERE M.ID=?"),
	MOVIES_BY_DIRECTOR("SELECT * FROM MOVIE M INNER JOIN DIRECTOR D ON M.DIRECTOR_ID=D.ID WHERE D.LAST_NAME LIKE ? ORDER BY M.RELEASE_YEAR"),
	MOVIES_BY_GENRE("SELECT * FROM MOVIE M INNER JOIN MOVIE_HAS_GENRE MHG ON M.ID=MHG.MOVIE_ID INNER JOIN GENRE G ON MHG.GENRE_ID=G.ID WHERE G.ID=?"),
	MOVIES_BY_YEAR("SELECT * FROM MOVIE M WHERE M.RELEASE_YEAR=? ORDER BY M.ID"),
	MOVIE_BY_ID("SELECT * FROM MOVIE WHERE ID=?"),
	INSERT_MOVIE("INSERT INTO MOVIE(ID,TITLE,DIRECTOR_ID,RELEASE_YEAR,RATING,ABOUT) VALUES ( ?,?,?,?,?,?)"),
	INSERT_MOVIE_GENRES("INSERT INTO MOVIE_HAS_GENRE (MOVIE_ID, GENRE_ID) VALUES(?,?)"),
	INSERT_DIRECTOR("INSERT INTO DIRECTOR (ID, FIRST_NAME, MID_NAME, LAST_NAME, ABOUT) VALUES( (SELECT MAX(ID)+1 FROM DIRECTOR),?,?,?,?)"),
	DELETE_MOVIE_BY_ID("DELETE MOVIE WHERE ID=?"),
	DELETE_MOVIEGENRE_BY_ID("DELETE MOVIE_HAS_GENRE WHERE MOVIE_ID=?"),
	DELETE_DIRECTOR_BY_ID("DELETE DIRECTOR WHERE ID=?"),
	ALL_DIRECTORS("SELECT * FROM DIRECTOR ORDER BY ID"),
	DIRECTOR_LASTNAMES("SELECT LAST_NAME FROM DIRECTOR ORDER BY LAST_NAME"),
	UPDATE_MOVIE("UPDATE MOVIE SET TITLE=?,DIRECTOR_ID=?,RELEASE_YEAR=?,RATING=?,ABOUT=? WHERE ID=?"),
	UPDATE_MOVIE_GENRES("UPDATE MOVIE_HAS_GENRE SET MOVIE_ID=?,GENRE_ID=? WHERE MOVIE_ID=?"),
	MOVIE_ID("SELECT MAX(ID)+1 FROM MOVIE");

	String SQL;

	private SQL(String SQL) {
		this.SQL = SQL;
	}

	/**
	 * Method return String which represents the query
	 * @return query text;
	 */
	public String query() {
		return SQL;
	}
}
