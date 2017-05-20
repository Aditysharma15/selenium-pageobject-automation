import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;


public class GuerrillaMailTest {
    public WebDriver chromeDriver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tuhin\\Downloads\\chromedriver_win32\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.guerrillamail.com/");
    }


    @Test
    public void testTemporaryEmailSendsEmailWithSentTitleAndBodyFirst() throws InterruptedException {

        String temporaryEmailId = chromeDriver.findElement(By.id("inbox-id")).getText();
        System.out.println(temporaryEmailId);

        Select dropdown = new Select(chromeDriver.findElement(By.id("gm-host-select")));
        String domainName = dropdown.getFirstSelectedOption().getText();
        System.out.println(domainName);
        String scrambledEmail = chromeDriver.findElement(By.id("email-widget")).getText();
        System.out.println(scrambledEmail);

        chromeDriver.findElement(By.id("nav-item-compose")).click();
        chromeDriver.findElement(By.name("to")).sendKeys(temporaryEmailId + "@" + domainName);
        chromeDriver.findElement(By.name("subject")).sendKeys("Testing Guirella");
        chromeDriver.findElement(By.name("body")).sendKeys("This is test Mail from MEEE");
        chromeDriver.findElement(By.name("send")).click();

        String receivedMailId = chromeDriver.findElement(By.id("email_list")).findElement(By.className("td2")).getText();
        System.out.println("OLD " + receivedMailId);
        Thread.sleep(30000);

        receivedMailId = chromeDriver.findElement(By.id("email_list")).findElement(By.className("td2")).getText();
        System.out.println("New " + receivedMailId);
        String emailToVerify = scrambledEmail.split("@")[0];
        assertThat(receivedMailId, containsString(emailToVerify));

    }

    @After
    public void cleanUp() {

        chromeDriver.findElement(By.name("mid[]")).click();
        chromeDriver.findElement(By.id("del_button")).click();
        chromeDriver.close();
    }


}