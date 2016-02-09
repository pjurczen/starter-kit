package pl.spring.demo.pages.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.pages.AbstractSelenium;
import pl.spring.demo.pages.impl.DialogAPage;

public class DialogAPageTest extends AbstractSelenium {

    private DialogAPage dialogAPage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        dialogAPage = openHomePage().clickADialog();
    }

    @Test
    public void shouldDisplayImageAndHeader() {
        // when
        boolean ifHeaderDisplays = dialogAPage.isHeaderDisplayed();
        boolean ifImageDisplays = dialogAPage.isImageDisplayed();
        //then
        assertTrue(ifHeaderDisplays);
        assertTrue(ifImageDisplays);
    }
}