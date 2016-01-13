package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pl.spring.configuration.CommonServiceTestConfiguration;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = CommonServiceTestConfiguration.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CacheManager cacheManager;
    
    
    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }

    @Test
    public void testShouldFindAllBooks() {
        // when
        Collection<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "opium w rosole";
        // when
        Collection<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
        // given
        final String author = "wili szeks";
        // when
        Collection<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
        // then
        assertNotNull(booksByAuthor);
        assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIllegalArgumentException() {
        // given
        final String author = "wiliamszekspir";
        // when
        bookService.findBooksByAuthor(author);
        // then
        fail("test should throw IllegalArgumentException");
    }
    
    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
}
