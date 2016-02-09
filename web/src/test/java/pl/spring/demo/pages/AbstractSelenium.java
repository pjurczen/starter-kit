package pl.spring.demo.pages;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.pages.impl.HomePage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RunWith(SeleniumScreenshotJUnit4Runner.class)
public abstract class AbstractSelenium {

    private WebDriver driver;

    @Before
    public void setUp() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("init.accept_languages","en");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public HomePage openHomePage() {
        return PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("C:\\tmp\\"+screenshot.getName());
        FileUtils.copyFile(screenshot, destFile);
        System.out.println(String.format("[[ATTACHMENT|%s]]", destFile.getAbsolutePath()));
    }
}