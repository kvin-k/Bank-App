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
import com.cg.bankapp.daoimpl.TransactionDAOImpl;
import com.cg.bankapp.driver.Main;
import com.cg.bankapp.exception.CannotWithdrawException;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;
import com.cg.bankapp.service.TransactionsService;

public class TransactionServiceImpl implements TransactionsService {
	TransactionsDAO dao=new TransactionDAOImpl();
	public long withdraw(long accountNo, long amount) {
		long balance=dao.withdraw(accountNo, amount);
		if(balance<0){
			try {
				throw new CannotWithdrawException();
			} catch (CannotWithdrawException e) {
			}
		}
		return balance;
	}

	public long deposit(long accountNo, long amount) {
		// TODO Auto-generated method stub
		return dao.deposit(accountNo, amount);
	}

	public long fundTransfer(TransactionDetails transactionDetails) {
		// TODO Auto-generated method stub
		return dao.fundTransfer(transactionDetails);
	}
	

	
     
}
