package es.whinedo.pilots.protocolbuffer.customer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.whinedo.pilots.protocolbuffer.customer.model.CustomerProto.Accounts;

@FeignClient(value = "account-service")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/customer/{customerId}")
    Accounts getAccounts(@PathVariable("customerId") Integer customerId);
    
}