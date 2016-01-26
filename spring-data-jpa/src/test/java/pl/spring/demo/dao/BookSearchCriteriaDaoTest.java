package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.BookSearchCriteria;
import pl.spring.demo.dao.BookSearchCriteriaDao;
import pl.spring.demo.entity.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class BookSearchCriteriaDaoTest {
    
    @Autowired
    private BookSearchCriteriaDao bookSearchCriteriaDao;
    
    @Test
    public void testShouldFindBooksByTitle() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("p", null, null);
        // when
        List<BookEntity> foundBooks = bookSearchCriteriaDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(2, foundBooks.size());
    }
    
    @Test
    public void testShouldFindBooksByAuthors() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, "Jan Kowalski", null);
        // when
        List<BookEntity> foundBooks = bookSearchCriteriaDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(1, foundBooks.size());
    }
    
    @Test
    public void testShouldFindBooksByLibraryname() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, "Politechnika Wroclawska");
        // when
        List<BookEntity> foundBooks = bookSearchCriteriaDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(3, foundBooks.size());
    }
    
    @Test
    public void testShouldFindAllBooks() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, null);
        // when
        List<BookEntity> foundBooks = bookSearchCriteriaDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(5, foundBooks.size());
    }
}
