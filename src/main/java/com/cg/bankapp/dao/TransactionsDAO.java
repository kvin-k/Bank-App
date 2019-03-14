package com.cg.bankapp.dao;

import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;

public interface TransactionsDAO {

	public long withdraw(long accountNo,long balance);
	public long deposit(long accountNo,long balance);
	public TransactionDetails fundTransfer(TransactionDetails transactionDetails);
	
}
