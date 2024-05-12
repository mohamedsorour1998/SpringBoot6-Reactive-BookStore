package app.rosettacloud.spring6reactive.bootStrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.rosettacloud.spring6reactive.domain.Book;
import app.rosettacloud.spring6reactive.domain.Customer;
import app.rosettacloud.spring6reactive.repositories.BookRepository;
import app.rosettacloud.spring6reactive.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

// CommandLineRunner to run on startup
@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBookData();
        loadCustomerData();
        bookRepository.count().subscribe(
                count -> {
                    System.out.println("Books Count is: " + count);
                });
        customerRepository.count().subscribe(count -> {
            System.out.println("Customer Count is: " + count);
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

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if (count == 0) {
                customerRepository.save(Customer.builder()
                        .customerName("Customer 1")
                        .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                        .customerName("Customer 2")
                        .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                        .customerName("Customer 3")
                        .build())
                        .subscribe();
            }
        });
    }
}
