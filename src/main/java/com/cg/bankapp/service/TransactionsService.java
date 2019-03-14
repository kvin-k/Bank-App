package com.cg.bankapp.service;

import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;

public interface TransactionsService {

	long withdraw(long accountNo,long amount);
	long deposit(long accountNo,long amount);
	public long fundTransfer(TransactionDetails transactionDetails);
	
}
