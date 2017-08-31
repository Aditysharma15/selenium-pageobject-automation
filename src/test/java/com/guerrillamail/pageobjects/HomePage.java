package com.guerrillamail.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends DriverSetup {
    private static WebElement element = null;

    public static WebElement pageTitle(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("guerrilla.mail")));
        return element;
    }

    public static WebElement txtbx_EmailID(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("inbox.id")));
        return element;
    }

    public static WebElement drpdwn_SelectedId(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("gm.host.select.id")));
        return element;
    }

    public static WebElement txtbx_ScarmbledEmail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("email.widget.id")));
        return element;
    }

}
