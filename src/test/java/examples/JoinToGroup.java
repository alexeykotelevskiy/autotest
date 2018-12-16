package examples;

import bot.Bot;
import core.TestBase;
import core.TestUtils;
import core.page.GroupsListPage;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JoinToGroup extends TestBase {
    protected Bot loginBot;
    protected GroupsListPage groupsListPage;

    @Before
    public void init() {
        this.loginBot = Bot.generateDefault();
        this.groupsListPage = TestUtils.login(driver, loginBot).navigateToGroups().resetGroups();
    }

    @Test
    public void joinToGroup() {
        List<GroupElemWrapper> groupsList = groupsListPage.getGroupsList();
        assertTrue(groupsList.size() > 0);
        int groupsCount = groupsListPage
                .joinToRandomGroup(groupsList)
                .getGroupsCount();
        assertEquals(1, groupsCount);
        assertEquals(0, groupsListPage.resetGroups().getGroupsCount());
    }

    protected void cleanUp() {
        groupsListPage.resetGroups();
    }
}
