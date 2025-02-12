package FeeDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    public static void addStudent(String name, String email, String course, double fee, double paid, String address, String phone) throws SQLException {
        String query = "insert into student values (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = DBConnection.getDbConnect();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, name);
    	pst.setString(2, email);
    	pst.setString(3, course);
    	pst.setDouble(4, fee);
    	pst.setDouble(5, paid);
    	pst.setDouble(6, fee - paid);
    	pst.setString(7, address);
    	pst.setString(8, phone);
    	pst.executeUpdate();
        System.out.println("Student details added successfully.");
   }

    public static void checkDueFee() throws SQLException {
        String query = "select * from student where due > 0";
        Connection con = DBConnection.getDbConnect();
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("Student ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                    ", Due Fee: " + rs.getDouble("due"));
        }
    }
}

