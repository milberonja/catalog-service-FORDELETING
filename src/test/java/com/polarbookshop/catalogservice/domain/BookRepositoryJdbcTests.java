package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest //Identifies a class that focuses on Spring Data JDBC components (Involve just needed classes for testing repository slice)
@Import(DataConfig.class) //Imports the data configuration needed to enable auditing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Disables the default behavior of relying on an embedded
                                                                            //test database since we want to use Testcontainers
@ActiveProfiles("integration") //Enables the "integration" profile to load the configuration from application-integration.yml
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; //Because bookRepository is under the test we need other repository
                                                        //to interact with the database
    @Test
    void findBookByIsbnWhenExisting(){
        var bookIsbn = "0123456789";
        var book = Book.of(bookIsbn, "Title", "Author", 9.90, "Polarsophia");
        jdbcAggregateTemplate.insert(book); //jdbcAggregateTemplate is used to prepare data targeted by the test
        Optional<Book> insertedBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(insertedBook).isPresent();
        assertThat(insertedBook.get().isbn()).isEqualTo(bookIsbn);
    }

}
