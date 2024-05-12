package app.rosettacloud.spring6reactive.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import app.rosettacloud.spring6reactive.model.BookDTO;
import app.rosettacloud.spring6reactive.services.BookService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController // RestController = controller + Response Body
@RequiredArgsConstructor
public class BookController {

    public static final String BOOK_PATH = "/api/v2/book";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookID}";

    private final BookService bookService;

    @GetMapping(BOOK_PATH_ID)
    Mono<BookDTO> getBookById(@PathVariable("bookID") Integer id) {
        return bookService.getBookById(id);
    }

    @GetMapping(BOOK_PATH)
    Flux<BookDTO> listBooks() {
        return bookService.listBooks();
    }

    @PostMapping(BOOK_PATH)
    public Mono<ResponseEntity<?>> addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO)
                .map(savedBook -> ResponseEntity.created(
                        // .build().toUri(), which converts the URI components into a URI object that is
                        // suitable for the ResponseEntity.
                        UriComponentsBuilder
                                .fromHttpUrl("http://localhost:8080" + BOOK_PATH + "/" + savedBook.getId())
                                .build()
                                .toUri())
                        .build());
    }

    @PutMapping(BOOK_PATH_ID)
    public ResponseEntity<Void> updateBook(@PathVariable("bookID") Integer bookId,
            @RequestBody BookDTO bookDTO) {

        bookService.updateBook(bookId, bookDTO)
                .subscribe();
        return ResponseEntity.ok().build();

    }

    @PatchMapping(BOOK_PATH_ID)
    public ResponseEntity<Void> patchBook(@PathVariable("bookID") Integer bookId,
            @RequestBody BookDTO bookDTO) {
        bookService.patchBook(bookId, bookDTO)
                .subscribe();
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(BOOK_PATH_ID)
    public ResponseEntity<Void> deleteBook(@PathVariable("bookID") Integer bookId) {
        bookService.deleteBook(bookId)
                .subscribe();
        return ResponseEntity.ok().build();
    }

}
