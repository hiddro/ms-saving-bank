package ms.saving.bank.service;

import ms.saving.bank.documents.entities.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISavingService extends ICrudService<SavingAccount, String>{

    Mono<SavingAccount> saveSavingAccount(SavingAccount savingAccount);

    Flux<SavingAccount> getAllSaving();

    Mono<SavingAccount> getByIdCustomer(String id);

    Mono<SavingAccount> getByIdNumber(String id);

    Mono<SavingAccount> updateSaving (String id, SavingAccount savingAccount);

    Mono<Void> deleteSaving (String id);
}
