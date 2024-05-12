package app.rosettacloud.spring6reactive.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import app.rosettacloud.spring6reactive.mappers.BookMapper;
import app.rosettacloud.spring6reactive.model.BookDTO;
import app.rosettacloud.spring6reactive.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Flux<BookDTO> listBooks() {
        return bookRepository.findAll()
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> getBookById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> addBook(BookDTO bookDTO) {
        return bookRepository.save(bookMapper.toBook(bookDTO))
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> updateBook(Integer bookId, BookDTO newBookDTO) {
        return bookRepository.findById(bookId)
                .flatMap(oldBook -> {
                    oldBook.setBookName(newBookDTO.getBookName());
                    oldBook.setBookStyle(newBookDTO.getBookStyle());
                    oldBook.setUpc(newBookDTO.getUpc());
                    oldBook.setPrice(newBookDTO.getPrice());
                    oldBook.setQuantityOnHand(newBookDTO.getQuantityOnHand());
                    return bookRepository.save(oldBook);
                })
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> patchBook(Integer bookId, BookDTO bookDTO) {

        return bookRepository.findById(bookId)
                .flatMap(oldBook -> {
                    if (StringUtils.hasText(bookDTO.getBookName())) {
                        oldBook.setBookName(bookDTO.getBookName());
                    }
                    if (bookDTO.getBookStyle() != null) {
                        oldBook.setBookStyle(bookDTO.getBookStyle());
                    }
                    if (bookDTO.getUpc() != null) {
                        oldBook.setUpc(bookDTO.getUpc());
                    }
                    if (bookDTO.getQuantityOnHand() != null) {
                        oldBook.setQuantityOnHand(bookDTO.getQuantityOnHand());
                    }
                    return bookRepository.save(oldBook);
                }).map(bookMapper::toDto);
    }

    @Override
    public Mono<Void> deleteBook(Integer bookId) {
        return bookRepository.deleteById(bookId);
    }

}
