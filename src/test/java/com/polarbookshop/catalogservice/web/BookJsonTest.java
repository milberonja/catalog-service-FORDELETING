package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@JsonTest
public class BookJsonTest {

    @Autowired
    JacksonTester<Book> jacksonTester;

    @Test
    public void serializeTest() throws IOException {
        Book book = new Book("0123456789", "Author", "Title", 9.90);
        var serializedBook = jacksonTester.write(book);
        assertThat(serializedBook).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(serializedBook).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(serializedBook).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(serializedBook).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    public void deserializeTest() throws IOException {
        var jsonContent = """
                {
                    "isbn":"0123456789",
                    "title":"Title",
                    "author":"Author",
                    "price":9.90
                }
                """;
        assertThat(jacksonTester.parse(jsonContent)).isEqualTo(new Book("0123456789", "Title", "Author", 9.90));
    }
}
