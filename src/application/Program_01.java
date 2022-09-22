package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import db.ConexaoDB;
import model.Seller;

public class Program_01 {
    
	
	public static void main(String[] args) throws ParseException{
	  Connection conn = null;
	  PreparedStatement st = null;
	  ResultSet rs = null;
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Seller seller = new Seller(null, "Vicente Rocha", "vicenterochajp@gmail.com.br", LocalDateTime.now(), 5000.00, 1);
	  try {
		  conn = ConexaoDB.getConnection();
		  st = conn.prepareStatement("INSERT INTO seller (name, email, birthdate, basesalary, departmentid) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		  
		  st.setString(1, seller.getName());
		  st.setString(2, seller.getEmail());
		  st.setDate(3, new java.sql.Date(sdf.parse("10/03/2021").getTime()));
		  st.setDouble(4, seller.getBaseSalary());
		  st.setInt(5, seller.getDepartmentId());
		  int rowsAffected = st.executeUpdate();
		  
		  if(rowsAffected > 0) {
			  rs = st.getGeneratedKeys();
			  while(rs.next()) {
				  int id = rs.getInt(1);
				  System.out.println("Done! id: "+ id);
			  }
		  }else {
			  System.out.println("No rown affected");			  
		  }
		  
	  }catch(SQLException error){
		  error.printStackTrace();
	  }finally {
		  ConexaoDB.closeStatement(st);
		  ConexaoDB.closeResultSet(rs);
		  ConexaoDB.closeConnection();
	  }
	}
}
