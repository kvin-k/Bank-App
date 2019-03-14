package com.cg.bankapp.serviceimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cg.bankapp.dao.LoginDAO;
import com.cg.bankapp.dao.TransactionsDAO;
import com.cg.bankapp.daoimpl.LoginDAOImpl;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.service.LoginService;

public class LoginServiceImpl implements  LoginService{
	LoginDAO dao =new LoginDAOImpl();
	public CustomerDetails login(long accountNo, String password) {
		return dao.login(accountNo, password);
	}
}
