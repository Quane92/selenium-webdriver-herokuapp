package com.zawartkawoj.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddRemoveElementsPage {

    @FindBy(xpath = "//button[text()='Add Element']")
    private WebElement addElementButton;

    private WebDriver driver;

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Adding new element...")
    public AddRemoveElementsPage clickAddElementButton(int timesToClick) {
        for (int i = 0; i < timesToClick; i++) {
            addElementButton.click();
        }
        return this;
    }

    @Step("Returning all visible elements count...")
    public int getAddedElementsCount() {
        List<WebElement> addedElements = getAddedElements();
        return addedElements.size();
    }

    @Step("Removing {0} elements...")
    public AddRemoveElementsPage removeElements(int elementsToRemove) {
        for (int i = 0; i < elementsToRemove; i++) {
            getAddedElements().getFirst().click();
        }
        return this;
    }

    @Step("Returning a list of added elements...")
    private List<WebElement> getAddedElements() {
        return driver.findElements(By.xpath("//button[text()='Delete']"))
                .stream()
                .toList();
    }
}
