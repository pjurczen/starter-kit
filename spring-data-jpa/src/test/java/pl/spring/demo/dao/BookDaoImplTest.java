package pl.spring.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = bookDao.findOne(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("Pierwsza książka", bookEntity.getTitle());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "p";
        // when
        List<BookEntity> booksEntity = bookDao.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
    
    @Test
    public void testShouldFindBooksByTitleWithSearchCriteria() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria("p", null, null);
        // when
        List<BookEntity> foundBooks = bookDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(2, foundBooks.size());
    }
    
    @Test
    public void testShouldFindBooksByAuthorsWithSearchCriteria() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, "Jan Kowalski", null);
        // when
        List<BookEntity> foundBooks = bookDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(1, foundBooks.size());
    }
    
    @Test
    public void testShouldFindBooksByLibraryNameWithSearchCriteria() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, "Politechnika Wroclawska");
        // when
        List<BookEntity> foundBooks = bookDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(3, foundBooks.size());
    }
    
    @Test
    public void testShouldFindAllBooksWithSearchCriteria() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria(null, null, null);
        // when
        List<BookEntity> foundBooks = bookDao.findBooksBySearchCriteria(bookSearchCriteria);
        // then
        assertNotNull(foundBooks);
        assertFalse(foundBooks.isEmpty());
        assertEquals(5, foundBooks.size());
    }
}
