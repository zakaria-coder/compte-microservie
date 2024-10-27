package org.enset.comptemicroservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class CompteResponse {

    private String accountNumber;
    private String currency;
}