package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class UserMainPage extends PageBase {

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    public GroupsListPage navigateToGroups() {
        click("//i[@class='tico_img ic ic_nav_groups-v2']");

        return new GroupsListPage(driver);
    }

    public void navigateToVideos() {
        click("//*[@id=\"hook_Block_TopMenuVideo\"]/div/div[1]/div");
    }

    @Override
    protected void check() {
        assertTrue("main page didn't appear",
                explicitWait(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@id='hook_Block_Avatar']")),
                        5,
                        500));
    }
}
