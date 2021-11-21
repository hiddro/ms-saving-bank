package ms.saving.bank.handler;

import ms.saving.bank.documents.entities.SavingAccount;
import ms.saving.bank.service.ISavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SavingAccountHandler {

    @Autowired
    private ISavingService savingService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(savingService.getAllSaving(), SavingAccount.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request){

        String id = request.pathVariable("customerIdentityNumber");

        return savingService.getByIdCustomer(id)
                .flatMap(c -> ServerResponse
                        .ok().
                        contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByAccountNumber(ServerRequest request){

        String id = request.pathVariable("accountNumber");

        return savingService.getByIdNumber(id)
                .flatMap(c -> ServerResponse
                        .ok().
                        contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> newSavingAccount(ServerRequest request){

        Mono<SavingAccount> savingAccountMono = request.bodyToMono(SavingAccount.class);

        return savingAccountMono.flatMap(saving -> savingService.saveSavingAccount(saving))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> updateSavingAccound(ServerRequest request){

        Mono<SavingAccount> savingAccountMono = request.bodyToMono(SavingAccount.class);

        String id = request.pathVariable("id");

        return savingAccountMono.flatMap(saving -> savingService.updateSaving(id, saving))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> deleteSavingAccound(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<SavingAccount> savingAccountMono = savingService.findById(id);

        return savingAccountMono.flatMap(saving -> savingService.deleteSaving(saving.getId()))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

}
