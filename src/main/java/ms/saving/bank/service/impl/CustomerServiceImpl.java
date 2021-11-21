package ms.saving.bank.service.impl;

import ms.saving.bank.documents.dto.Customer;
import ms.saving.bank.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Customer> getCustomer(String customerIdentityNumber) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8100/api/customer/getByIdentity/"+customerIdentityNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(customerResponse -> customerResponse.bodyToMono(Customer.class))
                .doOnNext(c -> log.info("Customer Response: Customer={}"+ c.getName()))
                .switchIfEmpty(Mono.empty());
    }
}
