package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Container;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDaoImpl implements BookDao {
    
    private Container<BookEntity> books;

    @Autowired
    public BookDaoImpl(Container<BookEntity> books) {
        this.books = books;
        addTestBooks();
    }
    
    @Override
    public Collection<BookEntity> findAll() {
        Collection<BookEntity> book = books.getAsStream()
                .collect(Collectors.toList());
        return book;
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        return books.getAsStream()
                .filter(x -> x.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) throws IllegalArgumentException {
        AuthorTo tmpAuthor;
        String[] authorSplit = author.toLowerCase().split(" ");
        try {
            tmpAuthor = new AuthorTo(1L, authorSplit[0], authorSplit[1]); 
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        return books.getAsStream()
                .filter(x -> x.getAuthors().contains(tmpAuthor))
                .collect(Collectors.toList());
    }
    
    @Override
    @NullableId
    public BookEntity save(BookEntity book) {
        books.add(book);
        return book;
    }
    
    private void addTestBooks() {
        books.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
        books.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
        books.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
        books.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        books.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        books.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
    }
}
