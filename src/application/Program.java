package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DB.getConnection();

			/* pst = conn.prepareStatement( "INSERT INTO seller " 
			 * 								+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES "
			 * 								+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);				
			 * 								
			 * pst.setString(1, "Carl Purple");
			 * pst.setString(2, "carl@gmail.com");
			 * pst.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			 * pst.setDouble(4, 3000.0); 
			 * pst.setInt(5, 4);
			 */

			pst = conn.prepareStatement("INSERT INTO department (Name) VALUES ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rSet = pst.getGeneratedKeys();
				while (rSet.next()) {
					int id = rSet.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
			DB.closeConnection();
		}
	}
}
