package pl.spring.demo.dao;

import pl.spring.demo.to.BookEntity;

import java.util.Collection;


public interface BookDao {

    Collection<BookEntity> findAll();

    Collection<BookEntity> findBookByTitle(String title);

    Collection<BookEntity> findBooksByAuthor(String author);

    BookEntity save(BookEntity book);
}
