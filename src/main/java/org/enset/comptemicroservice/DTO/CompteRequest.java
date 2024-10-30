package org.enset.comptemicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.enset.comptemicroservice.enums.CompteType;


@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
@Getter@Setter
public class CompteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long createdAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    @JsonProperty("compteType")
    private CompteType type;
}
