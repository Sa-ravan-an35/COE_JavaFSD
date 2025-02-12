package FeeDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static String driver = "jdbc:mysql://localhost:3306/panimalar";
	static String userName = "root";
	static String pwd = "root";
	
	public static Connection getDbConnect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(driver, userName, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}	
}
