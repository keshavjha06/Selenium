package seleniumconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Locators2 extends BaseTest {

    @Test
    public void locatorsDemo() {
        driver.get("https://login.salesforce.com/?login=1");
        driver.findElement(By.name("username")).sendKeys("name");
        driver.findElement(By.xpath("//*[@id='Login']")).click();
        System.out.println(new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#error.loginError"))).getText());

    }

}
