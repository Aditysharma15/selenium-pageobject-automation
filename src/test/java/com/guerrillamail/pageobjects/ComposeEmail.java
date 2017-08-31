package com.guerrillamail.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ComposeEmail extends DriverSetup {
    private static WebElement element = null;


    public static WebElement btn_composeMail(WebDriver driver) {
        element = driver.findElement(By.id(properties.getProperty("nav.item.compose.id")));
        return element;
    }

    public static WebElement txtbx_toOfMail(WebDriver driver) {
        element = driver.findElement(By.name(properties.getProperty("to.name")));
        return element;
    }

    public static WebElement txtbx_SubjectOfMail(WebDriver driver) {
        element = driver.findElement(By.name(properties.getProperty("subject.name")));
        return element;
    }

    public static WebElement txtbx_bodyOfMail(WebDriver driver) {
        element = driver.findElement(By.name(properties.getProperty("body.name")));
        return element;
    }

    public static WebElement btn_sendMail(WebDriver driver) {
        element = driver.findElement(By.name(properties.getProperty("send.name")));
        return element;
    }


}
