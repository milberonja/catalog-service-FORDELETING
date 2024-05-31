package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String isbn){
        super("Book with ISBN " + isbn + " already exist in Catalog service");
    }
}
