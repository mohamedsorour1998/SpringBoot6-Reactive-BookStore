package app.rosettacloud.spring6reactive.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import app.rosettacloud.spring6reactive.domain.Book;
import app.rosettacloud.spring6reactive.model.BookDTO;
import app.rosettacloud.spring6reactive.repositories.BookRepositoryTest;
import reactor.core.publisher.Mono;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class BookControllerTest {

        @Autowired
        WebTestClient webTestClient;

        @Test
        @Order(3)
        void testAddBook() {
                webTestClient.post().uri(BookController.BOOK_PATH)
                                .body(Mono.just(
                                                BookRepositoryTest.getTestBook()),
                                                BookDTO.class)
                                .header("Content-type", "application/json")
                                .exchange()
                                .expectStatus().isCreated()
                                .expectHeader().location("http://localhost:8080/api/v2/book/4")
                                .expectBody(BookDTO.class);
        }

        @Test
        @Order(7)
        void testAddBookFail() {
                Book testBook = BookRepositoryTest.getTestBook();
                testBook.setBookName("");
                webTestClient.post().uri(BookController.BOOK_PATH)
                                .body(Mono.just(testBook),
                                                BookDTO.class)
                                .header("Content-type", "application/json")
                                .exchange()
                                .expectStatus().isBadRequest();
        }

        @Test
        @Order(4)
        void testDeleteBook() {
                webTestClient.delete().uri(BookController.BOOK_PATH_ID, 4)
                                .exchange()
                                .expectStatus().isNoContent();
        }

        @Test
        @Order(2)
        void testGetBookById() {
                webTestClient.get().uri(BookController.BOOK_PATH_ID, 1)
                                .exchange()
                                .expectStatus().isOk()
                                .expectHeader().valueEquals("Content-type", "application/json")
                                .expectBody(BookDTO.class);
        }

        @Test
        @Order(1)
        void testListBooks() {
                webTestClient.get().uri(BookController.BOOK_PATH)
                                .exchange()
                                .expectStatus().isOk()
                                .expectHeader().valueEquals("Content-type", "application/json")
                                .expectBody().jsonPath("$.size()").isEqualTo(3);
        }

        @Test
        @Order(5)
        void testPatchBook() {
                Book testBook = BookRepositoryTest.getTestBook();
                testBook.setBookName("Patched book");
                webTestClient.patch().uri(BookController.BOOK_PATH_ID, 1)
                                .body(Mono.just(
                                                testBook),
                                                BookDTO.class)
                                .exchange()
                                .expectStatus().isNoContent()
                                .expectBody(BookDTO.class);
        }

        @Test
        @Order(9)
        void testPatchBookFail() {
                Book testBook = BookRepositoryTest.getTestBook();
                testBook.setBookName("");
                webTestClient.patch().uri(BookController.BOOK_PATH_ID, 1)
                                .body(Mono.just(
                                                testBook),
                                                BookDTO.class)
                                .exchange()
                                .expectStatus().isBadRequest();
        }

        @Order(6)
        @Test
        void testUpdateBook() {
                webTestClient.put().uri(BookController.BOOK_PATH_ID, 1)
                                .body(Mono.just(
                                                BookRepositoryTest.getTestBook()),
                                                BookDTO.class)
                                .exchange()
                                .expectStatus().isNoContent()
                                .expectBody(BookDTO.class);
        }

        @Order(8)
        @Test
        void testUpdateBookFail() {
                Book testBook = BookRepositoryTest.getTestBook();
                testBook.setBookName("");
                webTestClient.put().uri(BookController.BOOK_PATH_ID, 1)
                                .body(Mono.just(
                                                testBook),
                                                BookDTO.class)
                                .exchange()
                                .expectStatus().isBadRequest();
        }
}
