package org.enset.comptemicroservice.services;

import org.enset.comptemicroservice.entities.Compte;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompteService {

    Compte createCompte(Compte compte);

    Optional<Compte> getCompteById(String id);

    List<Compte> getAllComptes();

    Compte updateCompte(String id, Compte updatedCompte);

    void deleteCompte(String id);

}
