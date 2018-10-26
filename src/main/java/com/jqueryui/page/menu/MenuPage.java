package com.jqueryui.page.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='sidebar']//a[contains(text(), 'Droppable')]")
    private WebElement droppable;

    @FindBy(xpath = "//*[@id='sidebar']//a[contains(text(), 'Datepicker')]")
    private WebElement datepicker;

    @FindBy(xpath = "//*[@id='sidebar']//a[contains(text(), 'Slider')]")
    private WebElement slider;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickDroppable() {
        droppable.click();
    }

    public void clickDatepicker() {
        datepicker.click();
    }

    public void clickSlider() {
        slider.click();
    }
}
