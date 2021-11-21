package ms.saving.bank.service;

import ms.saving.bank.documents.dto.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Mono<Customer> getCustomer(String customerIdentityNumber);
}
