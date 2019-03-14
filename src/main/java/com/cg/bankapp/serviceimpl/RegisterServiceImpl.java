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
import com.cg.bankapp.daoimpl.RegisterDAOImpl;
import com.cg.bankapp.driver.Main;
import com.cg.bankapp.exception.CustomerAleadyExistException;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
	RegisterDAO dao= new RegisterDAOImpl();
	public long registration(CustomerDetails customerDetails) {
		return dao.registration(customerDetails);
	}

	public boolean validation(String aadharNo) {
		boolean check = false;
		if(dao.validation(aadharNo)) {
			if(aadharNo.length()==12) {
				check=true;
			}
		}else {
			try {
				throw new CustomerAleadyExistException();
			} catch (CustomerAleadyExistException e) {
			}
		}
		return check;

	}
	
	public boolean validateNumber(String mobile) {
		if(mobile.length()==10) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
