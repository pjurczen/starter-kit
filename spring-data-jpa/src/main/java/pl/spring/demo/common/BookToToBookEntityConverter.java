package pl.spring.demo.common;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookToToBookEntityConverter implements Converter<BookTo, BookEntity> {

    @Override
    public BookEntity convert(BookTo source) {
        Long id = source.getId();
        String title;
        String authors;
        try {
            id = source.getId();
            title = source.getTitle();
            authors = source.getAuthors();
            return new BookEntity(id, title, authors);
        } catch (NullPointerException e) {
            return new BookEntity(id, "", "");
        }
    }

}
