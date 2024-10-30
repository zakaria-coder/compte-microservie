package org.enset.comptemicroservice.services;

import org.enset.comptemicroservice.DTO.CompteRequest;
import org.enset.comptemicroservice.DTO.CompteResponse;
import org.enset.comptemicroservice.Mappers.CompteMapper;
import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.repositries.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompteServiceImpl implements CompteService  {
     @Autowired
    private  CompteRepository compteRepository;

     @Autowired
     private CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public CompteResponse createCompte(CompteRequest compteRequest) {
        Compte compte = Compte.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(compteRequest.getBalance())
                .currency(compteRequest.getCurrency())
                .type(compteRequest.getType())
                .build();

        Compte compteSaved = compteRepository.save(compte);

        CompteResponse compteResponse = compteMapper.fromCompte(compteSaved);
        return  compteResponse;

    }

    @Override
    public Optional<Compte> getCompteById(String id) {
        return compteRepository.findById(id);
    }

    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Compte updateCompte(String id, Compte updatedCompte) {
        Optional<Compte> compte = compteRepository.findById(id);
        if (compte.isPresent()) {
            Compte existingCompte = compte.get();
            existingCompte.setCurrency(updatedCompte.getCurrency());
            existingCompte.setBalance(updatedCompte.getBalance());
            existingCompte.setCreatedAt(updatedCompte.getCreatedAt());
            existingCompte.setType(updatedCompte.getType());
            return compteRepository.save(existingCompte);
        }
        return  null ;
    }

    @Override
    public void deleteCompte(String id) {
        compteRepository.deleteById(id);
    }
}
