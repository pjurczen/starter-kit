package pl.spring.demo.pages.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.pages.AbstractSelenium;
import pl.spring.demo.pages.impl.AuthorListPage;

public class AuthorListPageTest extends AbstractSelenium {

    private AuthorListPage authorListPage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        authorListPage = openHomePage().clickAuthorList();
    }

    @Test
    public void shouldLoadAllAuthorsWithPageLoad() {
        // given
        // when
        int allAuthorsCount = authorListPage.countAuthors();
        // then
        assertTrue(allAuthorsCount > 0);
    }
    
    @Test
    public void shouldFindAuthorsWithPrefix() {
        // given
        String prefix = "a";
        // when
        int allAuthorsCount = authorListPage.countAuthors();
        authorListPage.setAuthorPrefix(prefix);
        int filteredAuthorsCount = authorListPage.countAuthors();
        // then
        assertTrue(allAuthorsCount > 0);
        assertNotEquals(allAuthorsCount, filteredAuthorsCount);
    }
}
