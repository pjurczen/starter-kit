package pl.spring.demo.dao.impl;

import pl.spring.demo.common.BookSearchCriteria;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

import javax.persistence.TypedQuery;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

import java.util.List;

public class BookDaoImpl extends AbstractDao<BookEntity, Long> implements BookDao {

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        TypedQuery<BookEntity> query = entityManager.createQuery(
                "select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')", BookEntity.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<BookEntity> findBooksBySearchCriteria(BookSearchCriteria bookSearchCriteria) {
        QBookEntity bookEntity = QBookEntity.bookEntity;
        JPAQuery query = new JPAQuery(entityManager).from(bookEntity);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (bookSearchCriteria.getTitle() != null) {
            Predicate ifTitleMatches = bookEntity.title.startsWithIgnoreCase(bookSearchCriteria.getTitle());
            booleanBuilder.and(ifTitleMatches);
        }
        if (bookSearchCriteria.getAuthors() != null) {
            String[] authorNames = bookSearchCriteria.getAuthors().split(" ");
            String firstName = authorNames[0];
            String lastName = authorNames[authorNames.length - 1];
            Predicate ifFirstNameMatches = bookEntity.authors.any().firstName.startsWithIgnoreCase(firstName);
            Predicate ifLastNameMatches = bookEntity.authors.any().lastName.startsWithIgnoreCase(lastName);
            booleanBuilder.and(ifFirstNameMatches).and(ifLastNameMatches);
        }
        if(bookSearchCriteria.getLibraryName()!=null){
            Predicate ifLibraryNameMatches = bookEntity.library.name.startsWithIgnoreCase(bookSearchCriteria.getLibraryName());
            booleanBuilder.and(ifLibraryNameMatches);
        }
        return query.where(booleanBuilder).listResults(bookEntity).getResults();
    }
}
