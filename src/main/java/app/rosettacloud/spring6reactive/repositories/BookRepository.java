package app.rosettacloud.spring6reactive.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.rosettacloud.spring6reactive.domain.Book;

// ReactiveCrudRepository returns mono or flux
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

}