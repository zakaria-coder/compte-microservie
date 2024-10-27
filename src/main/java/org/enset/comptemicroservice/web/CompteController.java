package org.enset.comptemicroservice.web;


import lombok.AllArgsConstructor;
import org.enset.comptemicroservice.DTO.CompteRequest;
import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.services.CompteService;
import org.enset.comptemicroservice.services.CompteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CompteController {

    @Autowired
    private CompteService compteService;

    @GetMapping("/comptes")
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }


    @GetMapping("/comptes/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable String id) {
        Optional<Compte> compte = compteService.getCompteById(id);
        return compte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/comptes")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteService.createCompte(compte);
        return ResponseEntity.ok(savedCompte);
    }


    @PutMapping("/comptes/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable String id, @RequestBody Compte compte) {
        Compte updatedCompte = compteService.updateCompte(id, compte);
        return updatedCompte != null ? ResponseEntity.ok(updatedCompte) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable String id) {
        compteService.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }
}