package com.cg.bankapp.driver;

import java.util.Scanner;

import javax.imageio.spi.RegisterableService;

import com.cg.bankapp.exception.WrongAadharLengthException;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.service.LoginService;
import com.cg.bankapp.service.RegisterService;
import com.cg.bankapp.serviceimpl.LoginServiceImpl;
import com.cg.bankapp.serviceimpl.RegisterServiceImpl;

public class Main{
	public static void main(String[] args) {

		CustomerDetails customerDetails=new CustomerDetails();
		Scanner sc = new Scanner(System.in);
		RegisterService regService=new RegisterServiceImpl();
		LoginService login=new LoginServiceImpl();
		int i;
		do {
		// Showing the menu here
		System.out.println("Enter your choice");
		System.out.println("1. Registration");
		System.out.println("2. Login");
		int ch = sc.nextInt();
            switch(ch)
            {
            case 1 :
            	// Scanning the customer details for registartion
            	System.out.println("Enter firstname");
            	customerDetails.setFirstname(sc.next());
    			System.out.println("Enter lastname");
    			customerDetails.setLastname(sc.next());
    			System.out.println("Enter Email");
    			customerDetails.setEmail(sc.next());
    			System.out.println("Enter password");
    			customerDetails.setPassword(sc.next());
    			System.out.println("Enter pan no");
    			customerDetails.setPanNo(sc.next());
    			System.out.println("Enter aadhar");
    			customerDetails.setAadharCardNo(sc.next());
    			if(regService.validation(customerDetails.getAadharCardNo())) {
    				System.out.println("Enter address");
    				customerDetails.setAddress(sc.next());
	    			System.out.println("Enter mobile number");
	    			customerDetails.setMobileNo(sc.next());
	    			customerDetails.setBalance(0);
	    			System.out.println("Account number is "+regService.registration(customerDetails));
	    		}else {
    				try {
						throw new WrongAadharLengthException();
					} catch (WrongAadharLengthException e) {
					}
    			}
            	break;
            case 2:
            	System.out.println("Enter account num");
            	customerDetails.setAccountNo(sc.nextLong());
            	System.out.println("Enter password");
            	customerDetails.setPassword(sc.next());
            	customerDetails=login.login(customerDetails.getAccountNo(), customerDetails.getPassword());
            	if(customerDetails.getAccountNo()>0) {
            		System.out.println("Enter your choice");
            		System.out.println("1. Deposit");
            		System.out.println("2. Withdraw");
            		System.out.println("3. Show balance");
            		System.out.println("4. Fund transfer");
            		ch = sc.nextInt();
                        switch(ch)
                        {
                        case 1 :
                        }
            	}
            	else {
            		throw new WrongLoginCredentialsException();
            	}
            	break;
            default:
            		System.exit(0);
            }
            System.out.println("Enter 1 to continue and press any number to exit.");
             i = sc.nextInt();
		} while(i==1);
		System.out.println("Thank you");
	}
}