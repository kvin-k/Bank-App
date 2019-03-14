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
import com.cg.bankapp.dao.TransactionsDAO;
import com.cg.bankapp.driver.Main;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;
import com.cg.bankapp.service.TransactionsService;
import com.cg.bankapp.serviceimpl.TransactionServiceImpl;

public class TransactionDAOImpl implements TransactionsDAO {

	

	public long withdraw(long accountNo, long amount) {
		// TODO Auto-generated method stub
		// CustomerDetails c=new CustomerDetails();
		 long bal=0;
	       try {
	    	   Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","supermarket","oracle123");			
			Statement st = connection.createStatement();			
			ResultSet resultSet = st.executeQuery("select * from customer_details");
			while(resultSet.next())
			{
				if(accountNo==resultSet.getLong(1))
				{
					 bal = resultSet.getLong(10);
					// System.out.println(bal+"here");
				}
			}
		
			if(amount>bal)
			{
				bal=-1;
			}
			else
			{
				bal = bal - amount;
				PreparedStatement preparedStatement = connection.prepareStatement("update customer_details set balance=? where account_no=?");		
				preparedStatement.setLong(1, bal);
				preparedStatement.setLong(2, accountNo);
				preparedStatement.executeUpdate();
			}			
				connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
	}

	
	

	public long deposit(long accountNo, long amount) {
		// TODO Auto-generated method stub
				long bal=0;
				 try {
					 	Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","supermarket","oracle123");			
					
						Statement st = connection.createStatement();
						
						ResultSet resultSet = st.executeQuery("select * from customer_details order by account_no");
						while(resultSet.next())
						{
							if(accountNo==resultSet.getLong(1))
							{
								bal = resultSet.getLong(10);
							}
						}
						bal = bal+amount;
						PreparedStatement preparedStatement = connection.prepareStatement("update customer_details set balance=? where account_no=?");
						
						preparedStatement.setLong(1, bal);
						preparedStatement.setLong(2, accountNo);
						preparedStatement.executeUpdate();
						
						connection.close();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return bal;
	}

	public long fundTransfer(TransactionDetails transactionDetails) {

	
		try {
		 	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","supermarket","oracle123");			
			TransactionsService transactionsService=new TransactionServiceImpl();
			if(transactionsService.withdraw(transactionDetails.getFromAccountNo(),transactionDetails.getAmount_transfered())>0) {
				transactionsService.deposit(transactionDetails.getToAccountNo(), transactionDetails.getAmount_transfered());
				PreparedStatement preparedStatement = connection.prepareStatement("insert into transaction_details values(transaction_details_seq.nextval,?,?,?)");			
				preparedStatement.setLong(1, transactionDetails.getFromAccountNo());
				preparedStatement.setLong(2, transactionDetails.getToAccountNo());
				preparedStatement.setLong(3, transactionDetails.getAmount_transfered());
				preparedStatement.executeUpdate();
				
				Statement st = connection.createStatement();
				ResultSet resultSet = st.executeQuery("select * from transaction_details");
				while(resultSet.next())
				{
					transactionDetails.setTransactionId(resultSet.getLong(1));
				}
			
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionDetails.getTransactionId();
	}
     
}
