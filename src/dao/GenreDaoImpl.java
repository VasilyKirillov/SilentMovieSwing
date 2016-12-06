package dao;

public class GenreDaoImpl {
	/**
	 * Consider that genre list are immutable 
	 */
	private final String[] GENRE_NAMES = { "Comedy", "Action", "Adventure", "Crime", "Drama", "Fantasy", "Horror",
			"Romance", "Thriller", "Mystery", "History", "Biography", "Sport", "Western", "Sci-fi", "Family" };

	public String[] getList() {
		return GENRE_NAMES;
	}

}
