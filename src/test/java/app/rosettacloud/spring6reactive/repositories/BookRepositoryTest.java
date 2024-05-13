package app.rosettacloud.spring6reactive.repositories;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.rosettacloud.spring6reactive.config.DatabaseConfig;
import app.rosettacloud.spring6reactive.domain.Book;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getTestBook()));
    }

    @Test
    void saveNewBook() {
        bookRepository.save(getTestBook())
                .subscribe(
                        book -> {
                            System.out.println(book.toString());
                        });
    }

    public static Book getTestBook() {
        return Book.builder()
                .bookName("Book1")
                .bookStyle("HardCover")
                .price(BigDecimal.ONE)
                .quantityOnHand(10)
                .upc("12345")
                .build();
    }

}
