package pl.spring.demo.pages.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.pages.AbstractSelenium;
import pl.spring.demo.pages.impl.BookListPage;

public class BookListPageTest extends AbstractSelenium {
	
	private BookListPage bookListPage;
	
	@Override
    @Before
    public void setUp() {
        super.setUp();
        bookListPage = openHomePage().clickBookList();
    }
    
    @Test
    public void shouldFindAllBooksWithEmptyPrefix() {
        // given
        String prefix = "";
        // when
        bookListPage.clickSearchButton();
        int allBooksCount = bookListPage.countBooks();

        bookListPage.setTitlePrefix(prefix);
        int filteredBooksCount = bookListPage.countBooks();
        bookListPage.clickSearchButton();
        // then
        assertTrue(allBooksCount > 0);
        assertEquals(filteredBooksCount, allBooksCount);
    }
    
    @Test
    public void shouldFindBooksWithPrefix() {
        // given
        String prefix = "p";
        // when
        bookListPage.clickSearchButton();
        int allBooksCount = bookListPage.countBooks();

        bookListPage.setTitlePrefix(prefix);
        bookListPage.clickSearchButton();
        int filteredBooksCount = bookListPage.countBooks();
        // then
        assertTrue(allBooksCount > 0);
        assertNotEquals(filteredBooksCount, allBooksCount);
    }
    
    @Test
    public void shouldEditBookAndFlashAlert() {
        // given
        int bookIndex = 0;
        String newTitle = "W pustyni i w puszczy";
        // when
        bookListPage.clickSearchButton();
        bookListPage.clickEditButton(bookIndex)
                        .setBookTitle(newTitle)
                        .clickSubmitButton();
        // then
        assertNotNull(bookListPage.getRows()
                                    .get(bookIndex));
        assertTrue(bookListPage.isFlashDisplayed("Book \""+ newTitle +"\" updated successfully!"));
    }
    
    @Test
    public void shouldAddBookAndFlashAlert() {
        // given
        String title = "Pilot i ja";
        String firstName = "Grzegorz";
        String lastName = "Brzęczyszczykiewicz";
        // when
        bookListPage.clickAddBookButton()
                        .setBookTitle(title)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .clickAddAuthorButton()
                        .clickSubmitButton();
        // then
        assertNotNull(bookListPage.getRows()
                                    .get(0));
        assertTrue(bookListPage.isFlashDisplayed("Book \""+ title +"\" added successfully!"));
    }
    
    @Test
    public void shouldRemoveBook() {
        // given
        int bookIndex = 0;
        // when
        bookListPage.clickSearchButton();
        int allBooksCount = bookListPage.countBooks();
        bookListPage.clickDeleteButton(bookIndex);
        int booksAfterDeletionCount = bookListPage.countBooks();
        // then
        assertEquals(allBooksCount - 1, booksAfterDeletionCount);
        assertTrue(bookListPage.isFlashDisplayed("Book successfully deleted."));
    }
}
