package examples;

import core.TestUtils;
import core.page.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GroupElemWrapper {
    private final static By GROUP_IMAGE_IN_LIST = By.xpath(".//div[@class='photo']//img");
    private final static By POPUP_ID = By.id("hook_Block_ShortcutMenu");
    private final static By JOIN_POPUP_BUTTON = By.xpath(".//*[@class='tico entity-shortcut-menu_footer-tico']");
    private final static By JOIN_BUTTON = By.xpath(".//button[@class='button-pro __small']");

    private WebDriver driver;
    private WebElement element;

    public GroupElemWrapper(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public void joinFromPopup(PageBase page) {
        WebElement image = element.findElement(GROUP_IMAGE_IN_LIST);
        Actions actions = new Actions(driver);
        actions.moveToElement(image, image.getRect().getWidth() / 2, image.getRect().getHeight() / 2).perform();
        WebElement popup = driver.findElement(POPUP_ID);
        TestUtils.expclicitWaitAssert(page,
                "popup not visible",
                POPUP_ID);
        popup.findElement(JOIN_POPUP_BUTTON).click();
        driver.navigate().refresh();
    }


    public void join() {
        element.findElement(JOIN_BUTTON).click();
        driver.navigate().refresh();
    }
}
