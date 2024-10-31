package org.enset.comptemicroservice.repositries;

import org.enset.comptemicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
}
