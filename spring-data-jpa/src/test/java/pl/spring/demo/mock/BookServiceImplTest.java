package pl.spring.demo.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.common.BookEntityToBookToConverter;
import pl.spring.demo.common.BookToToBookEntityConverter;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;
    @Mock
    private BookToToBookEntityConverter bookToToBookEntityConverter;
    @Mock
    private BookEntityToBookToConverter bookEntityToBookToConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author");
        Mockito.when(bookService.saveBook(book)).thenReturn(new BookTo(1L, "title", "AuthorFirstName AuthorLastName"));
        // when
        BookTo result = bookService.saveBook(book);
        // then
        assertEquals(1L, result.getId().longValue());
    }
}
