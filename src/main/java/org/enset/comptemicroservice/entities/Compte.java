package org.enset.comptemicroservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.enset.comptemicroservice.enums.CompteType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    @JsonProperty("compteType")
    private CompteType type;
    @ManyToOne
    private Customer customer;

}
