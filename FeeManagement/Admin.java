package FeeDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin{
	public static boolean validateAdmin(String username, String password) {
        String query = "select * from admin where username = ? and password = ?";
        try (Connection con = DBConnection.getDbConnect();
            PreparedStatement pst = con.prepareStatement(query)) {
        	pst.setString(1, username);
        	pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next(); 
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public static void addAccountant(String name, String email, String phone, String password) throws SQLException {
        String query = "insert into accountant (name, email, phone, password) values (?, ?, ?, ?)";
        Connection con = DBConnection.getDbConnect();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, email);
        pst.setString(3, phone);
        pst.setString(4, password);
        pst.executeUpdate();
        System.out.println("Accountant added successfully!");
    }

    public static void viewAccountants() throws SQLException {
        String query = "select * from accountant";
        Connection con = DBConnection.getDbConnect();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Id is " + rs.getInt("id") + ", Name is " + rs.getString("name") +
                   ", Email is " + rs.getString("email") + ", Phone NUmber is " + rs.getString("phone"));
         }
    }
}

