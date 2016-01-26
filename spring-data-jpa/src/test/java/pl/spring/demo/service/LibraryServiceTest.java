package pl.spring.demo.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {
    
    @Autowired
    LibraryService libraryService;
    
    @Autowired
    BookService bookService;
    
    @Test
    public void testShouldDeleteAllBooksWithLibrary() {
        //given
        final long id = 1L;
        List<BookTo> allBooks = bookService.findAllBooks();
        List<BookTo> booksInLibrary = libraryService.findAllBooksByLibraryId(id);
        //when
        libraryService.removeLibraryById(id);
        //then
        List<BookTo> booksLeft = bookService.findAllBooks();
        Assert.assertEquals(booksLeft.size(), allBooks.size() - booksInLibrary.size());
    }
}
