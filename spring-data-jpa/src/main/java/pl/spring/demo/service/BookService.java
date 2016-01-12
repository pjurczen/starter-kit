package pl.spring.demo.service;

import java.util.Collection;

import pl.spring.demo.to.BookTo;



public interface BookService {

    Collection<BookTo> findAllBooks();
    Collection<BookTo> findBooksByTitle(String title);
    Collection<BookTo> findBooksByAuthor(String author);

    BookTo saveBook(BookTo book);
}
