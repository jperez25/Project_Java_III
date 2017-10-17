package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	private ResultSet query;
	private Connection connec;
	private Statement stmt;
	
	public Connector() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/whereismyspot", "root",
				"Computer1");
		stmt = connec.createStatement();
	}
	public void close() throws SQLException {
		connec.close();
	}
	public ResultSet getQuery() {
		return query;
	}
	public ResultSet execQuery(String query) throws SQLException {
		return stmt.executeQuery(query);
	}
	public int execUpdate(String query) throws SQLException {
		return stmt.executeUpdate(query);
	}
}
