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

    @Override
    public CompteResponse updateCompte2(String id,CompteRequest compteRequest) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        if (optionalCompte.isPresent()) {
            Compte compte = optionalCompte.get();
            if (compteRequest.getBalance() != null) {
                compte.setBalance(compteRequest.getBalance());
            }
            if (compteRequest.getCurrency() != null) {
                compte.setCurrency(compteRequest.getCurrency());
            }
            if (compteRequest.getType() != null) {
                compte.setType(compteRequest.getType());
            }

            return compteMapper.fromCompte(compteRepository.save(compte));
        } else {

            throw new RuntimeException("Compte with ID " + compteRequest.getId() + " not found.");

        }
    }


    public String deleteCompte2(String id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return "Compte with ID " + id + " deleted successfully";
        } else {
            throw new RuntimeException("Account with ID " + id + " does not exist.");
        }

    }

}

