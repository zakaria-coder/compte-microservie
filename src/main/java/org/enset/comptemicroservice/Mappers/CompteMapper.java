package org.enset.comptemicroservice.Mappers;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.enset.comptemicroservice.DTO.CompteResponse;
import org.enset.comptemicroservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public CompteResponse fromCompte(Compte compte) {
        CompteResponse compteResponse = new CompteResponse();
        BeanUtils.copyProperties(compte, compteResponse);
        return compteResponse;
    }
}

