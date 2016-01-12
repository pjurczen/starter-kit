package pl.spring.demo.dao;

import pl.spring.demo.to.BookTo;

import java.util.Collection;


public interface BookDao {

    Collection<BookTo> findAll();

    Collection<BookTo> findBookByTitle(String title);

    Collection<BookTo> findBooksByAuthor(String author);

    BookTo save(BookTo book);
}
