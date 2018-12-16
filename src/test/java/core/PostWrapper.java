package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostWrapper {
    private static final By TEXT = By.xpath(".//div[@class='media-text_cnt']");

    private WebDriver driver;
    private WebElement element;

    public PostWrapper(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public String getText() {
        return element.findElement(TEXT).getText();
    }
}
