package core.page;

import core.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewPostPopup extends PageBase {
    private final static By NEW_POST_POPUP = By.id("mtLayer");
    private final static By NEW_POST_FIELD = By.xpath("//*[@contenteditable='true']");
    private final static By SUBMIT_BUTTON = By.xpath(".//*[@class='posting_submit button-pro']");

    public NewPostPopup(WebDriver webDriver) {
        super(webDriver);
    }

    public DetailGroupPage addNewPost(String text) {
        WebElement popup = driver.findElement(NEW_POST_POPUP);
        WebElement newPostField = popup.findElement(NEW_POST_FIELD);
        newPostField.click();
        newPostField.sendKeys(text);
        popup.findElement(SUBMIT_BUTTON).click();
        return new DetailGroupPage(driver);
    }

    protected void check() {
        TestUtils.expclicitWaitAssert(this, "New post popup didnt appear", NEW_POST_POPUP);
    }
}
