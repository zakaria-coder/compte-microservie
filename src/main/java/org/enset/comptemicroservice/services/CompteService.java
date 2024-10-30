package org.enset.comptemicroservice.services;

import org.enset.comptemicroservice.DTO.CompteRequest;
import org.enset.comptemicroservice.DTO.CompteResponse;
import org.enset.comptemicroservice.entities.Compte;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompteService {
    Compte createCompte(Compte compte);

    CompteResponse createCompte(CompteRequest compteRequest);
    CompteResponse updateCompte2(String id,CompteRequest compteRequest);

    Optional<Compte> getCompteById(String id);

    List<Compte> getAllComptes();

    Compte updateCompte(String id, Compte updatedCompte);

    void deleteCompte(String id);


}
