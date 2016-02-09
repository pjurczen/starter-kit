package pl.spring.demo.pages.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.spring.demo.pages.AbstractPageObject;

public class DialogAPage extends AbstractPageObject {

    @FindBy(xpath = "//h2 [contains(text(),'Hello from Dialog A!')]")
    private WebElement header;

    @FindBy(xpath = "//img [contains(@src,'Koala.jpg')]")
    private WebElement image;

    public DialogAPage(WebDriver driver) {
        super(driver);
    }

    public boolean isHeaderDisplayed() {
        return header.isDisplayed();
    }

    public boolean isImageDisplayed() {
        return image.isDisplayed();
    }

}
