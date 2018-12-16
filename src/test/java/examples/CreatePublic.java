package examples;

import bot.Bot;
import core.TestBase;
import core.TestUtils;
import core.page.GroupsListPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreatePublic extends TestBase {
    private final static String PUBLIC_NAME = "test name";
    private final static String PUBLIC_DESCRIPTION = "test description";

    protected Bot loginBot;
    protected GroupsListPage groupsListPage;

    @Before
    public void init() {
        this.loginBot = Bot.generateDefault();
        this.groupsListPage = TestUtils.login(driver, loginBot).navigateToGroups().resetGroups();
    }

    @Test
    public void createPublic() {
        GroupsListPage gropusList = groupsListPage
                .openCreatePublicPopup()
                .createPublic(PUBLIC_NAME, PUBLIC_DESCRIPTION)
                .deletePublic();
        int count = gropusList.getGroupsCount();
        Assert.assertEquals(0, count);
    }

    protected void cleanUp() {
        groupsListPage.resetGroups();
    }
}
