package org.pages;

import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public abstract class BasePage extends Form {

    protected BasePage(By uniqueElementLocator, String namePage) {
        super(uniqueElementLocator, namePage);
    }

    public String getTextElement(IElement iElement) {
        return iElement.getText();
    }

    public String getAttributeElement(IElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public void clickElement(IElement iElement) {
        iElement.click();
    }

    public boolean elementIsNotDisplayed(IElement iElement) {
        return iElement.state().waitForNotDisplayed();
    }

    public boolean elementIsDisplayed(IElement iElement) {
        return iElement.state().waitForDisplayed();
    }
}
