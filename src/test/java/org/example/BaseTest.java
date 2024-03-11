package org.example;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.constants.ConfigConstants;
import org.forms.MenuForm;
import org.pages.MyPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    @BeforeTest
    protected void setUp() {
        ISettingsFile settingsFile = new JsonSettingsFile(ConfigConstants.SETTINGS_FILE_NAME);
        String URL = settingsFile.getValue(ConfigConstants.URL).toString();
        AqualityServices.getBrowser().goTo(URL);
        AqualityServices.getBrowser().maximize();

        MenuForm menuForm = new MenuForm();
        menuForm.clickMyPageButton();

        MyPage myPage = new MyPage();
        myPage.state().waitForDisplayed();
    }

    @AfterTest
    protected void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
