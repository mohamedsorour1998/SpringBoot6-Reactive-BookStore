package app.rosettacloud.spring6reactive.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import app.rosettacloud.spring6reactive.domain.Customer;
import app.rosettacloud.spring6reactive.model.CustomerDTO;
import app.rosettacloud.spring6reactive.repositories.BookRepositoryTest;
import reactor.core.publisher.Mono;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class CustomerControllerTest {
        @Autowired
        WebTestClient webTestClient;

        private Customer getTestCustomer() {
                return Customer.builder()
                                .customerName("testCust1")
                                .build();
        }

        @Test
        @Order(3)
        void testCreateNewCustomer() {
                webTestClient.post().uri(CustomerController.CUSTOMER_PATH)
                                .body(Mono.just(
                                                this.getTestCustomer()),
                                                CustomerDTO.class)
                                .header("Content-type", "application/json")
                                .exchange()
                                .expectStatus().isCreated()
                                .expectHeader().location("http://localhost:8080/api/v2/customer/4")
                                .expectBody(CustomerDTO.class);

        }

        @Test
        @Order(3)
        void testCreateNewCustomerFail() {
                Customer badCustomer = this.getTestCustomer();
                badCustomer.setCustomerName("");
                webTestClient.post().uri(CustomerController.CUSTOMER_PATH)
                                .body(Mono.just(
                                                badCustomer),
                                                CustomerDTO.class)
                                .header("Content-type", "application/json")
                                .exchange()
                                .expectStatus().isBadRequest();

        }

        @Test
        @Order(4)
        void testDeleteById() {
                webTestClient.delete().uri(CustomerController.CUSTOMER_PATH_ID, 4)
                                .exchange();
                // .expectStatus().isNoContent();
        }

        @Test
        @Order(2)
        void testGetCustomerById() {
                webTestClient.get().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                                .exchange()
                                .expectStatus().isOk()
                                .expectHeader().valueEquals("Content-type", "application/json")
                                .expectBody(CustomerDTO.class);

        }

        @Test
        @Order(1)
        void testListCustomers() {
                webTestClient.get().uri(CustomerController.CUSTOMER_PATH)
                                .exchange()
                                .expectStatus().isOk()
                                .expectHeader().valueEquals("Content-type", "application/json")
                                .expectBody().jsonPath("$.size()").isEqualTo(3);
        }

        @Test
        @Order(5)
        void testPatchExistingCustomer() {

        }

        @Test
        @Order(6)
        void testUpdateExistingCustomer() {
                webTestClient.put().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                                .body(Mono.just(
                                                BookRepositoryTest.getTestBook()),
                                                CustomerDTO.class)
                                .exchange()
                                // .expectStatus().isNoContent()
                                .expectBody(CustomerDTO.class);

        }
}
