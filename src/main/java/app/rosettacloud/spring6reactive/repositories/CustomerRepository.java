package app.rosettacloud.spring6reactive.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.rosettacloud.spring6reactive.domain.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
