package com.cg.bankapp.dao;

import com.cg.bankapp.model.CustomerDetails;

public interface LoginDAO {
	public CustomerDetails login(long accountNo,String password);
}
