package com.guerrillamail.tests;

import com.guerrillamail.pageobjects.ComposeEmail;
import com.guerrillamail.pageobjects.DriverSetup;
import com.guerrillamail.pageobjects.EmailInbox;
import com.guerrillamail.pageobjects.HomePage;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class EmailClientTest extends DriverSetup {

    private final static Logger LOGGER = Logger.getLogger(EmailClientTest.class.getName());
    private static String temporaryEmailId;
    private static String domainName;
    private static String scrambledEmail;

    private static String receivedMailId;
    private static String receivedTitle;
    private static String receivedBody;
    private static String emailToVerify;
    private static String recepientEmailId;


    private void composeAndSendMail() throws InterruptedException {
        ComposeEmail.btn_composeMail(chromeDriver).click();
        ComposeEmail.txtbx_toOfMail(chromeDriver).sendKeys(properties.getProperty(recepientEmailId));
        ComposeEmail.txtbx_SubjectOfMail(chromeDriver).sendKeys(properties.getProperty("title"));
        ComposeEmail.txtbx_bodyOfMail(chromeDriver).sendKeys(properties.getProperty("body"));

        if (ComposeEmail.btn_sendMail(chromeDriver).isDisplayed()) {
            ComposeEmail.btn_sendMail(chromeDriver).click();
        }

    }


    private void testSendMail() throws InterruptedException {
        receivedMailId = EmailInbox.lnk_row2OfMail(chromeDriver).getText();
        LOGGER.info("OLD " + receivedMailId);
        Thread.sleep(30000);
        receivedMailId = EmailInbox.lnk_row2OfMail(chromeDriver).getText();
        LOGGER.info("New " + receivedMailId);


        emailToVerify = scrambledEmail.split("@")[0];
        assertThat(receivedMailId, containsString(emailToVerify));

        receivedTitle = EmailInbox.lnk_SubjectOfEmail(chromeDriver).getText();
        receivedBody = EmailInbox.lnk_emailBodyContent(chromeDriver).getText();

        LOGGER.info(receivedBody);
        LOGGER.info(receivedTitle);

        assertThat(receivedBody, containsString(properties.getProperty("body")));
        assertThat(receivedTitle, containsString(properties.getProperty("title")));

    }


    @Test
    public void testCopyTemporaryMail() throws Exception {
        temporaryEmailId = HomePage.txtbx_EmailID(chromeDriver).getText();
        domainName = new Select(HomePage.drpdwn_SelectedId(chromeDriver)).getFirstSelectedOption().getText();
        scrambledEmail = HomePage.txtbx_ScarmbledEmail(chromeDriver).getText();
        recepientEmailId = temporaryEmailId + "@" + domainName;
        properties.setProperty(recepientEmailId, recepientEmailId);
        LOGGER.info(recepientEmailId);
        composeAndSendMail();
        testSendMail();
    }

    @After
    public void deleteMail() {
        EmailInbox.chckbx_deleteMail(chromeDriver).click();
        EmailInbox.btn_deleteMail(chromeDriver).click();
        chromeDriver.close();
    }

}
