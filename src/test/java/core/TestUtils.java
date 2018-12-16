package core;

import bot.Bot;
import core.page.LoginPage;
import core.page.PageBase;
import core.page.UserMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class TestUtils {
    public static void expclicitWaitAssert(PageBase page, String errorLog, By path) {
        assertTrue(errorLog, page.explicitWait(ExpectedConditions.visibilityOfElementLocated(
                path),
                5,
                500));
    }

    public static void scrollToElem(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static UserMainPage login(WebDriver driver, Bot loginBot) {
        return new LoginPage(driver)
                .doLogin(loginBot);
    }
}
