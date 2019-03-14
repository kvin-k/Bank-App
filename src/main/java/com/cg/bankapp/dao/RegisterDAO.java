package com.cg.bankapp.dao;

import com.cg.bankapp.model.CustomerDetails;

public interface RegisterDAO {
	public long registration(CustomerDetails customerDetails);
	public boolean validation(String aadharNo);
	
		
		
		
}
