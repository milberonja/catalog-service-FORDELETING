package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
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
        Double price
)  { }
