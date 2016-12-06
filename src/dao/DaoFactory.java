package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoFactory {

	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet result = null;

	private static final String URL = "jdbc:oracle:thin:@192.168.1.10:1521:kitty";
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load Oracle's jdbc driver.");
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn =  DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Can't get connection");
			System.exit(-1);
		}
		return conn;
	}

	public void closeConnection(ResultSet result, PreparedStatement pstmt, Connection conn) {
		try {
			if (result != null)
				result.close();
		} catch (SQLException e) {
			System.out.println("Can't close ResultSet");
		} finally {
			closeConnection(pstmt, conn);
		}
	}

	public void closeConnection(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e2) {
			System.out.println("Can't close PreparedStatement");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				System.out.println("Can't close Connection");
			}
		}
	}

	public abstract List<?> getList();

	protected abstract int listFiller(ResultSet result) throws SQLException;

	protected void executor(SQL sql) {
		executor(sql, new Object[0]);
	}

	protected void executor(SQL sql, Object... obj) {
		int cnt = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.query());
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			boolean ans = pstmt.execute();
			if (obj.length>0) System.out.print(" id: "+obj[0]+" ");
			if (ans) {
				result = pstmt.getResultSet();
				cnt = listFiller(result);
				System.out.format("%s has executed, %d row(s) selected.\n", sql, cnt);
			} else {
				cnt = pstmt.getUpdateCount();
				System.out.format("%s has executed, %d row(s) modified.\n", sql, cnt);
			}
		} catch (SQLException e) {
			System.out.format("Can't execute %s.\n", sql);
		} finally {
			closeConnection(result, pstmt, conn);
		}
	}

}
// protected void selector(SQL sql){
// selector(sql, null);
// }
// protected void selector(SQL sql, Object obj) {
// try {
// conn = getConnection();
// pstmt = conn.prepareStatement(sql.query());
// if (obj != null)
// pstmt.setObject(1, obj);
// result = pstmt.executeQuery();
// listFiller();
// } catch (SQLException e) {
// System.out.format("Can't execute %s.\n\n", sql);
// e.printStackTrace(System.out);
// } finally {
// closeConnection(result, pstmt, conn);
// }
// }
// protected void modifier(SQL sql, Object... obj){
// try {
// conn = getConnection();
// pstmt = conn.prepareStatement(sql.query());
// for (int i=0; i<obj.length; i++){
// pstmt.setObject(i+1, obj[i]);
// }
// pstmt.execute();
// System.out.println(sql+" query executed with id="+obj[0]);
// } catch (SQLException e) {
// System.out.println("Can't executeUpdate "+sql+" with id="+obj[0]); //
// obj[0] hire like identifier
// } finally {
// closeConnection(pstmt, conn);
// }
// }