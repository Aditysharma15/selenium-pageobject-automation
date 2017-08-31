package com.guerrillamail.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailInbox extends DriverSetup {
    private static WebElement element = null;


    public static WebElement btn_composeMail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("nav.item.compose.id")));
        return element;
    }

    private static WebElement lnk_InboxMail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("email.list.id")));
        return element;
    }

    public static WebElement lnk_row2OfMail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("email.list.id"))).findElement(By.className(properties.getProperty("td2.className")));
        return element;
    }

    public static WebElement lnk_SubjectOfEmail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("email.list.id"))).findElement(By.className(properties.getProperty("td3.className")));
        return element;
    }

    public static WebElement lnk_emailBodyContent(WebDriver driver) {
        element = lnk_InboxMail(driver).findElement(By.className(properties.getProperty("email.excerpt.className")));
        return element;
    }

    public static WebElement chckbx_deleteMail(WebDriver driver) {
        element = driver.findElement(By.name(properties.getProperty("mid[].name")));
        return element;
    }

    public static WebElement btn_deleteMail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("del.button.id")));
        return element;
    }


}
