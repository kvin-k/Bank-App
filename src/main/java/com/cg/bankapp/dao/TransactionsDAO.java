package com.cg.bankapp.dao;

import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;

public interface TransactionsDAO {

	public long withdraw(long accountNo,long amount);
	public long deposit(long accountNo,long amount);
	public long fundTransfer(TransactionDetails transactionDetails);
	
}
