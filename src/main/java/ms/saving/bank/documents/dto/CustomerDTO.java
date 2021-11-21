package ms.saving.bank.documents.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private String name;

    private String code;

    private String customerIdentityNumber;
}
