package ms.saving.bank.repository;

import ms.saving.bank.documents.entities.SavingAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SavingAccountRepository extends ReactiveMongoRepository<SavingAccount, String> {
    Mono<SavingAccount> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<SavingAccount> findByAccountNumber(String accountNumber);
}
