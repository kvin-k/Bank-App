package com.cg.bankapp.service;

import com.cg.bankapp.model.CustomerDetails;

public interface RegisterService {
		long registration(CustomerDetails customerDetails);
		boolean validation(String string);
		
		
		
}
