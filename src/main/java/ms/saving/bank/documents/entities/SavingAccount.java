package ms.saving.bank.documents.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ms.saving.bank.documents.dto.CustomerDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "saving")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount {

    @Id
    private String id;

    @Field(name = "typeOfAccount")
    private String typeOfAccount;

    @Field(name = "customerIdentityNumber")
    private String customerIdentityNumber;

    @Field(name = "accountNumber")
    @Indexed(unique=true)
    private String accountNumber;

    @Field(name = "amount")
    private double amount;

    @Field(name = "create_saving")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDate = new Date();

    @Field(name = "customer")
    private CustomerDTO customer;
}
