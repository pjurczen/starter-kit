package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Container;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class BookDaoImpl implements BookDao {
    
    private Container<BookTo> books;

    public BookDaoImpl() {
        books = new Container<BookTo>(new HashSet<BookTo>());
    }
    
    @Override
    public Collection<BookTo> findAll() {
        return books.getAsStream()
                .collect(Collectors.toList());
    }

    @Override
    public List<BookTo> findBookByTitle(String title) {
        return null;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return null;
    }
    
    @Override
    @NullableId
    public BookTo save(BookTo book) {
        books.add(book);
        return book;
    }
    
    @PostConstruct
    private void addTestBooks() {
        books.add(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir"));
        books.add(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska"));
        books.add(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski"));
        books.add(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        books.add(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        books.add(new BookTo(6L, "Zemsta", "Aleksander Fredro"));
    }
}
