package ms.saving.bank.service.impl;

import ms.saving.bank.documents.dto.CustomerDTO;
import ms.saving.bank.documents.entities.SavingAccount;
import ms.saving.bank.repository.SavingAccountRepository;
import ms.saving.bank.service.ICustomerService;
import ms.saving.bank.service.ISavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingServiceImpl implements ISavingService {

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    private ICustomerService customerService;

    @Override
    public Mono<SavingAccount> create(SavingAccount o) {
        return savingAccountRepository.save(o);
    }

    @Override
    public Flux<SavingAccount> findAll() {
        return savingAccountRepository.findAll();
    }

    @Override
    public Mono<SavingAccount> findById(String s) {
        return savingAccountRepository.findById(s);
    }

    @Override
    public Mono<SavingAccount> update(SavingAccount o) {
        return savingAccountRepository.save(o);
    }

    @Override
    public Mono<Void> delete(SavingAccount o) {
        return savingAccountRepository.delete(o);
    }

    @Override
    public Mono<SavingAccount> saveSavingAccount(SavingAccount savingAccount) {
        return customerService.getCustomer(savingAccount.getCustomerIdentityNumber()).flatMap(c -> {
            if(c.getId() == null){
                return Mono.empty();
            }
            savingAccount.setTypeOfAccount("SAVING_ACCOUNT");
            savingAccount.setAmount(0.0);
            savingAccount.setCustomer(CustomerDTO.builder().name(c.getName())
                    .code(c.getCustomerType().getCode())
                    .customerIdentityNumber(c.getCustomerIdentityNumber()).build());

            return savingAccountRepository.save(savingAccount);
        });
    }

    @Override
    public Flux<SavingAccount> getAllSaving() {
        return savingAccountRepository.findAll();
    }

    @Override
    public Mono<SavingAccount> getByIdCustomer(String id) {
        return savingAccountRepository.findByCustomerIdentityNumber(id);
    }

    @Override
    public Mono<SavingAccount> getByIdNumber(String id) {
        return savingAccountRepository.findByAccountNumber(id);
    }

    @Override
    public Mono<SavingAccount> updateSaving(String id, SavingAccount savingAccount) {
        Mono<SavingAccount> savingExist = savingAccountRepository.findById(id);

        return savingExist.flatMap(existSaving -> {
            existSaving.setTypeOfAccount(savingAccount.getTypeOfAccount());
            existSaving.setCustomerIdentityNumber(savingAccount.getCustomerIdentityNumber());
            existSaving.setAccountNumber(savingAccount.getAccountNumber());
            existSaving.setAmount(savingAccount.getAmount());
            return savingAccountRepository.save(existSaving);
        }).switchIfEmpty(Mono.just(SavingAccount.builder().build()));
    }

    @Override
    public Mono<Void> deleteSaving(String id) {
        return savingAccountRepository.deleteById(id);
    }
}
