package com.cg.bankapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.bankapp.daoimpl.LoginDAOImpl;
import com.cg.bankapp.daoimpl.RegisterDAOImpl;
import com.cg.bankapp.model.CustomerDetails;

class RegisterDAOTest {

	 static RegisterDAO dao;
	 
	 	@BeforeAll
	 	public static void init() {
	 		dao=new RegisterDAOImpl();
	 	}
	
	@Test
	void testRegistration() {
		CustomerDetails customerDetails=new CustomerDetails();
		customerDetails.setFirstname("Vinith");
		customerDetails.setLastname("umar");
		customerDetails.setAadharCardNo("123412391235");
		customerDetails.setAddress("noida");
		customerDetails.setBalance(0);
		customerDetails.setEmail("Vin@gmil.com");
		customerDetails.setMobileNo("7512345678");
		customerDetails.setPanNo("dwe4t4g");
		customerDetails.setPassword("pwd");
		assertEquals(123456793,dao.registration(customerDetails));
	}

	@Test
	void testValidation() {
		assertEquals(true, dao.validation("12356788"));
	}

}
