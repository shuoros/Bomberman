package DataBase;

import java.sql.DriverManager;

import java.sql.Connection;

/**
 * This class connects the program with the database.
 * 
 * @author Soroush
 *
 */
public class Connect {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bomberman", "root", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return con;
	}
}
