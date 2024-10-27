package org.enset.comptemicroservice.repositries;

import org.enset.comptemicroservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte,String> {


}

