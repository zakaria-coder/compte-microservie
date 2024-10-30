package org.enset.comptemicroservice.web;



import org.enset.comptemicroservice.DTO.CompteRequest;
import org.enset.comptemicroservice.DTO.CompteResponse;
import org.enset.comptemicroservice.Mappers.CompteMapper;
import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.services.CompteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CompteController {




    private CompteService compteService;
    private CompteMapper compteMapper;


    public CompteController(CompteMapper compteMapper, CompteService compteService) {
        this.compteMapper = compteMapper;
        this.compteService = compteService;
    }




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
    public ResponseEntity<Object> createCompte(@RequestBody Compte compte) {
        if (compte.getId() == null || compte.getId().isEmpty()) {
            compte.setId(UUID.randomUUID().toString());
        }
        if (compte.getCreatedAt() == null) {
            compte.setCreatedAt(new Date());
        }
        if (compte.getCurrency() == null || compte.getCurrency().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Currency cannot be empty");
        }

        Compte savedCompte = compteService.createCompte(compte);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompte);
    }

    //DTO CREATION
    @PostMapping
    public CompteResponse createCompte(@RequestBody CompteRequest compteRequest) {

        return  compteService.createCompte(compteRequest);


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