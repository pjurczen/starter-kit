package pl.spring.demo.mapper;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookTo map(BookEntity bookEntity) {
        if (bookEntity != null) {
            return new BookTo(bookEntity.getId(), bookEntity.getTitle(), mapAuthors(bookEntity.getAuthors()));
        }
        return null;
    }

    public static BookEntity map(BookTo bookTo) {
        if (bookTo != null) {
            BookEntity bookEntity = new BookEntity(bookTo.getId(), bookTo.getTitle());
            bookEntity.setAuthors(mapAuthors(bookTo.getAuthors()));
            bookEntity.setLibrary(new LibraryEntity(1L, "Capgemini"));
            return bookEntity;
        }
        return null;
    }

    public static List<BookTo> map2To(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    private static List<AuthorTo> mapAuthors(Collection<AuthorEntity> authors) {
        List<AuthorTo> authorsTo = new ArrayList<AuthorTo>();
        authors.stream().forEach(authorEntity -> authorsTo.add(new AuthorTo(authorEntity.getId(), authorEntity.getFirstName(), authorEntity.getLastName())));
        return authorsTo;
    }
    
    private static Set<AuthorEntity> mapAuthors(List<AuthorTo> authors) {
        Set<AuthorEntity> authorsSet = new HashSet<AuthorEntity>();
        authors.stream().forEach(authorTo -> authorsSet.add(new AuthorEntity(authorTo.getFirstName(), authorTo.getLastName())));
        return authorsSet;
    }
}