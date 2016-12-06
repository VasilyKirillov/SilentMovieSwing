package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Director;

public class DirectorDaoImpl extends DaoFactory implements DirectorDao {

	private List<Director> directorList = new ArrayList<Director>();

	@Override
	public void createDirector(Director d) {
		executor(SQL.INSERT_DIRECTOR, d.getFirstName(), d.getMidName(), d.getLastName(), d.getAbout());
	}

	@Override
	public void deleteDirector(int id) {
		executor(SQL.DELETE_DIRECTOR_BY_ID, id);
	}

	@Override
	public List<Director> getList() {
		executor(SQL.ALL_DIRECTORS);
		return directorList;
	}

	protected int listFiller(ResultSet rs) throws SQLException {
		int cnt = 0;
		directorList.clear();
		while (rs.next()) {
			directorList.add(
					new Director(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)) );
			cnt++;
		}
		return cnt;
	}
}
