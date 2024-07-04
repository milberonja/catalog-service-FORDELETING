package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

//If we use Spring Data JPA we have to use Book class entity as JPA works with mutating objects.
//With Spring Data JPA we will here use @Entity annotations and @Id and @Version will be from javax.persistence package
//As we use Spring Data JDBC we use Book record entity like not mutating object
public record Book(
        @Id
        Long id,

        @NotBlank(message = "ISBN must not be omitted!")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$",
        message = "The ISBN must be in proper format!")
        String isbn,

        @NotBlank(message = "Title must not be omitted!")
        String title,

        @NotBlank(message = "Author must not be omitted!")
        String author,

        @NotNull(message = "Price must not be omitted!")
        @Positive(message = "Price must be greater than zero!")
        Double price,

        String publisher,

        @CreatedDate //When the entity was created
        Instant createdDate,

        @LastModifiedDate //When the entity was last modified
        Instant lastModifiedDate,

        @Version
        int version
)  {
        public static Book of(String isbn, String title, String author, Double price, String publisher){
                return new Book(null, isbn, title, author, price, publisher, null, null, 0);
        }
}
