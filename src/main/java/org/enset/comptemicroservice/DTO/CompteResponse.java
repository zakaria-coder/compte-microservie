package org.enset.comptemicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.enset.comptemicroservice.enums.CompteType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class CompteResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    @JsonProperty("compteType")
    private CompteType type;
}
