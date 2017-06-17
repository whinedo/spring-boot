package es.whinedo.pilots.protocolbuffer.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import es.whinedo.pilots.protocolbuffer.customer.model.CustomerProto.Customer;
import es.whinedo.pilots.protocolbuffer.customer.model.CustomerProto.Customer.CustomerType;

@SpringBootApplication
@EnableFeignClients
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	@Bean
	public RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
		return new RestTemplate(Arrays.asList(hmc));
	}

	@Bean
	public CustomerRepository repository() {
		List<Customer> customers = new ArrayList<>();
		customers.add(Customer.newBuilder().setId(1).setPesel("12345").setName("Adam Kowalski")
				.setType(CustomerType.INDIVIDUAL).build());
		customers.add(Customer.newBuilder().setId(2).setPesel("12346").setName("Anna Malinowska")
				.setType(CustomerType.INDIVIDUAL).build());
		customers.add(Customer.newBuilder().setId(3).setPesel("12347").setName("Paweł Michalski")
				.setType(CustomerType.INDIVIDUAL).build());
		customers.add(Customer.newBuilder().setId(4).setPesel("12348").setName("Karolina Lewandowska")
				.setType(CustomerType.INDIVIDUAL).build());
		return new CustomerRepository(customers);
	}

}
