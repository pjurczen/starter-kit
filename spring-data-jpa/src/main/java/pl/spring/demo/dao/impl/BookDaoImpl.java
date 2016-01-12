package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Container;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDaoImpl implements BookDao {
    
    private Container<BookTo> books;

    @Autowired
    public BookDaoImpl(Container<BookTo> books) {
        this.books = books;
        addTestBooks();
    }
    
    @Override
    public Collection<BookTo> findAll() {
        Collection<BookTo> book = books.getAsStream()
                .collect(Collectors.toList());
        return book;
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
    
    private void addTestBooks() {
        books.add(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir"));
        books.add(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska"));
        books.add(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski"));
        books.add(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        books.add(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        books.add(new BookTo(6L, "Zemsta", "Aleksander Fredro"));
    }
}
