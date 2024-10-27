package org.enset.comptemicroservice;

import org.enset.comptemicroservice.entities.Compte;
import org.enset.comptemicroservice.enums.CompteType;
import org.enset.comptemicroservice.repositries.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class CompteMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteMicroServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(CompteRepository compteRepository) {


        return args -> {
            for(int i=1;i < 10;i++){
            Compte compte = new Compte().builder()
                    .id(UUID.randomUUID().toString())
                    .type(Math.random()>0.5? CompteType.CURRENT_ACCOUNT:CompteType.SAVING_ACCOUNT)
                    .balance(10000+Math.random()*90000)
                    .createdAt(new Date())
                    .currency("MAD")
                    .build();

             compteRepository.save(compte);
            System.out.println("Initial data loaded into the Compte table.");
            }
            };

    }
}