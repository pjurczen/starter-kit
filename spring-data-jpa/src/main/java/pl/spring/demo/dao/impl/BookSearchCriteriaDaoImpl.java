package pl.spring.demo.dao.impl;

import java.util.List;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

import pl.spring.demo.common.BookSearchCriteria;
import pl.spring.demo.dao.BookSearchCriteriaDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

public class BookSearchCriteriaDaoImpl extends AbstractDao<BookEntity, Long> implements BookSearchCriteriaDao {

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
