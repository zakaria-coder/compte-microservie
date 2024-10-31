package org.enset.comptemicroservice.web;

import org.enset.comptemicroservice.DTO.CompteRequest;
import org.enset.comptemicroservice.DTO.CompteResponse;
import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.entities.Customer;
import org.enset.comptemicroservice.repositries.CompteRepository;
import org.enset.comptemicroservice.repositries.CustomerRepository;
import org.enset.comptemicroservice.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ComptesGraphQLController {

@Autowired
private CompteRepository compteRepository;
@Autowired
private CustomerRepository customerRepository;

@Autowired
private CompteService   compteService;

@QueryMapping
public List<Compte> ListComptes(){
    return compteRepository.findAll();
}
    @QueryMapping
    public Compte comptesbyId(@Argument String id ){
        return compteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("id %s not found",id)));
    }
    @MutationMapping
    public CompteResponse createCompte (@Argument("Compte") CompteRequest compte ){
        return compteService.createCompte(compte);
    }
    @MutationMapping
    public CompteResponse updateCompte2(@Argument("id")String id ,@Argument("Compte") CompteRequest compteRequest) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("The given id must not be null or empty");
        }
        return compteService.updateCompte2(id,compteRequest);
    }

    @MutationMapping
    public String deleteCompte2(@Argument("id") String id) {
        System.out.println("Deleting account with ID: " + id);
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return "Account with ID " + id + " was deleted successfully.";
        } else {
            throw new RuntimeException("Account with ID " + id + " does not exist.");
        }
    }
    @SchemaMapping(field = "customer", typeName = "Compte")
    public Customer getCustomer(Compte compte) {
        return customerRepository.findById(compte.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}

