package com.polarbookshop.catalogservice.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


public class BookValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenAllFieldsCorrectThenValidationSucceed() {
        Book book = Book.of("0123465789", "Title", "Author", 9.90, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var book = Book.of("", "Title", "Author", 9.90, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(2);
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("ISBN must not be omitted!")
                .contains("The ISBN must be in proper format!");
    }

    @Test
    public void whenIsbnDefinedButIncorrectThenValidationFails(){
        Book book = Book.of("j23465789", "Title", "Author", 9.90, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertTrue(violations.size() == 1);
        assertTrue("The ISBN must be in proper format!".equals(violations.stream().findFirst().get().getMessage()));
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        var book = Book.of("1234567890", "", "Author", 9.90, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Title must not be omitted!");
    }

    @Test
    void whenAuthorIsNotDefinedThenValidationFails() {
        var book = Book.of("1234567890", "Title", "", 9.90, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Author must not be omitted!");
    }

    @Test
    void whenPriceIsNotDefinedThenValidationFails() {
        var book = Book.of("1234567890", "Title", "Author", null, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Price must not be omitted!");
    }

    @Test
    void whenPriceDefinedButZeroThenValidationFails() {
        var book = Book.of("1234567890", "Title", "Author", 0.0, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Price must be greater than zero!");
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        var book = Book.of("1234567890", "Title", "Author", -9.90, "Polarsophia");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Price must be greater than zero!");
    }


}
