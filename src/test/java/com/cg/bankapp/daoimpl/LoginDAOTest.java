package com.cg.bankapp.daoimpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.bankapp.dao.LoginDAO;
import com.cg.bankapp.model.CustomerDetails;

class LoginDAOTest {

	 static LoginDAO dao;
	 
	 	@BeforeAll
	 	public static void init() {
	 		dao=new LoginDAOImpl();
	 	}
	 	@Test
		public void testLogin() {
			CustomerDetails customerDetails=dao.login(71234788, "po");
			assertEquals(-1, customerDetails.getAccountNo());
		}

}
