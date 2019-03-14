package com.cg.bankapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cg.bankapp.dao.LoginDAO;
import com.cg.bankapp.dao.TransactionsDAO;
import com.cg.bankapp.model.CustomerDetails;

public class LoginDAOImpl implements LoginDAO{

	
		 
	
	public CustomerDetails login(long accountNo, String password) {
		CustomerDetails customer=new CustomerDetails();
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","supermarket","oracle123");
				Statement st = connection.createStatement();
				TransactionsDAO transactions=new TransactionDAOImpl();
				ResultSet resultSet = st.executeQuery("select * from customer_details");
				while(resultSet.next())
				{
					if(accountNo==resultSet.getLong(1)&&password.equals(resultSet.getString(5)))
					{
						customer.setAccountNo(resultSet.getLong(1));
						customer.setFirstname(resultSet.getString(2));
						customer.setBalance(resultSet.getLong(10));
						break;
					}
					else {
						customer.setAccountNo(-1);
					}
				}
				
				connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return customer;
	}
}
