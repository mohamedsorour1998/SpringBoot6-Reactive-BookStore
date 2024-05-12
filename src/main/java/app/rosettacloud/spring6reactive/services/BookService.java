package app.rosettacloud.spring6reactive.services;

import app.rosettacloud.spring6reactive.model.BookDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<BookDTO> listBooks();

    Mono<BookDTO> getBookById(Integer id);

    Mono<BookDTO> addBook(BookDTO bookDTO);

    Mono<BookDTO> updateBook(Integer bookId, BookDTO bookDTO);

    Mono<BookDTO> patchBook(Integer bookId, BookDTO bookDTO);

    Mono<Void> deleteBook(Integer bookId);

}
