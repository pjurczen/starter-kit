package pl.spring.demo.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pl.spring.demo.pages.AbstractPageObject;

import java.util.List;

public class AuthorListPage extends AbstractPageObject {

    @FindBy(xpath = "//input [@ng-model='prefix']")
    private WebElement authorPrefix;

    @FindBy(xpath = "//section/table [@class='listAuthors']")
    private WebElement authorTable;

    @FindBy(xpath = "//section/table[@class='listAuthors']/tbody/tr[@class='entity ng-scope']")
    private WebElement authorTableEntity;

    public AuthorListPage(WebDriver driver) {
        super(driver);
    }

    public AuthorListPage setAuthorPrefix(String prefix) {
        authorPrefix.clear();
        authorPrefix.sendKeys(prefix);
        return this;
    }

    public List<WebElement> getRows() {
        String xpath = "//tbody/tr [@class='entity ng-scope']";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(authorTableEntity));
        return authorTable.findElements(By.xpath(xpath));
    }

    public int countAuthors() {
        return getRows().size();
    }
}
