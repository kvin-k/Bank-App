package com.cg.bankapp.service;

import com.cg.bankapp.model.CustomerDetails;

public interface LoginService {
	public CustomerDetails login(long accountNo,String password);
}
