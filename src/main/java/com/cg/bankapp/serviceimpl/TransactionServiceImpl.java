package com.cg.bankapp.serviceimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.cg.bankapp.dao.RegisterDAO;
import com.cg.bankapp.dao.TransactionsDAO;
import com.cg.bankapp.driver.Main;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.service.TransactionsService;

public class TransactionServiceImpl implements TransactionsService {

	

	@Override
	public long withdraw(CustomerDetails customer) {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		 CustomerDetails c=new CustomerDetails();
	       try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");
			
			
			
			
			
			
			Statement st = connection.createStatement();
			
			ResultSet resultSet = st.executeQuery("select * from customerDetails");
			long bal=0;
			while(resultSet.next())
			{
				if(customer.getAccountNo()==resultSet.getLong(4))
				{
					 bal = resultSet.getLong(9);
					// System.out.println(bal+"here");
				}
			}
			
			
			System.out.println("enter amount");
			long amt = sc.nextLong();
			if(amt>bal)
			{
				System.out.println("Low balance");
			}
			else
			{
				bal = bal - amt;
				System.out.println("updated balance "+" "+ bal);
			}
			
			

			PreparedStatement preparedStatement = connection.prepareStatement("update customerDetails set balance=? where accountNo=?");
			
			preparedStatement.setLong(1, bal);
			preparedStatement.setLong(2, customer.getAccountNo());
			
			
			 int i = preparedStatement.executeUpdate();
			 
			 if (i==1) {
					System.out.println("done");
				} else {
		          System.out.println("error");
				}
					
				connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public long deposit(CustomerDetails customer) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount");
		long amount = sc.nextLong();
		long bal=0;
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");
				
				Statement st = connection.createStatement();
				
				ResultSet resultSet = st.executeQuery("select * from customerDetails");
				while(resultSet.next())
				{
					if(customer.getAccountNo()==resultSet.getLong(4))
					{
						bal = resultSet.getLong(9);
					}
				}
				bal = bal+amount;
				//System.out.println("money added");
				PreparedStatement preparedStatement = connection.prepareStatement("update customerDetails set balance=? where accountNo=?");
				
				preparedStatement.setLong(1, bal);
				preparedStatement.setLong(2, customer.getAccountNo());
				int i = preparedStatement.executeUpdate();
				
				if (i==1) {
					System.out.println("Money added");
				} else {
		          System.out.println("Error");
				}
				connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return 0;
	}
     
}
