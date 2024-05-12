package app.rosettacloud.spring6reactive.bootStrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.rosettacloud.spring6reactive.domain.Book;
import app.rosettacloud.spring6reactive.repositories.BookRepository;
import lombok.RequiredArgsConstructor;

// CommandLineRunner to run on startup
@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBookData();
        bookRepository.count().subscribe(
                count -> {
                    System.out.println("Count is: " + count);
                });
    }

    private void loadBookData() {
        bookRepository.count().subscribe(
                count -> {
                    if (count == 0) {
                        Book Book1 = Book.builder()
                                .bookName("Book1")
                                .bookStyle("HardCover")
                                .upc("12356")
                                .price(new BigDecimal("12.99"))
                                .quantityOnHand(122)
                                .createdDate(LocalDateTime.now())
                                .lastModifiedDate(LocalDateTime.now())
                                .build();

                        Book Book2 = Book.builder()
                                .bookName("Book2")
                                .bookStyle("SoftCover")
                                .upc("12356222")
                                .price(new BigDecimal("11.99"))
                                .quantityOnHand(392)
                                .createdDate(LocalDateTime.now())
                                .lastModifiedDate(LocalDateTime.now())
                                .build();

                        Book Book3 = Book.builder()
                                .bookName("Book3")
                                .bookStyle("HardCover")
                                .upc("12356")
                                .price(new BigDecimal("13.99"))
                                .quantityOnHand(144)
                                .createdDate(LocalDateTime.now())
                                .lastModifiedDate(LocalDateTime.now())
                                .build();

                        bookRepository.save(Book1).subscribe();
                        bookRepository.save(Book2).subscribe();
                        bookRepository.save(Book3).subscribe();
                    }
                });
    }
}
