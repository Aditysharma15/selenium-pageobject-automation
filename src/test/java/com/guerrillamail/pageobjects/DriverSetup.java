package com.guerrillamail.pageobjects;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.InputStream;
import java.util.Properties;

public abstract class DriverSetup {
    protected static WebDriver chromeDriver;
    protected static Properties properties;

    @Before
    public void setUp() throws Exception {

        String resourceName = "email-object-repo.properties";
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try (InputStream resourceStream = contextClassLoader.getResourceAsStream(resourceName)) {
            properties.load(resourceStream);
        }

        System.out.println("Property class loaded");

        // Ensure you have the right driver for your environment. This is for windows
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.guerrillamail.com/");
    }

}
