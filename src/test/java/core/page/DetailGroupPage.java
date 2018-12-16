package core.page;

import core.TestUtils;
import core.Transformer;
import core.PostWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DetailGroupPage extends PageBase {
    private final static By TOP_MENU = By.id("hook_Block_AltGroupMainMenu");
    private final static By EXPAND_ELEMENT = By.xpath(".//li[@class='u-menu_li expand-action-item']");
    private final static By DELETE_PUBLIC = By.xpath(".//li[@class='u-menu_li __divided __custom']/a");
    private final static By DELETE_PUBLIC_POPUP = By.id("hook_Modal_popLayerModal");
    private final static By DELETE_PUBLIC_POPUP_BUTTON = By.id("hook_FormButton_button_delete");
    private final static By IN_GROUP_BUTTON = By.xpath("//div[@class='main-content-header_data_main-menu']//*[contains(text(), 'Участник') or contains(text(), 'В группе')]");
    private final static By LEAVE_GROUP = By.xpath("//div[contains(text(), 'Покинуть страницу') or contains(text(), 'Выйти из группы')]");
    private final static By JOIN_TO_GROUP_BUTTON = By.xpath(".//div[@id='hook_Block_AltGroupMainMenu']//a[contains(text(), 'Присоединиться')]");
    private final static By NEW_POST_BLOCK = By.id("hook_Block_PostingForm");
    private final static By NEW_POST_FIELD = By.xpath(".//*[contains(text(), 'Создать новую тему')]");
    private final static By WALL_POST = By.xpath("//*[@class='groups_post-w __search-enabled']");
    private final static By TOP_MENU_CHECK = By.id("hook_Block_MiddleColumnTopCard_MenuAltGroup");

    public DetailGroupPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void check() {
        TestUtils.expclicitWaitAssert(this,
                "group detail page didn't appear",
                TOP_MENU_CHECK);
    }

    public GroupsListPage exitOrLeave() {
        try {
            driver.findElement(IN_GROUP_BUTTON);
            leaveFromGroup();
        } catch (NoSuchElementException e) {
            deletePublic();
        }
        return new GroupsListPage(driver);
    }

    public GroupsListPage leaveFromGroup() {
        WebElement combobox = driver.findElement(IN_GROUP_BUTTON);
        combobox.click();
        combobox.findElement(LEAVE_GROUP).click();
        TestUtils.expclicitWaitAssert(this,
                "impossible to leave the group",
                JOIN_TO_GROUP_BUTTON);
        returnBack();
        return new GroupsListPage(driver);
    }


    public GroupsListPage deletePublic() {
        WebElement expandMenu = driver
                .findElement(TOP_MENU)
                .findElement(EXPAND_ELEMENT);
        expandMenu.click();
        expandMenu.findElement(DELETE_PUBLIC).click();
        TestUtils.expclicitWaitAssert(this, "delete public popup didnt appear", DELETE_PUBLIC_POPUP);
        driver.findElement(DELETE_PUBLIC_POPUP).findElement(DELETE_PUBLIC_POPUP_BUTTON).click();
        return new GroupsListPage(driver);
    }

    public NewPostPopup openNewPostPopup() {
        driver.findElement(NEW_POST_BLOCK).findElement(NEW_POST_FIELD).click();
        return new NewPostPopup(driver);
    }

    public List<PostWrapper> getPosts() {
        return Transformer.wrap(driver.findElements(WALL_POST), driver, PostWrapper::new);
    }

    public GroupsListPage returnBack() {
        driver.findElement(By.id("toolbar_back_id")).click();
        return new GroupsListPage(driver);
    }
}
