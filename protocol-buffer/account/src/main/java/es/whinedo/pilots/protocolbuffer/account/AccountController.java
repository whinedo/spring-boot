package es.whinedo.pilots.protocolbuffer.account;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.whinedo.pilots.protocolbuffer.account.model.AccountProto.Account;
import es.whinedo.pilots.protocolbuffer.account.model.AccountProto.Accounts;

@RestController
public class AccountController {

	@Autowired
	private AccountRepository repository;

	protected Logger logger = Logger.getLogger(AccountController.class.getName());

	@RequestMapping(value = "/accounts/{number}", produces = "application/x-protobuf")
	public Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		return repository.findByNumber(number);
	}

	@RequestMapping(value = "/accounts/customer/{customer}", produces = "application/x-protobuf")
	public Accounts findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return Accounts.newBuilder().addAllAccount(repository.findByCustomer(customerId)).build();
	}

	@RequestMapping(value = "/accounts", produces = "application/x-protobuf")
	public Accounts findAll() {
		logger.info("Account.findAll()");
		return Accounts.newBuilder().addAllAccount(repository.findAll()).build();
	}

}