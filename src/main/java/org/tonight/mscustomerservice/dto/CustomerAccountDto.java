package org.tonight.mscustomerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAccountDto {

    private long accountId;
    private Integer id;
    private String accountType;
    private double accountBalance;
    private String routingNumber;

}
