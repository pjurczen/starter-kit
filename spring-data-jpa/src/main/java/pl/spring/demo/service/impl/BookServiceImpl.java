package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {
    
    private BookDao bookDao;
    
    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    
    @Override
    @Cacheable("booksCache")
    public Collection<BookTo> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Collection<BookTo> findBooksByTitle(String title) {
        return bookDao.findBookByTitle(title);
    }

    @Override
    public Collection<BookTo> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author);
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return bookDao.save(book);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
