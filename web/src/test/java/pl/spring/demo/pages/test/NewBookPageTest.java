package pl.spring.demo.pages.test;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.pages.AbstractSelenium;
import pl.spring.demo.pages.impl.NewBookPage;

import static org.junit.Assert.assertFalse;

public class NewBookPageTest extends AbstractSelenium {

    private NewBookPage newBookPage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        newBookPage = openHomePage().clickBookList().clickAddBookButton();
    }

    @Test
    public void shouldAddBook() throws Exception {
        String title = "Dzieci z Bullerbyn";
        // when
        newBookPage.setBookTitle(title)
                .setFirstName("Astrid")
                .setLastName("Lindgren")
                .clickAddAuthorButton()
                .clickSubmitButton();
        // then
        assertFalse(newBookPage.hasError());
    }
}
