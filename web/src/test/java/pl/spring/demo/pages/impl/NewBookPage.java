package pl.spring.demo.pages.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.spring.demo.pages.AbstractPageObject;

public class NewBookPage extends AbstractPageObject {

    @FindBy(xpath = "//input [@ng-model='book.title']")
    private WebElement bookTitle;

    @FindBy(xpath = "//div [@class='modal-body']/table [@class='inline']")
    private WebElement authorsTable;

    @FindBy(xpath = "//div [@class='modal-body']/table/tbody/tr/td/input [@ng-model='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//div [@class='modal-body']/table/tbody/tr/td/input [@ng-model='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//div [@class='modal-body']/table/tbody/tr/td/button [@ng-click='addAuthor(firstName,lastName)']")
    private WebElement addAuthorButton;

    @FindBy(xpath = "//div [@class='modal-footer']/button [@ng-click='addBook()']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-warning') and contains(text(),'Book title is required.')]")
    private WebElement flashTitleRequired;

    @FindBy(xpath = "//div[contains(@class, 'alert-warning') and contains(text(),' Book required at least one author.')]")
    private WebElement flashAuthorRequired;

    public NewBookPage(WebDriver driver) {
        super(driver);
    }

    public NewBookPage setBookTitle(String title) {
        bookTitle.clear();
        bookTitle.sendKeys(title);
        return this;
    }
    public NewBookPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }
    public NewBookPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public NewBookPage clickAddAuthorButton() {
        addAuthorButton.click();
        return this;
    }

    public BookListPage clickSubmitButton() {
        submitButton.click();
        return PageFactory.initElements(driver, BookListPage.class);
    }
}