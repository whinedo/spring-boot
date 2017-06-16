package es.whinedo.pilots.protocolbuffer.account;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import es.whinedo.pilots.protocolbuffer.account.model.AccountProto.Account;
import es.whinedo.pilots.protocolbuffer.account.model.AccountProto.Accounts;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AccountApplicationTests {

	protected Logger logger = Logger.getLogger(AccountApplicationTests.class.getName());
	
	@Autowired
	TestRestTemplate template;
	
	@Test
	public void testFindByNumber() {
		Account a = this.template.getForObject("/accounts/{id}", Account.class, "111111");
		logger.info("Account[\n" + a + "]");
	}
	
	@Test
	public void testFindByCustomer() {
		Accounts a = this.template.getForObject("/accounts/customer/{customer}", Accounts.class, "2");
		logger.info("Accounts[\n" + a + "]");
	}
	
	@Test
	public void testFindAll() {
		Accounts a = this.template.getForObject("/accounts", Accounts.class);
		logger.info("Accounts[\n" + a + "]");
	}
	

	@TestConfiguration
	static class Config {

		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().additionalMessageConverters(new ProtobufHttpMessageConverter());
		}

	}
	
}