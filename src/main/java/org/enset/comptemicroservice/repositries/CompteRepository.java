package org.enset.comptemicroservice.repositries;

import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.enums.CompteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;


@RepositoryRestResource(path = "comptes")
public interface CompteRepository extends JpaRepository<Compte,String> {
    @RestResource(path ="/bytype")
    List<Compte> findByType(@Param("t") CompteType type);



}

