package es.whinedo.pilots.protocolbuffer.customer;

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

import es.whinedo.pilots.protocolbuffer.customer.model.CustomerProto.Customer;
import es.whinedo.pilots.protocolbuffer.customer.model.CustomerProto.Customers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CustomerApplicationTests {

	protected Logger logger = Logger.getLogger(CustomerApplicationTests.class.getName());
	
	@Autowired
	TestRestTemplate template;
	
//	@Test
	public void testFindById() {
		Customer c = this.template.getForObject("/customers/{id}", Customer.class, 1);
		logger.info("Customer[\n" + c + "]");
	}
	
	@Test
	public void testFindByPesel() {
		Customer c = this.template.getForObject("/customers/pesel/{pesel}", Customer.class, "12346");
		logger.info("Customer[\n" + c + "]");
	}
	
	@Test
	public void testFindAll() {
		Customers c = this.template.getForObject("/customers", Customers.class);
		logger.info("Customers[\n" + c + "]");
	}
	

	@TestConfiguration
	static class Config {

		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().additionalMessageConverters(new ProtobufHttpMessageConverter());
		}

	}
	
}