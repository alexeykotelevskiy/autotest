package core.page;

import core.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class CreateGroupPopupPage extends PageBase {
    private final static By CREATE_GROUP_POPUP = By.id("hook_Block_PopLayer");
    private final static By CREATE_PUBLIC_BUTTON = By.xpath(".//*[contains(text(), 'Публичная страница')]");
    private final static By CREATE_PUBLIC_FORM = By.id("hook_Form_PopLayerCreateAltGroupDialogForm");
    private final static By NAME_FIELD = By.xpath(".//input[@name='st.layer.name']");
    private final static By DESCRIPTION_FIELD = By.xpath(".//textarea[@name='st.layer.description']");
    private final static By CATEGORY_FIELD = By.xpath(".//select[@name='st.layer.pageMixedCategory']");
    private final static By SUBMIT_FORM_BUTTON = By.id("hook_FormButton_button_create");

    public CreateGroupPopupPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void check() {
        TestUtils.expclicitWaitAssert(this,
                "create group popup didn't appear",
                CREATE_GROUP_POPUP
        );
    }

    public DetailGroupPage createPublic(String name, String description) {
        driver
                .findElement(CREATE_GROUP_POPUP)
                .findElement(CREATE_PUBLIC_BUTTON)
                .click();
        TestUtils.expclicitWaitAssert(this, "create public form didnt appear", CREATE_PUBLIC_FORM);
        typeKeys(NAME_FIELD, name);
        typeKeys(DESCRIPTION_FIELD, description);
        WebElement selectElement = driver
                .findElement(CREATE_GROUP_POPUP).findElement(CATEGORY_FIELD);
        Select category = new Select(selectElement);
        category.selectByIndex(new Random().nextInt(category.getOptions().size()));
        driver.findElement(SUBMIT_FORM_BUTTON).click();
        return new DetailGroupPage(driver);
    }

}
