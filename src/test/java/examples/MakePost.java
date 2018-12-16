package examples;

import bot.Bot;
import core.PostWrapper;
import core.TestBase;
import core.TestUtils;
import core.page.DetailGroupPage;
import core.page.GroupsListPage;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MakePost extends TestBase {

    private final static String PUBLIC_NAME = "test name";
    private final static String PUBLIC_DESCRIPTION = "test description";
    private final static String POST_TEXT1 = "TEXT_1";
    private final static String POST_TEXT2 = "TEXT_2";

    protected Bot loginBot;
    protected GroupsListPage groupsListPage;

    @Before
    public void init() {
        this.loginBot = Bot.generateDefault();
        this.groupsListPage = TestUtils.login(driver, loginBot).navigateToGroups().resetGroups();
    }

    @Test
    public void makePost() {
        DetailGroupPage detailGroupPage = groupsListPage
                .openCreatePublicPopup()
                .createPublic(PUBLIC_NAME, PUBLIC_DESCRIPTION)
                .openNewPostPopup()
                .addNewPost(POST_TEXT1);

        List<PostWrapper> posts = detailGroupPage.getPosts();
        assertEquals(1, posts.size());
        assertEquals(posts.get(0).getText(), POST_TEXT1);
        detailGroupPage = detailGroupPage.openNewPostPopup().addNewPost(POST_TEXT2);
        posts = detailGroupPage.getPosts();
        assertEquals(2, posts.size());
        assertEquals(posts.get(0).getText(), POST_TEXT2);
        assertEquals(posts.get(1).getText(), POST_TEXT1);
        detailGroupPage.returnBack().resetGroups();
    }

    protected void cleanUp() {
        groupsListPage.resetGroups();
    }
}
