package pl.spring.demo.pages.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.pages.AbstractSelenium;
import pl.spring.demo.pages.impl.HomePage;

public class HomePageTest extends AbstractSelenium {

    private HomePage homePage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        homePage = openHomePage();
    }

    @Test
    public void homePageShouldContainAllButtons() {
        // then
        assertFalse(homePage.hasError());
        assertTrue(homePage.getBookListButton().isDisplayed());
        assertTrue(homePage.getAuthorListButton().isDisplayed());
        assertTrue(homePage.getDialogAButton().isDisplayed());
        assertTrue(homePage.getDialogBButton().isDisplayed());
        assertTrue(homePage.getMainMenuButton().isDisplayed());
    }
}