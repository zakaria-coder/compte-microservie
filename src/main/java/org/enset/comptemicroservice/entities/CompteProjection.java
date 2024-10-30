package org.enset.comptemicroservice.entities;

import org.enset.comptemicroservice.enums.CompteType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Compte.class,name = "p1")
public interface CompteProjection {
 public String getId();
 public CompteType getType();
 public Double getBalance();
}
