package core.page;

import core.TestUtils;
import core.Transformer;
import examples.GroupElemWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class GroupsListPage extends PageBase {
    private final static By CREATE_GROUP_CONTAINER = By.xpath("//div[@class='create-group']");
    private final static By GROUPS_LIST = By.xpath("//div[@class='ucard-v __h  soh-s']");
    private final static By JOIN_BUTTON = By.xpath(".//button[@class='button-pro __small']");
    private final static By MY_GROUPS_LIST = By.xpath("//div[@class='portlet __sidebar __my-groups']");
    private final static By MY_GROUPS_COUNT = By.xpath("//div[@class='portlet_f ']/a[contains(text(), 'Все мои группы')]");
    private final static By LEAVE_FROM_GROUP_BUTTON = By.xpath("//ul[@class='ugrid_cnt']//div[@class='section']/a[@class='dblock']");

    public GroupsListPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void check() {
        TestUtils.expclicitWaitAssert(this,
                "group page didn't appear",
                CREATE_GROUP_CONTAINER);
    }

    public List<GroupElemWrapper> getGroupsList() {
        List<WebElement> list = driver.findElements(GROUPS_LIST);
        return Transformer.wrap(list, driver, GroupElemWrapper::new);
    }

    public GroupsListPage joinToRandomGroupPopup(List<GroupElemWrapper> wrappedList) {
        GroupElemWrapper randGroup = wrappedList.get(new Random().nextInt(wrappedList.size()));
        randGroup.joinFromPopup(this);
        return this;
    }

    public GroupsListPage joinToRandomGroup(List<GroupElemWrapper> wrappedList) {
        GroupElemWrapper randGroup = wrappedList.get(new Random().nextInt(wrappedList.size()));
        randGroup.join();
        return this;
    }

    private WebElement getGroupsListContainer() throws NoSuchElementException {
        WebElement list = driver.findElement(MY_GROUPS_LIST);
        TestUtils.scrollToElem(driver, list);
        return list;
    }

    public int getGroupsCount() {
        WebElement groupsListContainer;
        try {
            groupsListContainer = getGroupsListContainer();
        } catch (NoSuchElementException e) {
            return 0;
        }
        String groupsLabel = groupsListContainer.findElement(MY_GROUPS_COUNT).getText();
        String[] literals = groupsLabel.split(" ");
        return Integer.parseInt(literals[literals.length - 1]);
    }

    public GroupsListPage resetGroups() {
        final int groupsCount = getGroupsCount();
        for (int i = 0; i < groupsCount; ++i) {
            WebElement groupsListContainer = getGroupsListContainer();
            groupsListContainer.findElement(LEAVE_FROM_GROUP_BUTTON).click();
            new DetailGroupPage(driver).exitOrLeave();
        }
        return this;
    }

    public CreateGroupPopupPage openCreatePublicPopup() {
        driver.findElement(CREATE_GROUP_CONTAINER).findElement(By.tagName("a")).click();
        return new CreateGroupPopupPage(driver);
    }
}
