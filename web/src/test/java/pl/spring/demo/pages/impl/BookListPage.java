package pl.spring.demo.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pl.spring.demo.pages.AbstractPageObject;

import java.util.List;

public class BookListPage extends AbstractPageObject {

    @FindBy(xpath = "//input [@ng-model='prefix']")
    private WebElement bookTitle;

    @FindBy(xpath = "//button [@ng-click='search()']")
    private WebElement searchButton;

    @FindBy(xpath = "//button [@ng-click='addBook()']")
    private WebElement addButton;

    @FindBy(xpath = "//section/table[@class='list']")
    private WebElement bookTable;
    
    @FindBy(xpath = "//section/table[@class='list']/tbody/tr[@class='entity ng-scope']")
    private WebElement bookTableEntity;

    @FindBy(xpath = "//button [@ng-click='updateBook(book)']")
    private List<WebElement> updateButtons;

    @FindBy(xpath = "//button [@ng-click='deleteBook(book.id)']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//div [@role='alert']")
    private WebElement flash;

    
    public BookListPage(WebDriver driver) {
        super(driver);
    }

    public BookListPage setTitlePrefix(String prefix) {
        this.bookTitle.sendKeys(prefix);
        return this;
    }

    public BookListPage clickSearchButton() {
        searchButton.click();
        return this;
    }

    public NewBookPage clickAddBookButton() {
        addButton.click();
        return PageFactory.initElements(driver, NewBookPage.class);
    }

    public List<WebElement> getRows() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(bookTableEntity));
        String xpath = "//tbody/tr[@class='entity ng-scope']";
        return bookTable.findElements(By.xpath(xpath));
    }

    public NewBookPage clickEditButton(int bookId) {
        updateButtons.get(bookId).click();
        return PageFactory.initElements(driver, NewBookPage.class);
    }

    public BookListPage clickDeleteButton(int bookId) {
        deleteButtons.get(bookId).click();
        return this;
    }

    public boolean isFlashDisplayed(String message) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        String xpath = "//span [text() = '" + message +"']";
        wait.until(ExpectedConditions.visibilityOf(flash));
        return flash.findElement(By.xpath(xpath)).isDisplayed();
    }

    public int countBooks() {
        return getRows().size();
    }
}
