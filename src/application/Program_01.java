package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import db.ConexaoDB;
import model.Seller;

public class Program_01 {
    
	
	public static void main(String[] args) throws ParseException {
	  Connection conn = null;
	  PreparedStatement st = null;
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Seller seller = new Seller(null, "Anna Clara", "acbr@grupoacbr.com.br", LocalDateTime.now(), 125000.00, 1);
	  try {
		  conn = ConexaoDB.getConnection();
		  st = conn.prepareStatement("INSERT INTO seller (name, email, birthdate, basesalary, departmentid) VALUES (?, ?, ?, ?, ?)");
		  
		  st.setString(1, seller.getName());
		  st.setString(2, seller.getEmail());
		  st.setDate(3, new java.sql.Date(sdf.parse("10/03/2021").getTime()));
		  st.setDouble(4, seller.getBaseSalary());
		  st.setInt(5, seller.getDepartmentId());
		  int rowsAffected = st.executeUpdate();
		  System.out.println(rowsAffected);
	  }catch(SQLException error){
		  error.printStackTrace();
	  }finally {
		  ConexaoDB.closeStatement(st);
		 conn.close();
	  }
	}
}
