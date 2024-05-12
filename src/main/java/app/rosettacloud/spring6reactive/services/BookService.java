package app.rosettacloud.spring6reactive.services;

import app.rosettacloud.spring6reactive.model.BookDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<BookDTO> listBooks();

    Mono<BookDTO> getBookById(Integer id);
}
