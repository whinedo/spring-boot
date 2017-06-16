package es.whinedo.pilots.protocolbuffer.account;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.whinedo.pilots.protocolbuffer.account.model.AccountProto.Account;

@Repository
public class AccountRepository {

	List<Account> accounts;

	public AccountRepository(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Account> findAll() {
		return accounts;
	}

	public List<Account> findByCustomer(int customerId) {
		return accounts.stream().filter(it -> it.getCustomerId() == customerId).collect(Collectors.toList());
	}
	
	public Account findByNumber(String number) {
		return accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
	}

}