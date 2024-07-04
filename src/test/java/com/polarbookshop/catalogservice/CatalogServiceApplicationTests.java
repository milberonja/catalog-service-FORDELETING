package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.config.DataConfig;
import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration") //Enables the "integration" profile to load the configuration from application-integration.yml
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CatalogServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    WebTestClient webTestClient;


    @Test
    public void whenPostRequestThenBookCreated(){
        Book bookToSave = Book.of("0123456789", "Title", "Author", 9.90, "Polarsophia");
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToSave)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .equals(bookToSave);
//                .expectBody(Book.class).value(bookAfterSaving ->{
//                    assertThat(bookAfterSaving.isbn()).isEqualTo(bookAfterSaving.isbn());
//                });
    }



//    @Test
//    void whenGetRequestWithIdThenBookReturned() {
//        var bookIsbn = "1231231230";
//        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
//        Book expectedBook = webTestClient
//                .post()
//                .uri("/books")
//                .bodyValue(bookToCreate)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
//                .returnResult().getResponseBody();
//
//        webTestClient
//                .get()
//                .uri("/books/" + bookIsbn)
//                .exchange()
//                .expectStatus().is2xxSuccessful()
//                .expectBody(Book.class).value(actualBook -> {
//                    assertThat(actualBook).isNotNull();
//                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
//                });
//    }

//    @Test
//    void whenPutRequestThenBookUpdated() {
//        var bookIsbn = "1231231232";
//        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
//        Book createdBook = webTestClient
//                .post()
//                .uri("/books")
//                .bodyValue(bookToCreate)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
//                .returnResult().getResponseBody();
//        var bookToUpdate = new Book(createdBook.isbn(), createdBook.title(), createdBook.author(), 7.95);
//
//        webTestClient
//                .put()
//                .uri("/books/" + bookIsbn)
//                .bodyValue(bookToUpdate)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Book.class).value(actualBook -> {
//                    assertThat(actualBook).isNotNull();
//                    assertThat(actualBook.price()).isEqualTo(bookToUpdate.price());
//                });
//    }

//    @Test
//    void whenDeleteRequestThenBookDeleted() {
//        var bookIsbn = "1231231233";
//        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
//        webTestClient
//                .post()
//                .uri("/books")
//                .bodyValue(bookToCreate)
//                .exchange()
//                .expectStatus().isCreated();
//
//        webTestClient
//                .delete()
//                .uri("/books/" + bookIsbn)
//                .exchange()
//                .expectStatus().isNoContent();
//
//        webTestClient
//                .get()
//                .uri("/books/" + bookIsbn)
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectBody(String.class).value(errorMessage ->
//                        assertThat(errorMessage).isEqualTo("The book with ISBN " + bookIsbn + " was not found.")
//                );
//    }

}
