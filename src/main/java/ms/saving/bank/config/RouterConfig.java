package ms.saving.bank.config;

import ms.saving.bank.handler.SavingAccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(SavingAccountHandler savingAccountHandler){

        return route(GET("/getAll"), savingAccountHandler::findAll)
                .andRoute(GET("/getByIdCustomer/{customerIdentityNumber}"), savingAccountHandler::findByCustomerIdentityNumber)
                .andRoute(GET("/getByIdNumber/{accountNumber}"), savingAccountHandler::findByAccountNumber)
                .andRoute(POST("/create"), savingAccountHandler::newSavingAccount)
                .andRoute(PUT("/update/{id}"), savingAccountHandler::updateSavingAccound)
                .andRoute(DELETE("/delete/{id}"), savingAccountHandler::deleteSavingAccound);

    }

}
