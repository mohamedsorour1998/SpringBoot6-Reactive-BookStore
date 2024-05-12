package app.rosettacloud.spring6reactive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
