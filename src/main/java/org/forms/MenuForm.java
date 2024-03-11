package org.forms;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;
import org.pages.BasePage;

public class MenuForm extends BasePage {
    private final IButton myPage = getElementFactory().getButton(By.xpath("//li[contains(@id,'l_pr')]"), "My page button");

    public MenuForm() {
        super(By.xpath("//div[@id='react_rootLeftMenuRoot']"), "Menu form");
    }

    public void clickMyPageButton() {
        clickElement(myPage);
    }
}
