package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.common.BookEntityToBookToConverter;
import pl.spring.demo.common.BookToToBookEntityConverter;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {
    
    private BookDao bookDao;
    private BookEntityToBookToConverter bookEntityToBookToConverter;
    private BookToToBookEntityConverter bookToToBookEntityConverter;
    
    @Autowired
    public BookServiceImpl(BookDao bookDao,
            BookEntityToBookToConverter bookEntityToBookToConverter,
            BookToToBookEntityConverter bookToToBookEntityConverter) {
        this.bookDao = bookDao;
        this.bookEntityToBookToConverter = bookEntityToBookToConverter;
        this.bookToToBookEntityConverter = bookToToBookEntityConverter;
    }
    
    @Override
    @Cacheable("booksCache")
    public Collection<BookTo> findAllBooks() {
        Collection<BookTo> books = new ArrayList<BookTo>();
        for(BookEntity book : bookDao.findAll()) {
            books.add(bookEntityToBookToConverter.convert(book));
        }
        return books;
    }

    @Override
    public Collection<BookTo> findBooksByTitle(String title) {
        Collection<BookTo> books = new ArrayList<BookTo>();
        for(BookEntity book : bookDao.findBookByTitle(title)) {
            books.add(bookEntityToBookToConverter.convert(book));
        }
        return books;
    }

    @Override
    public Collection<BookTo> findBooksByAuthor(String author) {
        Collection<BookTo> books = new ArrayList<BookTo>();
        for(BookEntity book : bookDao.findBooksByAuthor(author)) {
            books.add(bookEntityToBookToConverter.convert(book));
        }
        return books;
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return bookEntityToBookToConverter
                .convert(bookDao.save(bookToToBookEntityConverter.convert(book)));
    }
}
