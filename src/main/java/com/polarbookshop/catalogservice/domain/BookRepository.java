package com.polarbookshop.catalogservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
//    Iterable<Book> findAll(); //SpringDataJdbc offer findAll() and save() methods out of box
    Optional<Book> findByIsbn(String isbn); // Rest of methods should be declared explicitly
    boolean existsByIsbn(String isbn);// Rest of methods should be declared explicitly
//    Book save(Book book);//SpringDataJdbc offer findAll() and save() methods out of box

    @Modifying //Identifies an operation that will modify the database state
    @Transactional //Identifies the method to be executed in a transaction
    @Query("delete from Book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
