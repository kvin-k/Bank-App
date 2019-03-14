package com.cg.bankapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.cg.bankapp.dao.RegisterDAO;
import com.cg.bankapp.driver.Main;
import com.cg.bankapp.model.CustomerDetails;

public class RegisterDAOImpl implements RegisterDAO {



	public boolean validation(String aadharNo) {
		boolean check = false;
		int temp=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","supermarket","oracle123");
			Statement st = connection.createStatement();

			ResultSet resultSet = st.executeQuery("select * from customer_details");
			// To get the number of rows present in table
			ResultSet resultSet2=st.executeQuery("select count(*) from customer_details");
			while (resultSet.next()) {
				if (aadharNo.equals(resultSet.getLong(7))) {
					check=false;
				} else {
					// This temp size will be size of the table if Aadhar does not exist
					temp++;
				}
			}
			resultSet2.next();// To get the value
			if(temp==resultSet2.getInt(1)) {
				check=true;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;

	}

	public long registration(CustomerDetails customer) {
		
		// long aadharCardNo=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","supermarket","oracle123");
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into customer_details values(CUSTOMER_DETAILS_SEQ.nextval,?,?,?,?,?,?,?,?,?)");


				preparedStatement.setString(1, customer.getFirstname());
				preparedStatement.setString(2, customer.getLastname());
				preparedStatement.setString(3, customer.getEmail());
				preparedStatement.setString(4, customer.getPassword());
				preparedStatement.setString(5, customer.getPanNo());
				preparedStatement.setString(6, customer.getAadharCardNo());
				preparedStatement.setString(7, customer.getAddress());
				preparedStatement.setString(8, customer.getMobileNo());
				preparedStatement.setLong(9, customer.getBalance());
				

				preparedStatement.executeUpdate();
				
				Statement st = connection.createStatement();

				ResultSet resultSet = st.executeQuery("select * from customer_details");
				while (resultSet.next()) {
					customer.setAccountNo(resultSet.getLong(1));
				}
				connection.close();
			

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer.getAccountNo();
	}

}
