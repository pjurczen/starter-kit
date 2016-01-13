package pl.spring.demo.common;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookEntityToBookToConverter implements Converter<BookEntity, BookTo> {

    @Override
    public BookTo convert(BookEntity source) {
        Long id = source.getId();
        String title = source.getTitle();;
        String authors = "";
        try {
            Collection<AuthorTo> sourceAuthors = source.getAuthors();
            Iterator<AuthorTo> iterator = sourceAuthors.iterator();
            iterator.next();
            for(AuthorTo author : sourceAuthors) {
                authors += author.getFirstName() + " " + author.getLastName();
                if(iterator.hasNext()) {
                    authors += ", ";
                    iterator.next();
                }
            }
        } catch (NullPointerException e) {
            
        }
        return new BookTo(id, title, authors);
    }

}
