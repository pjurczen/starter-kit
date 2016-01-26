package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.common.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

public interface BookSearchCriteriaDao extends Dao<BookEntity, Long> {
    List<BookEntity> findBooksBySearchCriteria(BookSearchCriteria bookSearchCriteria);
}
