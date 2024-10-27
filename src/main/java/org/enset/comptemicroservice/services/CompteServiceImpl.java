package org.enset.comptemicroservice.services;

import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.repositries.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompteServiceImpl implements CompteService  {

    private final CompteRepository compteRepository;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
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
